package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mafra on 15/04/16.
 * Singleton Pattern
 */

public class SistemaCaronas{

	private List<Carona> caronas;
	private static SistemaCaronas sistemaCaronas;

	private SistemaCaronas (){
		caronas = new ArrayList<Carona>();
		//criaCaronas();	
	}

	private void criaCaronas(){
		/*Horario horario = new Horario("8-10", DiaDaSemana.SEGUNDA, TipoCarona.IDA);
		Horario horario2 = new Horario("10-12", DiaDaSemana.SEGUNDA, TipoCarona.VOLTA);
		Horario horario3 = new Horario("8-10", DiaDaSemana.TERCA, TipoCarona.IDA);
		Horario horario4 = new Horario("14-16", DiaDaSemana.TERCA, TipoCarona.VOLTA);
		Horario horario5 = new Horario("14-16", DiaDaSemana.QUARTA, TipoCarona.IDA);
		Horario horario6 = new Horario("16-18", DiaDaSemana.QUARTA, TipoCarona.VOLTA);
		Horario horario7 = new Horario("8-10", DiaDaSemana.QUARTA, TipoCarona.IDA);
		Horario horario8 = new Horario("14-16", DiaDaSemana.QUARTA, TipoCarona.VOLTA);
		Horario horario9 = new Horario("14-16", DiaDaSemana.SEXTA, TipoCarona.IDA);
		Horario horario10 = new Horario("14-16", DiaDaSemana.SEXTA, TipoCarona.IDA);
		List<String> rota0 = new ArrayList<String>();
		List<String> rota1 = new ArrayList<String>();
		rota1.add("Centro");
		rota1.add("Prata");
		List<String> rota2 = new ArrayList<String>();
		rota1.add("Catolé");
		rota1.add("Centro");						
		Carona carona = new Carona(SistemaUsuarios.getUsuarios().get(0), horario, 4, rota1);
		Carona carona2 = new Carona(SistemaUsuarios.getUsuarios().get(3), horario2, 4, rota2);
		Carona carona3 = new Carona(SistemaUsuarios.getUsuarios().get(4), horario3, 3, rota2);
		Carona carona4 = new Carona(SistemaUsuarios.getUsuarios().get(2), horario4, 1, rota0);
		Carona carona5 = new Carona(SistemaUsuarios.getUsuarios().get(0), horario5, 2, rota1);
		Carona carona6 = new Carona(SistemaUsuarios.getUsuarios().get(6), horario6, 4, rota0);
		Carona carona7 = new Carona(SistemaUsuarios.getUsuarios().get(7), horario7, 3, rota0);
		Carona carona8 = new Carona(SistemaUsuarios.getUsuarios().get(8), horario8, 3, rota0);
		SistemaCaronas.adicionarCarona(carona);		
		SistemaCaronas.adicionarCarona(carona2);		
		SistemaCaronas.adicionarCarona(carona3);		
		SistemaCaronas.adicionarCarona(carona4);		
		SistemaCaronas.adicionarCarona(carona5);		
		SistemaCaronas.adicionarCarona(carona6);		
		SistemaCaronas.adicionarCarona(carona7);*/
	}

	public static SistemaCaronas getInstance(){
		if (sistemaCaronas == null){
			sistemaCaronas = new SistemaCaronas();
		}
		return sistemaCaronas;
	}

	public List<Carona> getCaronas(){
		return caronas;
	}

	public void adicionarCarona(Carona c){
		caronas.add(c);
	}

	public void removerCarona(Carona c) {
		caronas.remove(c);
	}


}