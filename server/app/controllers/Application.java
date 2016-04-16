
package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.*;
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

        if(usuarios == null){
           return badRequest("Usuario ou senha inválidos");
        }

        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getEmail() != null && usuarios.get(i).getEmail().equals(user.getEmail()) && usuarios.get(i).getSenha().equals(user.getSenha())){
                Usuario usuario = new Usuario();
                usuario.setNome(usuarios.get(i).getNome());
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
            return badRequest("E-mail inválido");
        }

        if(!Util.isValidTelefone(usuario.getTelefone())){
            return badRequest("O telefone deve possuir 9 digítos.");
        }

        SistemaUsuarios.getInstance().adicionarUsuario(usuario);
        for(Usuario s : SistemaUsuarios.getInstance().getUsuarios()){
            System.out.println(s.getCaronasMotorista());
            System.out.println(s.getEndereco());
            System.out.println(s.getEmail());
            System.out.println(s.getSenha());
        }

        return ok(Json.toJson(usuario));
    }

    public Result getHorariosMotoristas(){
        /*List<Usuario> usuarios = Usuario.find.all();
        return ok(toJson(usuarios));*/
        return badRequest("O telefone deve possuir 9 digítos.");
    }



    public Result getHorarios(){
        /*JsonNode json = request().body().asJson();
        System.out.println(json.toString());
        Usuario user = Json.fromJson(json, Usuario.class);

        if(Usuario.find.byId(user.getId()) != null){
            Usuario usuario = Usuario.find.byId(user.getId());
            for(Carona c : usuario.getCaronas()){
                System.out.println(c.getHorario());
            }
            return ok(Json.toJson(usuario.getCaronas()));
        }
*/
        return badRequest("Usuario não Encontrado!");
    }

    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Usuario usuario = Json.fromJson(json, Usuario.class);
        System.out.println(json.toString());

/*        if(Usuario.find.byId(usuario.getId()) != null){
            Usuario user = Usuario.find.byId(usuario.getId());
            List<Carona> caronas = usuario.getCaronas();
            System.out.println(caronas);
            user.getCaronas().addAll(caronas);
            user.update();

            return ok((Json.toJson(user)));

        }*/

        return badRequest("Usuario não Encontrado!");
    }

    public Result getAllCadastro(){
        List<Usuario> usuarios = SistemaUsuarios.getInstance().getUsuarios();
        return ok(toJson(usuarios));
    }




}
