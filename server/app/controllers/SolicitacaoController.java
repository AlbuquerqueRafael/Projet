package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Carona;
import models.SistemaSolicitacao;
import models.Solicitacao;
import play.Logger;
import play.libs.Json;
import play.mvc.*;

/**
 * Created by rafael on 21/04/16.
 */
public class SolicitacaoController extends Controller{

    public Result solicitarCarona(){
        JsonNode json = request().body().asJson();

        Carona caronaSolicitada = Json.fromJson(json, Carona.class);
        Solicitacao solicitacao = new Solicitacao(caronaSolicitada, UsuarioController.usuarioAutenticado());

        SistemaSolicitacao.getInstance().adicionarSolicitacao(solicitacao);

        Logger.info(solicitacao.getPassageiro().getEmail() + " acabou de solicitar uma carona a " + solicitacao.getCarona().getMotorista().getEmail());

        return ok(Json.toJson("Solicitação concluida!"));

    }
}
