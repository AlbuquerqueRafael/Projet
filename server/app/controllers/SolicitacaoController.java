package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.Logger;
import play.libs.Json;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 21/04/16.
 */
public class SolicitacaoController extends Controller{

    public static final int NUM_ITENS_PAGINA = 3;
    public Result solicitarCarona(){
        JsonNode json = request().body().asJson();

        Carona caronaSolicitada = Json.fromJson(json, Carona.class);
        Solicitacao solicitacao = new Solicitacao(caronaSolicitada, UsuarioController.usuarioAutenticado());

        SistemaSolicitacao.getInstance().adicionarSolicitacao(solicitacao);

        Logger.info(solicitacao.getPassageiro().getEmail() + " acabou de solicitar uma carona a " + solicitacao.getCarona().getMotorista().getEmail());

        return ok(Json.toJson("Solicitação concluida!"));

    }

    public Result aceitarCarona(){
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);
        List<Solicitacao> solicitacoes = SistemaSolicitacao.getInstance().getSolicitacao();
        solicitacao.getCarona().setMotorista(UsuarioController.usuarioAutenticado());
        System.out.println(Json.toJson(solicitacao));
        String telefone = null;
        for(Solicitacao s: solicitacoes){
            Carona carona = s.getCarona();
            if(s.equals(solicitacao) && solicitacao.getCarona().equals(carona)){
                s.setStatus(Status.ACEITO);
                int vagas = solicitacao.getCarona().getVagas();

                carona.novoPassageiro(solicitacao.getPassageiro());
                carona.setVagas(--vagas);
                telefone = s.getPassageiro().getTelefone();
                SistemaSolicitacao.getInstance().removerSolicitacao(s);
                break;
            }
        }

        for(Solicitacao c : SistemaSolicitacao.getInstance().getSolicitacao()){
            System.out.println(Json.toJson(c));
        }


        return ok(Json.toJson(telefone));

    }

    public Result getSolicitacoesCaronas(Long id){
        List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
        for(Solicitacao c : SistemaSolicitacao.getInstance().getSolicitacao()){
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
        int quantElementosLista = filterPassageiros.size();

        try{
            solicitacao = solicitacoes.get(count);
        }catch(IndexOutOfBoundsException e){
            return badRequest("Sem solicitacoes");
        }

        for(Solicitacao s : solicitacoes){
            System.out.println(Json.toJson(s));
        }

        while(solicitacao != null && quantElementosLista < NUM_ITENS_PAGINA && !fimDaLista){
            if(!solicitacao.getStatus().equals(Status.ACEITO)){
                Solicitacao sol = new Solicitacao();
                Carona carona = new Carona();
                Endereco end = new Endereco();
                Usuario usuario = new Usuario();
                carona.setEndereco(end);

                Horario horarioSolicitacao = solicitacao.getCarona().getHorario();
                TipoCarona tipo = solicitacao.getCarona().getTipo();
                String nomePassageiro = solicitacao.getPassageiro().getNome();
                String emailPassageiro = solicitacao.getPassageiro().getEmail();
                String telefonePassageiro = solicitacao.getPassageiro().getEmail();
                Endereco endereco = solicitacao.getCarona().getEndereco();
                int vagas = solicitacao.getCarona().getVagas();

                usuario.setNome(nomePassageiro);
                usuario.setEmail(emailPassageiro);
                usuario.setTelefone(telefonePassageiro);

                carona.setHorario(horarioSolicitacao);
                carona.setTipo(tipo);
                carona.setEndereco(endereco);
                carona.setVagas(vagas);

                sol.setCarona(carona);
                sol.setPassageiro(usuario);

                filterPassageiros.add(sol);
            }

            quantElementosLista = filterPassageiros.size();
            if (++count < limite) {
                solicitacao = solicitacoes.get(count);
            } else {
                fimDaLista = true;
            }


        }

        return ok(Json.toJson(filterPassageiros));
    }
}
