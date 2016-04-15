package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mafra on 15/04/16.
 * Singleton Pattern
 */

public class SistemaCaronas extends Model{

	private List<Carona> caronas;
	private SistemaCaronas sistemaCaronas;


	public SistemaCaronas (){
		caronas = new ArrayList<Carona>();
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