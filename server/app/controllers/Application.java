
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
        System.out.println(id);
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);
        carona.setMotorista(usuarioAutenticado());

        if(verificaSeHorarioPreenchido(carona)){
            return badRequest("Horário já preenchido");
        }

        int numeroPaginas = 0;
        boolean fimDaLista = false;
        int count = numeroPaginas;
        List<Carona> caronas = SistemaCaronas.getInstance().getCaronas();
        int limite = caronas.size();
        List<Usuario> filterMotoristas = new ArrayList<Usuario>();
        Carona caroItera = caronas.get(count);

        while(caroItera != null && count < numeroPaginas + 7 && !fimDaLista){
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
                  numeroPaginas--;
               }
           }

            numeroPaginas++;

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
        if(verHorarioPreenchidoUsuario(usuarioAutenticado().getCaronasPassageiro(), carona)){
            return true;
        }

        if(verHorarioPreenchidoUsuario(usuarioAutenticado().getCaronasMotorista(), carona)){
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
        for(Usuario s : SistemaUsuarios.getInstance().getUsuarios()){
            System.out.println(s.getCaronasMotorista());
            System.out.println(s.getEndereco());
            System.out.println(s.getEmail());
            System.out.println(s.getSenha());
        }
        System.out.println(Json.toJson(usuario));
        return ok(Json.toJson(usuario));
    }

    public Result getCaronasMotoristasUsuario(){
       System.out.println(usuarioAutenticado().getCaronasMotorista());
        return ok(Json.toJson(usuarioAutenticado().getCaronasMotorista()));
    }


    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);

        Usuario motorista = usuarioAutenticado();
        carona.setMotorista(motorista);
        motorista.setCaronasMotorista(carona);


        SistemaCaronas.getInstance().adicionarCarona(carona);


        return ok(Json.toJson(motorista.getCaronasMotorista()));
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
