package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import exception.*;
import play.libs.Json;
import play.mvc.*;
import services.*;
import sistemasInfo.SistemaUsuarios;
import java.util.ArrayList;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;


import java.util.List;

import static play.data.Form.form;



public class AutenticacaoController extends Controller {

    private static SistemaUsuarios sistemaUsuarios = SistemaUsuarios.getInstance();

    public  Result postLogin() {
        JsonNode json = request().body().asJson();
        List<Usuario> usuarios = Usuario.find.findList();
        Usuario user = Json.fromJson(json, Usuario.class);

        for(Usuario usuario: usuarios){
            if(usuario.getEmail().equals(user.getEmail()) && usuario.getSenha().equals(user.getSenha())){
                autenticar(usuario);
                ServiceLog.novaMensagemLog(usuario.getEmail() + " acabou de logar");
                Usuario newUsuario = new Usuario(usuario.getNome(), usuario.getEndereco(), usuario.getEmail());
                return ok(Json.toJson(newUsuario));
            }
        }

        ServiceLog.novaMensagemLog(user.getEmail() + " tentou logar, mas possui email ou senha inválidos!");
        return badRequest("Email ou senha inválidos!");
    }

    private void autenticar(Usuario usuario){
        session().put("logado", usuario.getEmail());
    }


     @Transactional
    public Result postCadastro() {
        JsonNode json = request().body().asJson();
        Usuario usuario = Json.fromJson(json, Usuario.class);
        usuario.setListaNotificacoes(new ArrayList<String>());

        /*
        try{
           sistemaUsuarios.adicionarUsuario(usuario);
        } catch (DadosInvalidosException exception){
            return badRequest(exception.getMessage());
        }
        */

        usuario.save();

        ServiceLog.novaMensagemLog(usuario.getEmail() + " acabou de se cadastrar no sistema");

        return ok(Json.toJson(usuario));
    }



}