package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.libs.Json;
import play.mvc.*;
import sistemasInfo.SistemaCaronas;
import sistemasInfo.SistemaSolicitacao;
import util.Util;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;



public class BuscaController extends Controller {

    public static final int NUM_ITENS_PAGINA = 6;

    /* Realiza a busca baseada nos criterios recebidos. Retorna apenas
    alguns resultados com o objetivo de otimizar a busca. Outros resultados sao mostrados em outras paginas
    */
    public Result buscarCarona(Long id){
        JsonNode json = request().body().asJson();
        Carona carona = Json.fromJson(json, Carona.class);
        carona.setMotorista(UsuarioController.usuarioAutenticado());
        List<Carona> caronasUsuario = new ArrayList<Carona>();
        List<Carona> caronasFiltradas = new ArrayList<Carona>();

        separaCaronas(caronasUsuario, caronasFiltradas);


        if(verHorarioPreenchidoUsuario(caronasUsuario, carona)){
            return badRequest("Horário já preenchido");
        }

        int numeroItens = Math.toIntExact(id-1) * NUM_ITENS_PAGINA;
        int count = numeroItens;
        boolean fimDaLista = false;

        int limite = caronasFiltradas.size();
        List<Carona> infoCaronas = new ArrayList<Carona>();
        Carona caroItera = new Carona();
        int quantElementosLista = infoCaronas.size();

        try {
            caroItera = caronasFiltradas.get(count);
        }catch(Exception e){
            return badRequest("Carona não encontrada");
        }

        while(caroItera != null && quantElementosLista < NUM_ITENS_PAGINA && !fimDaLista){
            if(!Util.isValidHorarioCarona(caroItera, carona) && Util.isEnderecoCompativel(caroItera, carona)
                      && caroItera.getTipo().equals(carona.getTipo()) && caroItera.getVagas() > 0){

                  Usuario motoristaCarona = caroItera.getMotorista();
                  Horario horarioCarona = caroItera.getHorario();

                  Usuario templateMotorista = new Usuario(motoristaCarona.getNome(), motoristaCarona.getEmail(), motoristaCarona.getTelefone());
                  Carona templateCarona = new Carona(horarioCarona, templateMotorista);

                  infoCaronas.add(templateCarona);

           }

            quantElementosLista = infoCaronas.size();
            if (++count < limite) {
                caroItera = caronasFiltradas.get(count);
            } else {
                fimDaLista = true;
            }
        }



        if(infoCaronas.size() == 0){
            return badRequest("Não há caronas para este horário");
        }
        return ok(Json.toJson(infoCaronas));
    }


    private void separaCaronas(List<Carona> caronasUsuario, List<Carona> caronasFiltradas){
        List<Carona> caronasGerais = SistemaCaronas.getInstance().getCaronas();
        boolean UserAutPertenceCarona= false;
        boolean solicitacaoJaFeita = false;
        for(Carona carona : caronasGerais){
            if(UsuarioController.usuarioAutenticado().equals(carona.getMotorista())){
                caronasUsuario.add(carona);
                UserAutPertenceCarona = true;
            }else{
                for(Usuario passageiro :carona.getListaPassageiros()){
                    if(UsuarioController.usuarioAutenticado().equals(passageiro)){
                        caronasUsuario.add(carona);
                        UserAutPertenceCarona = true;
                    }
                }
            }

            if(isRequestAlreadyMade(carona)){
                solicitacaoJaFeita = true;
            }

            if(!UserAutPertenceCarona && !solicitacaoJaFeita){
                caronasFiltradas.add(carona);
            }

            UserAutPertenceCarona = false;
            solicitacaoJaFeita = false;
        }

    }


    private boolean verHorarioPreenchidoUsuario(List<Carona> caronas, Carona carona){
        for(int i = 0; i < caronas.size(); i++){
            if(!Util.isValidHorarioCarona(carona, caronas.get(i))){
                return true;
            }
        }

        return false;
    }


    private boolean isRequestAlreadyMade(Carona carona){
        List<Solicitacao> solicitacoes = SistemaSolicitacao.getInstance().getSolicitacao();
        Solicitacao solicitacao = new Solicitacao(carona, UsuarioController.usuarioAutenticado());

        for(Solicitacao s : solicitacoes){
            if(s.getCarona().equals(carona) && s.equals(solicitacao)){
                return true;
            }
        }

        return false;
    }





}