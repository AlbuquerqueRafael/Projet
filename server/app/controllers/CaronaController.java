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



public class CaronaController extends Controller {

    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);

        Usuario motorista = UsuarioController.usuarioAutenticado();
        carona.setMotorista(motorista);


        SistemaCaronas.getInstance().adicionarCarona(carona);

        AdminController.novaCarona(carona);


        return ok("Carona cadastrada com sucesso!");
    }


    public Result solicitarCarona(){
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);

        
        if (Util.isCaronaLotada(solicitacao.getCarona())){
            return badRequest("Ops! Carona lotada!");
        }

        SistemaSolicitacao.getInstance().adicionarSolicitacao(solicitacao);

        AdminController.novaSolicitacao(solicitacao);

        return ok(Json.toJson(solicitacao)); // precisaria disso mesmo?
    }


    public Result aceitarCarona(){                       
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);

        if (Util.isCaronaLotada(solicitacao.getCarona())){
            return badRequest("Ops! Carona lotada!");
        }

        if (solicitacao.setStatus(Status.ACEITO)){                          // so pode aceitar se a carona estiver pendente
            Usuario passageiro = solicitacao.getPassageiro();
            Carona carona = solicitacao.getCarona();
            carona.novoPassageiro(passageiro);
            AdminController.novaSolicitacaoAceita(solicitacao);
            return ok(Json.toJson(solicitacao));

        } else {
            return badRequest("Você já tomou uma decisão sobre esse pedido");
        }

    }


    public Result rejeitarCarona(){                       
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);

        if (solicitacao.setStatus(Status.REJEITADO)){   
            AdminController.novaSolicitacaoRejeitada(solicitacao);                       // so pode recusar se a carona estiver pendente
            return ok(Json.toJson(solicitacao));
        } else {
            return badRequest("Você já tomou uma decisão sobre esse pedido");
        }
        
    }



}