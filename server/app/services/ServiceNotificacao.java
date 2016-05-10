package services;

import models.*;


public class ServiceNotificacao {


	 public static void notificaMotoristaPedido(Solicitacao solicitacaoEnviada){
	 	Usuario motorista = solicitacaoEnviada.getCarona().getMotorista();
	 	Usuario passageiro = solicitacaoEnviada.getPassageiro();
	 	String message = passageiro.getNome() + " solicitou carona a você. Acesse SOLICITAÇÕES para aceitar ou recusar";
	 	motorista.adicionaNotificacao(message);
	 }

	 public static void excluiNotificacaoLida(Usuario user, String message){
	 	user.removeNotificacao(message);
	 }

	 public static void notificaPassageiroAceito(Solicitacao solicitacaoAceita){
	 	Usuario motorista = solicitacaoAceita.getCarona().getMotorista();
	 	Usuario passageiro = solicitacaoAceita.getPassageiro();
	 	String message = motorista.getNome() + " aceitou seu pedido de carona. Acesse HORÁRIO na aba Passageiro e saiba mais";
	 	System.out.println(passageiro);
	 	passageiro.adicionaNotificacao(message);
	 }

	 public static void notificaPassageiroRecusado(Solicitacao solicitacaoRecusada){
	 	Carona carona = solicitacaoRecusada.getCarona();
	 	Horario horario = carona.getHorario();
	 	Usuario motorista = carona.getMotorista();
	 	Usuario passageiro = solicitacaoRecusada.getPassageiro();

	 	String message = motorista.getNome() + " recusou seu pedido de carona para " + horario.getDia() + " AULA: " + horario.getAula() + " TIPO: " + carona.getTipo();

	 	passageiro.adicionaNotificacao(message);

	 }

}