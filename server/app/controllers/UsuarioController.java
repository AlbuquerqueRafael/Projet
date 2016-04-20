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

    private static SistemaUsuarios sistemaUsuarios = SistemaUsuarios.getInstance();

    public Result getAllCadastro(){
        List<Usuario> usuarios = sistemaUsuarios.getUsuarios();
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
            int index = sistemaUsuarios.recuperarPosicaoDoUsuario(templateUsuario);
            Usuario usuario = sistemaUsuarios.recuperarUsuarioPelaPosicao(index);
            return usuario;
        }
    }



    



}