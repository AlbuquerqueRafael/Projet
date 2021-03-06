package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import exception.DadosInvalidosException;
import exception.FotoInvalidaException;
import models.*;
import play.libs.Json;
import play.mvc.*;
import services.ServiceLog;
import database.*;
import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.*;
import static play.data.Form.form;



public class UsuarioController extends Controller {

    private SistemaUsuarios sistemaUsuarios = SistemaUsuarios.getInstance();
    private SistemaCaronas sistemaCaronas = SistemaCaronas.getInstance();

    @Security.Authenticated(Secured.class)
    public Result getCaronasComoPassageiro(){
        List<Carona> caronasComoPassageiro = new ArrayList<Carona>();
        Usuario usuarioAtual = AutenticacaoController.usuarioAutenticado();
        List<Carona> allCaronas = SistemaCaronas.getInstance().getCaronas();


        for(Carona carona : allCaronas){
            for(Usuario passageiro: carona.getListaPassageiros()){
                if(usuarioAtual.equals(passageiro)){
                    Usuario newMotorista = new Usuario(carona.getMotorista().getEmail(), carona.getMotorista().getTelefone());
                    Carona newCarona = new Carona(carona.getHorario(),carona.getVagas(), newMotorista, carona.getTipo());
                    caronasComoPassageiro.add(newCarona);
                }
            }
        }
        return ok(Json.toJson(caronasComoPassageiro));
    }

    @Security.Authenticated(Secured.class)
    public Result getCaronasComoMotoristas(){
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();
        List<Carona> allCaronas = SistemaCaronas.getInstance().getCaronas();

        for (Carona carona : allCaronas) { 
            if (AutenticacaoController.usuarioAutenticado().equals(carona.getMotorista())) {
                caronasComoMotorista.add(carona);
            }
        }
        
        return ok(Json.toJson(caronasComoMotorista));
    }


    public Result postCadastro() {
        JsonNode json = request().body().asJson();
        Usuario usuario = Json.fromJson(json, Usuario.class);


        try{
            sistemaUsuarios.adicionarUsuario(usuario);
        } catch (DadosInvalidosException exception){
            return badRequest(exception.getMessage());
        }


        ServiceLog.novaMensagemLog(usuario.getEmail() + " acabou de se cadastrar no sistema");

        return ok(Json.toJson(usuario));
    }





}



    