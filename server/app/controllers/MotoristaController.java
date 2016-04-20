package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import util.Util;
import views.html.index;
import views.html.main;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static play.libs.Json.*;
import static play.data.Form.form;



public class MotoristaController extends Controller {
    

     public Result getCaronasMotoristasUsuario(){
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();
        for(Carona c : SistemaCaronas.getInstance().getCaronas()){
            if(UsuarioController.usuarioAutenticado().equals(c.getMotorista())){
                caronasComoMotorista.add(c);
            }
        }


        return ok(Json.toJson(caronasComoMotorista));
    }



    public Result getPedidosCaronas(Long id){
        List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
        System.out.println(SistemaSolicitacao.getInstance().getSolicitacao().size());
        for(Solicitacao c : SistemaSolicitacao.getInstance().getSolicitacao()){
            System.out.println(c.getCarona().getMotorista().getNome());
            if(UsuarioController.usuarioAutenticado().equals(c.getCarona().getMotorista())){
                solicitacoes.add(c);
            }
        }

        List<Solicitacao> filterPassageiros = new ArrayList<Solicitacao>();
        int numeroItens = Math.toIntExact(id-1) * 3;
        int count = numeroItens;
        int limite = solicitacoes.size();
        Solicitacao solicitacao = new Solicitacao();
        boolean fimDaLista = false;

        System.out.println("gg");
        try{
            solicitacao = solicitacoes.get(count);
        }catch(ArrayIndexOutOfBoundsException e){
            return badRequest("Sem solicitacoes");
        }

        System.out.println("gg");
        while(solicitacao != null && count < numeroItens + 3 && !fimDaLista){
            Solicitacao sol = new Solicitacao();
            Carona carona = new Carona();
            Endereco end = new Endereco();
            Usuario usuario = new Usuario();
            carona.setEndereco(end);

            Horario horarioSolicitacao = solicitacao.getCarona().getHorario();
            TipoCarona tipo = solicitacao.getCarona().getTipo();
            String nomePassageiro = solicitacao.getPassageiro().getNome();
            String emailPassageiro = solicitacao.getPassageiro().getEmail();
            String bairro = solicitacao.getCarona().getEndereco().getBairro();


            usuario.setNome(nomePassageiro);
            usuario.setEmail(emailPassageiro);
            carona.setHorario(horarioSolicitacao);
            carona.setTipo(tipo);
            carona.getEndereco().setBairro(bairro);



            sol.setCarona(carona);
            sol.setPassageiro(usuario);

            filterPassageiros.add(sol);

            if(count < limite) {
                solicitacao = solicitacoes.get(count++);
            }else{
                fimDaLista = true;
            }
            System.out.println("4");

        }

        return ok(Json.toJson(filterPassageiros));
    }


}