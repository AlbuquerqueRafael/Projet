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
		return Carona.find.findList();
	}

	public void adicionarCarona(Carona carona){
		carona.save();
	}

	public void removerCarona(Carona carona) {
		carona.delete();
	}

	public void atualizarCarona(Carona carona) {
		carona.update();
	}


}