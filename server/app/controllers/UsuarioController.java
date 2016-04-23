package controllers;


import exception.FotoInvalidaException;
import models.*;
import play.libs.Json;
import play.mvc.*;
import sistemasInfo.SistemaCaronas;
import sistemasInfo.SistemaLog;
import sistemasInfo.SistemaUsuarios;
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
        List<Usuario> usuarios = SistemaUsuarios.getInstance().getUsuarios();
        return ok(toJson(usuarios));
    }

    public Result logout() {
        Usuario usuarioAtual = usuarioAutenticado();
        session().clear();
        SistemaLog.novaMensagemLog(usuarioAtual.getEmail() + " saiu do sistema");
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

        for(Carona carona : allCaronas){
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
        for (Carona carona : sistemaCaronas.getCaronas()) {
            if (UsuarioController.usuarioAutenticado().equals(carona.getMotorista())) {
                caronasComoMotorista.add(carona);
            }
        }
        return ok(Json.toJson(caronasComoMotorista));
    }






}



    