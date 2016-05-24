
package controllers;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import database.*;
import services.*;
import play.libs.Json;
import play.mvc.*;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static play.data.Form.form;


public class AutenticacaoController extends Controller{

    private static SistemaUsuarios sistemaUsuarios = SistemaUsuarios.getInstance();
    public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "authToken";

    public  Result postLogin() {
        JsonNode json = request().body().asJson();
        Usuario user = Json.fromJson(json, Usuario.class);
      //  Usuario usuario = Usuario.find.where().eq("email", user.getEmail()).findUnique();
        System.out.println(Json.toJson(user));
        String authToken = "";
        for(Usuario usuario : SistemaUsuarios.getInstance().getUsuarios()) {
            System.out.println(Json.toJson(usuario));
            if(usuario.getEmail().equals(user.getEmail()) && usuario.getSenha().equals(user.getSenha())){
                ServiceLog.novaMensagemLog(usuario.getEmail() + " acabou de logar");
                Autenticador auth = Autenticador.find.where().eq("id_Usuario", usuario.getId()).findUnique();
                if(auth != null){
                    authToken = auth.getAuthToken();
                }else {
                    Timestamp currentTimestamp = createTimeStamp();
                    authToken = createToken();
                    Autenticador autentica = new Autenticador(usuario.getId(), currentTimestamp, authToken);
                    autentica.save();
                }

                return ok(autenticar(authToken));
            }
        }


        ServiceLog.novaMensagemLog(user.getEmail() + " tentou logar, mas possui email ou senha inválidos!");
        return badRequest("Email ou senha inválidos!");
    }


    @Security.Authenticated(Secured.class)
    public Result logout(){
        Usuario usuarioAtual = usuarioAutenticado();
        for(Autenticador a : Autenticador.find.all()){
            if(a.getId_Usuario().equals(usuarioAtual.getId())){
                a.delete();
            }
        }

        ServiceLog.novaMensagemLog(usuarioAtual.getEmail() + " saiu do sistema");
        return ok("Logged out successfully");
    }

    public static Usuario usuarioAutenticado() {
        return (Usuario)Http.Context.current().args.get("user");

    }


    private JsonNode autenticar(String authToken){
        ObjectNode authTokenJson = Json.newObject();
        authTokenJson.put(AUTH_TOKEN, authToken);
        response().setCookie(Http.Cookie.builder(AUTH_TOKEN, authToken).withSecure(ctx().request().secure()).build());
        return authTokenJson;
    }

    private String createToken(){
        String authToken = UUID.randomUUID().toString();
        return authToken;
    }

    private Timestamp createTimeStamp(){
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        return new Timestamp(now.getTime());
    }




}