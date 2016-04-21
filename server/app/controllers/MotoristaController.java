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



public class MotoristaController extends Controller {
    

     public Result getCaronasMotoristasUsuario(){
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();
        for(Carona c : SistemaCaronas.getInstance().getCaronas()){
            if(UsuarioController.usuarioAutenticado().equals(c.getMotorista())){
                caronasComoMotorista.add(c);
            }
        }


        return ok(Json.toJson(caronasComoMotorista));
    }





}