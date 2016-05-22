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



public class CaronaController extends Controller {

    public Result postCaronas(){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);

        Usuario motorista = UsuarioController.usuarioAutenticado();
        carona.setMotorista(motorista);


        SistemaCaronas.getInstance().adicionarCarona(carona);

        ServiceLog.novaMensagemLog(motorista.getEmail() + " acabou de cadastrar uma nova carona");


        return ok("Carona cadastrada com sucesso!");
    }


    public Result rejeitarCarona(Long id){
        Solicitacao solicitacao = SistemaSolicitacao.getInstance().geSolitacaoById(id);
        List<Solicitacao> solicitacoes = SistemaSolicitacao.getInstance().getSolicitacao();
        solicitacao.getCarona().setMotorista(UsuarioController.usuarioAutenticado());

        Carona carona = solicitacao.getCarona();
        ServiceLog.novaMensagemLog(carona.getMotorista().getEmail() + " rejeitou pedido de carona de " + solicitacao.getPassageiro().getEmail());
        ServiceNotificacao.notificaPassageiroRecusado(solicitacao);
        SistemaUsuarios.getInstance().atualizarUsuario(solicitacao.getPassageiro());
        SistemaSolicitacao.getInstance().removerSolicitacao(solicitacao);

        return ok("Carona rejeitada");

    }

    /*
    public Result caronasRejeitadas(){
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setPassageiro(UsuarioController.usuarioAutenticado());
        List<Solicitacao> solicitacoes = SistemaSolicitacao.getInstance().getSolicitacao();
        List<String> mensagens = new ArrayList<String>();

        for(int i = 0; i < solicitacoes.size(); i++){
            String mensagem = "";
            if(solicitacoes.get(i).equals(solicitacao)){
                Carona  carona = solicitacoes.get(i).getCarona();
                Usuario motorista = carona.getMotorista();
                String motoristaNome = motorista.getNome();
                mensagem = motoristaNome + " rejeitou seu pedido de carona para " + carona.getHorario().getDia() + ": "
                + carona.getHorario().getAula();
                mensagens.add(0, mensagem);
            }
        }

        return ok(Json.toJson(mensagens));
    }*/





}