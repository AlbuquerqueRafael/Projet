package controllers;


import models.*;
import models.enums.TipoCarona;
import play.libs.Json;
import play.mvc.*;
import sistemaInfo.SistemaCaronas;

import java.util.ArrayList;
import java.util.List;

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