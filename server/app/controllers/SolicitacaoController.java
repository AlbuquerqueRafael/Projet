package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import models.enums.TipoCarona;
import play.libs.Json;
import play.mvc.*;
import sistemasInfo.SistemaCaronas;
import services.*;
import sistemasInfo.SistemaSolicitacao;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 21/04/16.
 */
public class SolicitacaoController extends Controller{

    public static final int NUM_ITENS_PAGINA = 3;

    public Result solicitarCarona(){
        JsonNode json = request().body().asJson();
        Carona caronaSolicitada = Json.fromJson(json, Carona.class);

        for(Carona carona : SistemaCaronas.getInstance().getCaronas()){
            if(caronaSolicitada.equals(carona)){
                caronaSolicitada = carona;
            }
        }

        Solicitacao solicitacao = new Solicitacao(caronaSolicitada, UsuarioController.usuarioAutenticado());

        SistemaSolicitacao.getInstance().adicionarSolicitacao(solicitacao);

        ServiceLog.novaMensagemLog(solicitacao.getPassageiro().getEmail() + " solicitou uma carona a " + solicitacao.getCarona().getMotorista().getEmail());
        ServiceNotificacao.notificaMotoristaPedido(solicitacao);

        return ok(Json.toJson("Solicitação concluida!"));

    }

    public Result aceitarCarona(){
        JsonNode json = request().body().asJson();
        Solicitacao solicitacao = Json.fromJson(json, Solicitacao.class);
        List<Solicitacao> solicitacoes = SistemaSolicitacao.getInstance().getSolicitacao();
        solicitacao.getCarona().setMotorista(UsuarioController.usuarioAutenticado());
        String telefone = null;

        System.out.println(Json.toJson(solicitacao));
        for(Solicitacao sol: solicitacoes){
            Carona carona = sol.getCarona();
            if(sol.equals(solicitacao) && solicitacao.getCarona().equals(carona)){
                int vagas = solicitacao.getCarona().getVagas();

                carona.novoPassageiro(solicitacao.getPassageiro());
                carona.setVagas(--vagas);                                 //ATUALIZA CARONA
                SistemaCaronas.getInstance().atualizarCarona(carona);

                telefone = sol.getPassageiro().getTelefone();
                if(vagas == 0) {
                    limpaSolicitacoesSemVagas(sol.getCarona());
                }
                SistemaSolicitacao.getInstance().removerSolicitacao(sol);
                ServiceLog.novaMensagemLog(carona.getMotorista().getEmail() + " aceitou o pedido de carona de " + sol.getPassageiro().getEmail());
                ServiceNotificacao.notificaPassageiroAceito(sol);

                break;
            }
        }


        return ok(Json.toJson(telefone));

    }


    /* Assim como o metodo de busca, pega apenas parte das solicitacoes, usando paginacao para passar por todas as solicitacoes
    */
    public Result getSolicitacoesCaronas(Long id){
        List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
        for(Solicitacao pedido : SistemaSolicitacao.getInstance().getSolicitacao()){
            if(UsuarioController.usuarioAutenticado().equals(pedido.getCarona().getMotorista())){
                solicitacoes.add(pedido);
            }
        }

        List<Solicitacao> filterPassageiros = new ArrayList<Solicitacao>();
        int numeroItens = Math.toIntExact(id-1) * 3;
        int count = numeroItens;
        int limite = solicitacoes.size();
        Solicitacao solicitacao = new Solicitacao();
        boolean fimDaLista = false;
        int quantElementosLista = filterPassageiros.size();

        try{
            solicitacao = solicitacoes.get(count);
        }catch(IndexOutOfBoundsException e){
            return badRequest("Sem solicitacoes");
        }

        while(solicitacao != null && quantElementosLista < NUM_ITENS_PAGINA && !fimDaLista){
               
                Usuario usuario = new Usuario(solicitacao.getPassageiro().getNome(), solicitacao.getPassageiro().getEmail(), solicitacao.getPassageiro().getTelefone());

                Horario horarioSolicitacao = solicitacao.getCarona().getHorario();
                TipoCarona tipo = solicitacao.getCarona().getTipo();
                Endereco endereco = solicitacao.getCarona().getEndereco();
                int vagas = solicitacao.getCarona().getVagas();

                Carona carona = new Carona(horarioSolicitacao, tipo, endereco, vagas);

                Solicitacao sol = new Solicitacao(carona, usuario);

                filterPassageiros.add(sol);
            

            quantElementosLista = filterPassageiros.size();
            if (++count < limite) {
                solicitacao = solicitacoes.get(count);
            } else {
                fimDaLista = true;
            }


        }

        return ok(Json.toJson(filterPassageiros));
    }

    private void limpaSolicitacoesSemVagas(Carona carona){
        for(Solicitacao s : SistemaSolicitacao.getInstance().getSolicitacao()){
            if(s.getCarona().equals(carona) && carona.getVagas() == 0){
                ServiceNotificacao.notificaPassageiroRecusado(s);
                SistemaSolicitacao.getInstance().removerSolicitacao(s);

            }
        }
    }
}
