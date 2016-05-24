package controllers;

import models.Autenticador;
import models.Usuario;

import play.libs.Json;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import services.ServiceLog;

import models.Usuario;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import java.util.Calendar;

/**
 * Created by rafael on 22/05/16.
 */
public class Secured extends Security.Authenticator {

    public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public final static int tempoLimite = 1000;

    @Override
    public String getUsername(Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get(AUTH_TOKEN_HEADER);
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            Usuario user = findByAuthToken(authTokenHeaderValues[0]);
            if (user != null) {
                ctx.args.put("user", user);
                return user.getEmail();
            }else{
                onUnauthorized(ctx);
            }
        }

        return null;
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return unauthorized();
    }

    public Usuario findByAuthToken(String authToken){
        if (authToken == null) {
            return null;
        }

        try {
            Autenticador auth = Autenticador.find.where().eq("authToken", authToken).findUnique();
            calculaTempo(auth);
            return Usuario.find.byId(auth.getId_Usuario());
        } catch (Exception e) {
            return null;
        }
    }


    private void calculaTempo(Autenticador auth){
        Calendar cal = Calendar.getInstance();
        Calendar inicioToken = Calendar.getInstance();
        inicioToken.setTime(auth.getInicio());
        Long tempo = (cal.getTimeInMillis() - inicioToken.getTimeInMillis())/1000;
        System.out.println(tempo);
        if(tempo > tempoLimite){
            Usuario usuarioAtual = Usuario.find.byId(auth.getId_Usuario());
            ServiceLog.novaMensagemLog(usuarioAtual.getEmail() + " foi deslogado pois seu token expirou");
            auth.delete();
        }
    }
}

