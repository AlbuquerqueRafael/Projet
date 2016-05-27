package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import exception.IndiceInvalidoException;
import models.*;
import play.libs.Json;
import play.mvc.*;
import services.*;
import database.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 20/05/16.
 */
@Security.Authenticated(Secured.class)
public class NotificacaoController  extends Controller {

        public Result getNotificacoes(){
            Usuario usuario = AutenticacaoController.usuarioAutenticado();

            return ok(Json.toJson(usuario.getNovasNotificacoes()));
        }


        public Result removeNotificacoes(Long index){
            Usuario usuario = AutenticacaoController.usuarioAutenticado();
            int idx =  Math.toIntExact(index);
            try{
                List<Notificacao> notificacoes = usuario.getNovasNotificacoes();
                notificacoes.get(idx).delete();
            }catch(IndiceInvalidoException exception){
                return ok("Indice Inválido");
            }

            return ok(Json.toJson(usuario.getNovasNotificacoes()));
        }
}
