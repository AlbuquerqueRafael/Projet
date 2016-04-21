package controllers;


import models.*;
import play.libs.Json;
import play.mvc.*;
import sistemaInfo.SistemaCaronas;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;



public class MotoristaController extends Controller {
    

     public Result getCaronasComoMotoristas(){
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();
        for(Carona c : SistemaCaronas.getInstance().getCaronas()){
            if(UsuarioController.usuarioAutenticado().equals(c.getMotorista())){
                caronasComoMotorista.add(c);
            }
        }


        return ok(Json.toJson(caronasComoMotorista));
    }





}