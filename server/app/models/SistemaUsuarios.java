package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mafra on 15/04/16.
 * Singleton Pattern
 */

public class SistemaUsuarios{

	private List<Usuario> usuarios;
	private static SistemaUsuarios sistemaUsuarios;

	private SistemaUsuarios (){
		usuarios = new ArrayList<Usuario>();
		testeExemplos();	
	}

	private void testeExemplos() {
		Endereco endereco = new Endereco("Rua da Flores", "Centro");
		Endereco endereco2 = new Endereco("Rua Floriano Peixoto", "Centro");
		Endereco endereco3 = new Endereco("Rua Emiliano Rosendo da Silva", "Novo Bondocongó");
		Endereco endereco4 = new Endereco("Rua Paulo de Frontin", "Catolé");
		Endereco endereco5 = new Endereco("Rua Cazuza Barreto", "Catolé");
		Endereco endereco6 = new Endereco("Rua Compositor Rosil Cavalcante", "Novo Bondocongó");
		Endereco endereco7 = new Endereco("Rua Dos Marmeleiros", "Malvinas");
		Endereco endereco8 = new Endereco("Rua Dos Pinhões ", "Malvinas");
		Endereco endereco9 = new Endereco("Travessa Mestre Honório", "Alto Branco");
		Endereco endereco10 = new Endereco("Rua Mestre Honório ", "Alto Branco");
		Usuario usuario = new Usuario("Pedro", "123", "99621653", "pedro@hotmail.com", "123", endereco);
		Usuario usuario2 = new Usuario("Joao", "123", "99125444", "joao@hotmail.com", "1234", endereco2);
		Usuario usuario3 = new Usuario("Lucas", "123", "87221932", "lucas@hotmail.com", "1234", endereco3);
		Usuario usuario4 = new Usuario("Rafael", "123", "9861286", "rafael@hotmail.com", "12345", endereco4);
		Usuario usuario5 = new Usuario("Katia", "123", "99621653", "katia@hotmail.com", "123", endereco);
		Usuario usuario6 = new Usuario("Tiago", "123", "99621655", "tiago@hotmail.com", "123", endereco);
		Usuario usuario7 = new Usuario("Renata", "123", "99621657", "renata@hotmail.com", "123", endereco);
		Usuario usuario8 = new Usuario("Jessica", "123", "99621658", "jessica@hotmail.com", "123", endereco);
		Usuario usuario9 = new Usuario("Paula", "123", "99621623", "paula@hotmail.com", "123", endereco);
		Usuario usuario10 = new Usuario("Paulo", "123", "99621343", "paulo@hotmail.com", "123", endereco);
		usuarios.add(usuario);
		usuarios.add(usuario2);
		usuarios.add(usuario3);
		usuarios.add(usuario5);
		usuarios.add(usuario6);
		usuarios.add(usuario7);
		usuarios.add(usuario8);
		usuarios.add(usuario9);
		usuarios.add(usuario10);

		Horario horario = new Horario("8-10", DiaDaSemana.SEGUNDA);
		Horario horario2 = new Horario("10-12", DiaDaSemana.SEGUNDA);
		Horario horario3 = new Horario("8-10", DiaDaSemana.TERCA);
		Horario horario4 = new Horario("14-16", DiaDaSemana.TERCA);
		Horario horario5 = new Horario("14-16", DiaDaSemana.QUARTA);
		Horario horario6 = new Horario("16-18", DiaDaSemana.QUARTA);
		Horario horario7 = new Horario("8-10", DiaDaSemana.QUARTA);
		Horario horario8 = new Horario("14-16", DiaDaSemana.QUARTA);
		Horario horario9 = new Horario("14-16", DiaDaSemana.SEXTA);
		Horario horario10 = new Horario("14-16", DiaDaSemana.SEXTA);
		List<String> rota0 = new ArrayList<String>();
		List<String> rota1 = new ArrayList<String>();
		rota1.add("Centro");
		rota1.add("Prata");
		List<String> rota2 = new ArrayList<String>();
		rota1.add("Catolé");
		rota1.add("Centro");
		Carona carona = new Carona(usuario, horario, 4, rota1, endereco, TipoCarona.IDA);
		Carona carona2 = new Carona(usuario2, horario2, 4, rota2, endereco2, TipoCarona.IDA);
		Carona carona3 = new Carona(usuario3, horario2, 3, rota2, endereco2, TipoCarona.IDA);
		Carona carona4 = new Carona(usuario4, horario2, 1, rota0, endereco2, TipoCarona.IDA);
		Carona carona5 = new Carona(usuario5, horario2, 2, rota1, endereco2, TipoCarona.IDA);
		Carona carona6 = new Carona(usuario, horario, 4, rota0, endereco2, TipoCarona.VOLTA);
		Carona carona7 = new Carona(usuario6, horario2, 3, rota0, endereco2, TipoCarona.IDA);
		Carona carona8 = new Carona(usuario, horario, 3, rota0, endereco2, TipoCarona.IDA);
		Carona carona9 = new Carona(usuario7, horario2, 3, rota0, endereco2, TipoCarona.IDA);
		Carona carona10 = new Carona(usuario10, horario2, 3, rota0, endereco2, TipoCarona.IDA);

		SistemaCaronas.getInstance().adicionarCarona(carona);
		SistemaCaronas.getInstance().adicionarCarona(carona2);
		SistemaCaronas.getInstance().adicionarCarona(carona3);
		SistemaCaronas.getInstance().adicionarCarona(carona4);
		SistemaCaronas.getInstance().adicionarCarona(carona5);
		SistemaCaronas.getInstance().adicionarCarona(carona6);
		SistemaCaronas.getInstance().adicionarCarona(carona7);
		SistemaCaronas.getInstance().adicionarCarona(carona8);
		SistemaCaronas.getInstance().adicionarCarona(carona9);
		SistemaCaronas.getInstance().adicionarCarona(carona10);

		usuario.setCaronasMotorista(carona);
		usuario2.setCaronasMotorista(carona2);
		usuario3.setCaronasMotorista(carona3);
		usuario4.setCaronasMotorista(carona4);
		usuario5.setCaronasMotorista(carona5);
		usuario.setCaronasMotorista(carona6);
		usuario6.setCaronasMotorista(carona7);
		usuario.setCaronasMotorista(carona8);
		usuario7.setCaronasMotorista(carona9);
		usuario10.setCaronasMotorista(carona10);

	}

	public static SistemaUsuarios getInstance(){
		if (sistemaUsuarios == null){
			sistemaUsuarios = new SistemaUsuarios();
		}
		return sistemaUsuarios;
	}

	public List<Usuario> getUsuarios(){
		return usuarios;
	}

	public void adicionarUsuario(Usuario usr){
		usuarios.add(usr);
	}

	public void removerUsuario(Usuario usr) {
		usuarios.remove(usr);
	}

}