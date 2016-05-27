package database;

import models.Solicitacao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 19/04/16.
 */
public class SistemaSolicitacao {

    private static SistemaSolicitacao sistemaSolicitacao;

    private SistemaSolicitacao (){

    }

    public static SistemaSolicitacao getInstance(){
        if (sistemaSolicitacao == null){
            sistemaSolicitacao = new SistemaSolicitacao();
        }
        return sistemaSolicitacao;
    }

    public List<Solicitacao> getSolicitacao(){
        return Solicitacao.find.findList();    }

    public void adicionarSolicitacao(Solicitacao c){
        c.save();
    }

    public void removerSolicitacao(Solicitacao c) {
        c.delete();
    }

    public Solicitacao getSolitacaoById(Long id) {
        return Solicitacao.find.byId(id);
    }


}

