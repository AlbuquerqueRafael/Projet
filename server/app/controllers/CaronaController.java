package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.libs.Json;
import play.mvc.*;
import services.*;
import database.*;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;


@Security.Authenticated(Secured.class)
public class CaronaController extends Controller {

    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);

        Usuario motorista = AutenticacaoController.usuarioAutenticado();
        carona.setMotorista(motorista);


        SistemaCaronas.getInstance().adicionarCarona(carona);

        ServiceLog.novaMensagemLog(motorista.getEmail() + " acabou de cadastrar uma nova carona");


        return ok("Carona cadastrada com sucesso!");
    }


    public Result rejeitarCarona(Long id){
        Solicitacao solicitacao = SistemaSolicitacao.getInstance().getSolitacaoById(id);
        List<Solicitacao> solicitacoes = SistemaSolicitacao.getInstance().getSolicitacao();
        solicitacao.getCarona().setMotorista(AutenticacaoController.usuarioAutenticado());

        Carona carona = solicitacao.getCarona();
        ServiceLog.novaMensagemLog(carona.getMotorista().getEmail() + " rejeitou pedido de carona de " + solicitacao.getPassageiro().getEmail());
        ServiceNotificacao.notificaPassageiroRecusado(solicitacao);
        SistemaUsuarios.getInstance().atualizarUsuario(solicitacao.getPassageiro());
        SistemaSolicitacao.getInstance().removerSolicitacao(solicitacao);

        return ok("Carona rejeitada");

    }







}