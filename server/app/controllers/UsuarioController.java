package controllers;


import exception.FotoInvalidaException;
import models.*;
import play.libs.Json;
import play.mvc.*;
import services.ServiceLog;
import database.*;
import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.*;
import static play.data.Form.form;



public class UsuarioController extends Controller {

    private SistemaUsuarios sistemaUsuarios = SistemaUsuarios.getInstance();
    private SistemaCaronas sistemaCaronas = SistemaCaronas.getInstance();

    public Result getAllCadastro(){
        List<Usuario> usuarios = Usuario.find.findList();
        return ok(toJson(usuarios));
    }

    public Result logout() {
        Usuario usuarioAtual = usuarioAutenticado();
        session().clear();
        ServiceLog.novaMensagemLog(usuarioAtual.getEmail() + " saiu do sistema");
        return ok("Logged out successfully");
    }

    public static Usuario usuarioAutenticado() {
        if(session().get("logado") == null) {
            return null;
        } else {
            Usuario templateUsuario = new Usuario();
            templateUsuario.setEmail(session().get("logado"));
            int index = SistemaUsuarios.getInstance().recuperarPosicaoDoUsuario(templateUsuario);
            Usuario usuario = SistemaUsuarios.getInstance().recuperarUsuarioPelaPosicao(index);
            return usuario;
        }
    }

    public Result getCaronasComoPassageiro(){
        List<Carona> caronasComoPassageiro = new ArrayList<Carona>();
        Usuario usuarioAtual = UsuarioController.usuarioAutenticado();
        List<Carona> allCaronas = SistemaCaronas.getInstance().getCaronas();
        System.out.println(allCaronas);

        for(Carona carona : allCaronas){
            System.out.println(carona.getListaPassageiros());
            for(Usuario passageiro: carona.getListaPassageiros()){
                if(usuarioAtual.equals(passageiro)){
                    Usuario newMotorista = new Usuario(carona.getMotorista().getEmail(), carona.getMotorista().getTelefone());
                    Carona newCarona = new Carona(carona.getHorario(),carona.getVagas(), newMotorista, carona.getTipo());
                    caronasComoPassageiro.add(newCarona);
                }
            }
        }
        return ok(Json.toJson(caronasComoPassageiro));
    }

    public Result getCaronasComoMotoristas(){
        List<Carona> caronasComoMotorista = new ArrayList<Carona>();
        List<Carona> allCaronas = SistemaCaronas.getInstance().getCaronas();

        System.out.println(allCaronas);


        for (Carona carona : allCaronas) {
                System.out.println(carona.getMotorista());
                /*
                System.out.println(carona.getTipo());
                System.out.println(carona.getHorario().getDia());
                System.out.println(carona.getVagas());
                */
                System.out.println("----------------------------------------");
            if (UsuarioController.usuarioAutenticado().equals(carona.getMotorista())) {
                System.out.println("Adicionei carona");
                caronasComoMotorista.add(carona);
            }
        }

        if (!caronasComoMotorista.isEmpty()) {
            System.out.println(caronasComoMotorista.get(0).getTipo());
            System.out.println(caronasComoMotorista.get(0).getHorario().getAula());
            System.out.println(caronasComoMotorista.get(0).getHorario().getDia());
            System.out.println(caronasComoMotorista.get(0).getListaPassageiros().size());
            System.out.println(caronasComoMotorista.get(0).getVagas());

        }
        
        return ok(Json.toJson(caronasComoMotorista));
    }






}



    