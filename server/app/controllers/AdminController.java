package controllers;

import models.*;


public class AdminController extends Controller {

	public static novaSolicitacao(Solicitacao solicitacao){
		String mensagem = solicitacao.getPassageiro() + " solicitou carona a " + solicitacao.getCarona().getMotorista();
		SistemaAdmin.novaAcao(mensagem);
	}


	public static novaSolicitacaoAceita(Solicitacao solicitacao){
		String mensagem = solicitacao.getCarona().getMotorista() + " aceitou a solicitação de " + solicitacao.getPassageiro();
		SistemaAdmin.novaAcao(mensagem);
	}

	public static novaSolicitacaoRejeitada(Solicitacao solicitacao){
		String mensagem = solicitacao.getCarona().getMotorista() + " rejeitou a solicitação de " + solicitacao.getPassageiro();
		SistemaAdmin.novaAcao(mensagem);
	}

	public static novaCarona(Carona carona){
		String mensagem = carona.getMotorista() + " cadastrou uma nova carona";
		SistemaAdmin.novaAcao(mensagem);
	}




}