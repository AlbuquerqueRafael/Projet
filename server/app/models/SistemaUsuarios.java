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
		Endereco endereco = new Endereco("Rua da Flores", "Centro");
		Usuario usuario = new Usuario("Pedro", "123", "456", "Pedro@hotmail.com", "123", endereco);
		usuarios.add(usuario);
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