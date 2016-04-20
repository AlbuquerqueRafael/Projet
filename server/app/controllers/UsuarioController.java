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



public class UsuarioController extends Controller {


    public Result getAllCadastro(){
        List<Usuario> usuarios = SistemaUsuarios.getInstance().getUsuarios();
        return ok(toJson(usuarios));
    }

    public Result logout() {
        session().clear();
        return ok("Logged out successfully");
    }

    public static Usuario usuarioAutenticado() {
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



    



}