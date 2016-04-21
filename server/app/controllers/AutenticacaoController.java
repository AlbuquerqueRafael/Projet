package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import util.Util;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import play.Logger;

import static play.libs.Json.*;
import static play.data.Form.form;



public class AutenticacaoController extends Controller {

    private static SistemaUsuarios sistemaUsuarios = SistemaUsuarios.getInstance();

    public  Result postLogin() {
        JsonNode json = request().body().asJson();
        List<Usuario> usuarios = sistemaUsuarios.getUsuarios();
        Usuario user = Json.fromJson(json, Usuario.class);

        for(Usuario usuario: usuarios){
            if(usuario.getEmail().equals(user.getEmail()) && usuario.getSenha().equals(user.getSenha())){
                autenticar(usuario);
                Logger.info(usuario.getEmail() + " acabou de logar");
                Usuario newUsuario = new Usuario();
                newUsuario.setEmail(usuario.getEmail());
                newUsuario.setEndereco(usuario.getEndereco());
                return ok(Json.toJson(newUsuario));
            }
        }

        Logger.info(user.getEmail() + " tentou logar, mas não está cadastrado");
        return badRequest("Usuario ou senha inválidos!");
    }

    private void autenticar(Usuario usuario){
        session().put("logado", usuario.getEmail());
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

        sistemaUsuarios.adicionarUsuario(usuario);

        Logger.info(usuario.getEmail() + " acabou de se cadastrar no sistema");

        return ok(Json.toJson(usuario));
    }


}