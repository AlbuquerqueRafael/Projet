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



public class PassageiroController extends Controller {


    public Result getCaronasComoPassageiro(){
        List<Carona> caronasComoPassageiro = new ArrayList<Carona>();
        for(Carona c : SistemaCaronas.getInstance().getCaronas()){
            for(Usuario s: c.getListaPassageiros()){
                if(UsuarioController.usuarioAutenticado().equals(s)){
                    Carona carona = new Carona();
                    Horario horarioCarona = c.getHorario();
                    TipoCarona tipo = c.getTipo();
                    int vagas = c.getVagas();
                    String rua = c.getEndereco().getRua();
                    caronasComoPassageiro.add(c);
                }
            }

            System.out.println(Json.toJson(c));

        }



        return ok(Json.toJson(caronasComoPassageiro));
    }




}