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


@Security.Authenticated(Secured.class)
public class UsuarioController extends Controller {

    private SistemaUsuarios sistemaUsuarios = SistemaUsuarios.getInstance();
    private SistemaCaronas sistemaCaronas = SistemaCaronas.getInstance();


    public Result getCaronasComoPassageiro(){
        List<Carona> caronasComoPassageiro = new ArrayList<Carona>();
        Usuario usuarioAtual = AutenticacaoController.usuarioAutenticado();
        List<Carona> allCaronas = SistemaCaronas.getInstance().getCaronas();


        for(Carona carona : allCaronas){
            System.out.println(carona.getListaPassageiros());
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

    public Result getCaronasComoMotoristas(){
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();
        List<Carona> allCaronas = SistemaCaronas.getInstance().getCaronas();
        System.out.println(AutenticacaoController.usuarioAutenticado());

        for (Carona carona : allCaronas) { 
            if (AutenticacaoController.usuarioAutenticado().equals(carona.getMotorista())) {
                caronasComoMotorista.add(carona);
            }
        }
        
        return ok(Json.toJson(caronasComoMotorista));
    }


    public Result postCadastro() {
        JsonNode json = request().body().asJson();
        System.out.println(json);
        Usuario usuario = Json.fromJson(json, Usuario.class);
        //      usuario.setNovasNotificacoes(new ArrayList<Notificacao>());


        try{
            sistemaUsuarios.adicionarUsuario(usuario);
        } catch (DadosInvalidosException exception){
            return badRequest(exception.getMessage());
        }


        ServiceLog.novaMensagemLog(usuario.getEmail() + " acabou de se cadastrar no sistema");

        return ok(Json.toJson(usuario));
    }





}



    