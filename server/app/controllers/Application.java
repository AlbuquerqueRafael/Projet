
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

        System.out.println(usuarios);
        if(usuarios == null){
           return badRequest("Usuario ou senha inválidos");
        }

        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getEmail() != null && usuarios.get(i).getEmail().equals(user.getEmail()) && usuarios.get(i).getSenha().equals(user.getSenha())){
                autenticar(usuarios.get(i));
                Usuario usuario = new Usuario();
                usuario.setNome(usuarios.get(i).getNome());
                usuario.setEndereco(usuarios.get(i).getEndereco());
                return ok(Json.toJson(usuario));
            }
        }

        return badRequest("Usuario ou senha inválidos!");
    }

  /*  @Transactional
    public Result verificaCadastro(Usuario usuario) {
        return ok();
    }
*/

    public Result postCadastro() {
        JsonNode json = request().body().asJson();
        System.out.println(json.toString());
        Usuario usuario = Json.fromJson(json, Usuario.class);


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

    public Result getHorariosMotoristas(){
        System.out.println(isAuthenticated());
        return ok(Json.toJson(isAuthenticated().getCaronasMotorista()));
    }


    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);

        System.out.println(Json.toJson(carona));
        Usuario motorista = isAuthenticated();
        carona.setMotorista(motorista);
        motorista.setCaronasMotorista(carona);

        if(carona.getEndereco() == null){
            carona.setEndereco(motorista.getEndereco());
        }


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

    private Usuario isAuthenticated() {
        if(session().get("logado") == null) {
            return null;
        } else {
            Usuario templateUsuario = new Usuario();
            templateUsuario .setEmail(session().get("logado"));
            int index = SistemaUsuarios.getInstance().getUsuarios().indexOf(templateUsuario);
            Usuario usuario = SistemaUsuarios.getInstance().getUsuarios().get(index);
            return usuario;
        }
    }

    private void autenticar(Usuario usuario){
        session().put("logado", usuario.getEmail());
    }


}
