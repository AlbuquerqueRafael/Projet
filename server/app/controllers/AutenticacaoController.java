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

import static play.libs.Json.*;
import static play.data.Form.form;



public class AutenticacaoController extends Controller {

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

        SistemaUsuarios.getInstance().adicionarUsuario(usuario);

        return ok(Json.toJson(usuario));
    }


}