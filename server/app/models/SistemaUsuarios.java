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

	private void testeExemplos(){
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
		Usuario usuario = new Usuario("Pedro", "110112342", "99621653", "pedro@hotmail.com", "123", endereco);
		Usuario usuario2 = new Usuario("Joao", "110233124", "99125444", "joao@hotmail.com", "1234", endereco2);
		Usuario usuario3 = new Usuario("Lucas", "112982983", "87221932", "lucas@hotmail.com", "1234", endereco3);
		Usuario usuario4 = new Usuario("Rafael", "114110423", "9861286", "rafael@hotmail.com", "12345", endereco4);
		Usuario usuario5 = new Usuario("Katia", "111232342", "99621653", "katia@hotmail.com", "123", endereco);
		Usuario usuario6 = new Usuario("Tiago", "111502932", "99621655", "tiago@hotmail.com", "123", endereco);
		Usuario usuario7 = new Usuario("Renata", "114112321", "99621657", "renata@hotmail.com", "123", endereco);
		Usuario usuario8 = new Usuario("Jessica", "114110447", "99621658", "jessica@hotmail.com", "123", endereco);
		Usuario usuario9 = new Usuario("Paula", "114978221", "99621623", "paula@hotmail.com", "123", endereco);		
		Usuario usuario10 = new Usuario("Paulo", "115986223", "99621343", "paulo@hotmail.com", "123", endereco);
		usuarios.add(usuario);
		usuarios.add(usuario2);
		usuarios.add(usuario3);
		usuarios.add(usuario5);	
		usuarios.add(usuario6);	
		usuarios.add(usuario7);	
		usuarios.add(usuario8);	
		usuarios.add(usuario9);	
		usuarios.add(usuario10);		
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