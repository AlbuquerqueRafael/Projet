package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 19/04/16.
 */
public class SistemaSolicitacao {
    private List<Solicitacao> solicitacoes;
    private static SistemaSolicitacao sistemaSolicitacao;

    private SistemaSolicitacao (){
        solicitacoes = new ArrayList<Solicitacao>();

    }

    public static SistemaSolicitacao getInstance(){
        if (sistemaSolicitacao == null){
            sistemaSolicitacao = new SistemaSolicitacao();
        }
        return sistemaSolicitacao;
    }

    public List<Solicitacao> getSolicitacao(){
        return solicitacoes;
    }

    public void adicionarSolicitacao(Solicitacao c){
        solicitacoes.add(c);
    }

    public void removerSolicitacao(Solicitacao c) {
        solicitacoes.remove(c);
    }



}

