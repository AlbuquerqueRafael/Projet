package sistemasInfo;

import models.Carona;

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