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



public class BuscaController extends Controller {


    public Result buscarCarona(Long id){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);
        carona.setMotorista(UsuarioController.usuarioAutenticado());

        if(verificaSeHorarioPreenchido(carona)){
            return badRequest("Horário já preenchido");
        }

        int numeroItens = Math.toIntExact(id-1) * 6;
        int count = numeroItens;

        boolean fimDaLista = false;
        List<Carona> caronas = SistemaCaronas.getInstance().getCaronas();
        int limite = caronas.size();
        List<Usuario> filterMotoristas = new ArrayList<Usuario>();
        Carona caroItera = caronas.get(count);

        while(caroItera != null && count < numeroItens + 6 && !fimDaLista){
           if(!caroItera.getMotorista().equals(UsuarioController.usuarioAutenticado())) {
              if(!Util.isValidHorarioCarona(caroItera, carona) && Util.isEnderecoCompativel(caroItera, carona)
                      && caroItera.getTipo().equals(carona.getTipo())){
                  Usuario usuario = new Usuario();

                  Usuario motoristaCarona = caroItera.getMotorista();
                  usuario.setNome(motoristaCarona.getNome());
                  usuario.setEmail(motoristaCarona.getEmail());
                  usuario.setTelefone(motoristaCarona.getTelefone());
                  Endereco enderecoMotorista = motoristaCarona.getEndereco();
                  usuario.setEndereco(enderecoMotorista);

                  filterMotoristas.add(usuario);
                  numeroItens--;
               }
           }

            numeroItens++;

            if(count < limite) {
                caroItera = caronas.get(count++);
            }else{
                fimDaLista = true;
            }
        }



        if(filterMotoristas.size() == 0){
            return badRequest("Não há caronas para este horário");
        }
        return ok(Json.toJson(filterMotoristas));
    }


    private boolean verificaSeHorarioPreenchido(Carona carona){
        List<Carona> caronasComoPassageiro = new ArrayList<Carona>();
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();

        for(Carona c : SistemaCaronas.getInstance().getCaronas()){
            if(UsuarioController.usuarioAutenticado().equals(c.getMotorista())){
                caronasComoMotorista.add(c);
            }

            for(Usuario passageiros : c.getListaPassageiros()){
                if(UsuarioController.usuarioAutenticado().equals(caronasComoPassageiro)){
                    caronasComoPassageiro.add(c);
                }
            }
        }

        if(verHorarioPreenchidoUsuario(caronasComoPassageiro, carona)){
            return true;
        }

        if(verHorarioPreenchidoUsuario(caronasComoMotorista, carona)){
            return true;
        }
        return false;
    }

    private boolean verHorarioPreenchidoUsuario(List<Carona> caronas, Carona carona){
        for(int i = 0; i < caronas.size(); i++){
            if(!Util.isValidHorarioCarona(carona, caronas.get(i))){
                return true;
            }
        }

        return false;
    }






}