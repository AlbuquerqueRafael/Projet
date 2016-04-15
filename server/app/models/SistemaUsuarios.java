package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mafra on 15/04/16.
 * Singleton Pattern
 */

public class SistemaUsuarios extends Model{

	private List<Usuario> usuarios;
	private SistemaUsuarios sistemaUsuarios;


	public SistemaUsuarios (){
		usuarios = new ArrayList<Usuario>();
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