
package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import util.Util;
import views.html.busca;
import views.html.index;
import views.html.home;
import views.html.cadastro;
import views.html.main;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static play.libs.Json.*;
import static play.data.Form.form;

public class Application extends Controller {

    @PersistenceContext
    private EntityManager em;


    public Result main(String any) {
        return ok(main.render());
    }

    public Result index() {
        return ok(index.render());
    }

    public  Result postLogin() {
        JsonNode json = request().body().asJson();
        List<Usuario> usuarios = Usuario.find.all();
        Usuario user = Json.fromJson(json, Usuario.class);
        System.out.println(Arrays.toString(usuarios.toArray()));

        if(usuarios == null){
           return badRequest("Usuario ou senha inválidos");
        }

        for(int i = 0; i < usuarios.size(); i++){
            System.out.println(usuarios.get(i).getEmail());
            if(usuarios.get(i).getEmail() != null && usuarios.get(i).getEmail().equals(user.getEmail()) && usuarios.get(i).getSenha().equals(user.getSenha())){
                Usuario usuario = new Usuario();
                usuario.setNome(usuarios.get(i).getNome());
                usuario.setId(usuarios.get(i).getId());
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

    @Transactional
    public Result postCadastro() {
        JsonNode json = request().body().asJson();
        Usuario usuario = Json.fromJson(json, Usuario.class);

        if(!Util.isValidEmailAddress(usuario.getEmail())){
            return badRequest("E-mail inválido");
        }

        if(!Util.isValidTelefone(usuario.getTelefone())){
            return badRequest("O telefone deve possuir 9 digítos.");
        }

        usuario.save();
        return ok(Json.toJson(usuario));
    }

    public Result getHorariosMotoristas(){
        List<Usuario> usuarios = Usuario.find.all();
        return ok(toJson(usuarios));
    }



    public Result getHorarios(){
        JsonNode json = request().body().asJson();
        System.out.println(json.toString());
        Usuario user = Json.fromJson(json, Usuario.class);

        if(Usuario.find.byId(user.getId()) != null){
            Usuario usuario = Usuario.find.byId(user.getId());
            for(Carona c : usuario.getCaronas()){
                System.out.println(c.getHorario());
            }
            return ok(Json.toJson(usuario.getCaronas()));
        }

        return badRequest("Usuario não Encontrado!");
    }

    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Usuario usuario = Json.fromJson(json, Usuario.class);
        System.out.println(json.toString());

        if(Usuario.find.byId(usuario.getId()) != null){
            Usuario user = Usuario.find.byId(usuario.getId());
            List<Carona> caronas = usuario.getCaronas();
            System.out.println(caronas);
            user.getCaronas().addAll(caronas);
            user.update();

            return ok((Json.toJson(user)));

        }

        return badRequest("Usuario não Encontrado!");
    }

    public Result getAllCadastro(){
        List<Usuario> usuarios = Usuario.find.all();
        return ok(toJson(usuarios));
    }




}
