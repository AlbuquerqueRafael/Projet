
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

public class Application extends Controller {



    public Result main(String any) {
        return ok(main.render());
    }

    public Result index() {
        return ok(index.render());
    }

    public  Result postLogin() {
        JsonNode json = request().body().asJson();
        List<Usuario> usuarios = SistemaUsuarios.getInstance().getUsuarios();
        Usuario user = Json.fromJson(json, Usuario.class);

        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(user.getEmail()) && usuarios.get(i).getSenha().equals(user.getSenha())){
                autenticar(usuarios.get(i));
                Usuario usuario = new Usuario();
                usuario.setEmail(usuarios.get(i).getEmail());
                usuario.setEndereco(usuarios.get(i).getEndereco());
                return ok(Json.toJson(usuario));
            }
        }


        return badRequest("Usuario ou senha inválidos!");
    }


    public Result buscarCarona(Long id){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);
        carona.setMotorista(usuarioAutenticado());

        if(verificaSeHorarioPreenchido(carona)){
            return badRequest("Horário já preenchido");
        }

        int numeroItens = Math.toIntExact(id-1) * 6;
        int count = numeroItens;

        boolean fimDaLista = false;
        List<Carona> caronas = SistemaCaronas.getInstance().getCaronas();
        int limite = caronas.size();
        List<Usuario> filterMotoristas = new ArrayList<Usuario>();
        Carona caroItera = caronas.get(count);

        while(caroItera != null && count < numeroItens + 6 && !fimDaLista){
           if(!caroItera.getMotorista().equals(usuarioAutenticado())) {
              if(!Util.isValidHorarioCarona(caroItera, carona) && Util.isEnderecoCompativel(caroItera, carona)
                      && caroItera.getTipo().equals(carona.getTipo())){
                  Usuario usuario = new Usuario();

                  Usuario motoristaCarona = caroItera.getMotorista();
                  usuario.setNome(motoristaCarona.getNome());
                  usuario.setEmail(motoristaCarona.getEmail());
                  usuario.setTelefone(motoristaCarona.getTelefone());
                  Endereco enderecoMotorista = motoristaCarona.getEndereco();
                  usuario.setEndereco(enderecoMotorista);

                  filterMotoristas.add(usuario);
                  numeroItens--;
               }
           }

            numeroItens++;

            if(count < limite) {
                caroItera = caronas.get(count++);
            }else{
                fimDaLista = true;
            }
        }



        if(filterMotoristas.size() == 0){
            return badRequest("Não há caronas para este horário");
        }
        return ok(Json.toJson(filterMotoristas));
    }

    private boolean verificaSeHorarioPreenchido(Carona carona){
        List<Carona> caronasComoPassageiro = new ArrayList<Carona>();
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();

        for(Carona c : SistemaCaronas.getInstance().getCaronas()){
            if(usuarioAutenticado().equals(c.getMotorista())){
                caronasComoMotorista.add(c);
            }

            for(Usuario passageiros : c.getListaPassageiros()){
                if(usuarioAutenticado().equals(caronasComoPassageiro)){
                    caronasComoPassageiro.add(c);
                }
            }
        }

        if(verHorarioPreenchidoUsuario(caronasComoPassageiro, carona)){
            return true;
        }

        if(verHorarioPreenchidoUsuario(caronasComoMotorista, carona)){
            return true;
        }
        return false;
    }

    private boolean verHorarioPreenchidoUsuario(List<Carona> caronas, Carona carona){
        for(int i = 0; i < caronas.size(); i++){
            if(!Util.isValidHorarioCarona(carona, caronas.get(i))){
                return true;
            }
        }

        return false;
    }

    public Result postCadastro() {
        JsonNode json = request().body().asJson();
        System.out.println(json.toString());
        Usuario usuario = Json.fromJson(json, Usuario.class);
        System.out.println(Json.toJson(usuario));

        if(!Util.isValidEmailAddress(usuario.getEmail())){
            return badRequest("Email inválido");
        }

        if(!Util.isValidMatricula(usuario.getMatricula())){
            return badRequest("Matricula inválida");
        }

        if(!Util.isValidPassword(usuario.getSenha())){
            return badRequest("Senha inválida");
        }

        if(!Util.isValidTelefone(usuario.getTelefone())){
            return badRequest("Telefone inválido");
        }

        SistemaUsuarios.getInstance().adicionarUsuario(usuario);

        return ok(Json.toJson(usuario));
    }

    public Result getCaronasMotoristasUsuario(){
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();
        for(Carona c : SistemaCaronas.getInstance().getCaronas()){
            if(usuarioAutenticado().equals(c.getMotorista())){
                caronasComoMotorista.add(c);
            }
        }


        return ok(Json.toJson(caronasComoMotorista));
    }


    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);

        Usuario motorista = usuarioAutenticado();
        carona.setMotorista(motorista);


        SistemaCaronas.getInstance().adicionarCarona(carona);


        return ok("Carona cadastrada com Sucesso");
    }

    public Result solicitarCarona(){
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);

        
        if (Util.isCaronaLotada(solicitacao.getCarona())){
            return badRequest("Ops! Carona lotada!");
        }

        Usuario passageiro = usuarioAutenticado();
     //   passageiro.setSolicitacoesEnviadas(solicitacao);

        Usuario motorista = solicitacao.getCarona().getMotorista();
      //  motorista.setSolicitacoesRecebidas(solicitacao);

        return ok(Json.toJson(solicitacao)); // precisaria disso mesmo?
    }

    public Result aceitarCarona(){                       
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);

        if (Util.isCaronaLotada(solicitacao.getCarona())){
            return badRequest("Ops! Carona lotada!");
        }

        if (solicitacao.setStatus(Status.ACEITO)){                          // so pode aceitar se a carona estiver pendente
            Usuario passageiro = solicitacao.getPassageiro();
            Carona carona = solicitacao.getCarona();
            carona.novoPassageiro(passageiro);
            return ok(Json.toJson(solicitacao));

        } else {
            return badRequest("Você já tomou uma decisão sobre esse pedido");
        }

    }


    public Result rejeitarCarona(){                       
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);

        if (solicitacao.setStatus(Status.REJEITADO)){                          // so pode recusar se a carona estiver pendente
            return ok(Json.toJson(solicitacao));
        } else {
            return badRequest("Você já tomou uma decisão sobre esse pedido");
        }
        
    }

    public Result getPedidosCaronas(Long id){
        List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
        System.out.println(SistemaSolicitacao.getInstance().getSolicitacao().size());
        for(Solicitacao c : SistemaSolicitacao.getInstance().getSolicitacao()){
            System.out.println(c.getCarona().getMotorista().getNome());
            if(usuarioAutenticado().equals(c.getCarona().getMotorista())){
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

    public Result getAllCadastro(){
        List<Usuario> usuarios = SistemaUsuarios.getInstance().getUsuarios();
        return ok(toJson(usuarios));
    }

    public Result logout() {
        session().clear();
        return ok("Logged out successfully");
    }

    private Usuario usuarioAutenticado() {
        if(session().get("logado") == null) {
            return null;
        } else {
            Usuario templateUsuario = new Usuario();
            templateUsuario.setEmail(session().get("logado"));
            int index = SistemaUsuarios.getInstance().getUsuarios().indexOf(templateUsuario);
            Usuario usuario = SistemaUsuarios.getInstance().getUsuarios().get(index);
            return usuario;
        }
    }

    private void autenticar(Usuario usuario){
        session().put("logado", usuario.getEmail());
    }


}
