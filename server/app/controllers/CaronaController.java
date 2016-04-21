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
import play.Logger;

import static play.libs.Json.*;
import static play.data.Form.form;



public class CaronaController extends Controller {

    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);

        Usuario motorista = UsuarioController.usuarioAutenticado();
        carona.setMotorista(motorista);


        SistemaCaronas.getInstance().adicionarCarona(carona);

        Logger.info(motorista.getEmail() + " acabou de cadastrar uma nova carona");


        return ok("Carona cadastrada com sucesso!");
    }


    public Result rejeitarCarona(){                       
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);

        if (solicitacao.setStatus(Status.REJEITADO)){ 
            Logger.info(solicitacao.getCarona().getMotorista().getEmail() + " recusou o pedido de carona de " + solicitacao.getPassageiro().getEmail());                    // so pode recusar se a carona estiver pendente
            return ok(Json.toJson(solicitacao));
        } else {
            return badRequest("Você já tomou uma decisão sobre esse pedido");
        }
        
    }



}