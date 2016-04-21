package controllers;


import models.*;
import play.mvc.*;
<<<<<<< HEAD
import sistemasInfo.SistemaLog;
import sistemasInfo.SistemaUsuarios;
=======
import sistemaInfo.SistemaLog;
import sistemaInfo.SistemaUsuarios;
>>>>>>> master

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
        Usuario usuarioAtual = usuarioAutenticado();
        session().clear();
        SistemaLog.novaMensagemLog(usuarioAtual.getEmail() + " saiu do sistema");
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