package database;

import models.*;
import models.enums.TipoCarona;
import exception.*;
import java.util.ArrayList;
import java.util.List;
import util.Util;
import models.enums.DiaDaSemana;
import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;

/**
 * Created by Mafra on 15/04/16.
 * Singleton Pattern
 */

public class SistemaUsuarios{

	private static SistemaUsuarios sistemaUsuarios;

	private SistemaUsuarios (){
		//testeExemplos();
	}

	private void testeExemplos() {
		Endereco endereco = new Endereco("Rua da Flores", "Centro");
		Endereco endereco2 = new Endereco("Rua Floriano Peixoto", "Centro");
		Endereco endereco3 = new Endereco("Rua Emiliano Rosendo da Silva", "Novo Bondocongó");

		Usuario usuario = new Usuario("Pedro", "123", "99621653", "pedro@hotmail.com", "123", endereco);
		Usuario usuario2 = new Usuario("Joao", "123", "99125444", "joao@hotmail.com", "1234", endereco2);
		Usuario usuario3 = new Usuario("Lucas", "123", "87221932", "lucas@hotmail.com", "1234", endereco3);
		
		usuario.save();
		usuario2.save();
		usuario3.save();

		Horario horario = new Horario("8-10", DiaDaSemana.SEGUNDA);
		Horario horario2 = new Horario("10-12", DiaDaSemana.SEGUNDA);
		Horario horario3 = new Horario("14-16", DiaDaSemana.SEXTA);

		List<Rota> rota1 = new ArrayList<Rota>();

		rota1.add(new Rota("Jardim Paulistano"));
		rota1.add(new Rota("Prata"));
		rota1.add(new Rota("Catole"));
		rota1.add(new Rota("Centro"));

		Carona carona = new Carona(usuario, horario, 4, rota1, endereco, TipoCarona.IDA);
		Carona carona2 = new Carona(usuario2, horario2, 4, rota1, endereco2, TipoCarona.IDA);
		Carona carona3 = new Carona(usuario3, horario3, 4, rota1, endereco3, TipoCarona.VOLTA);
		Carona carona4 = new Carona(usuario, horario2, 3, rota1, endereco, TipoCarona.IDA);

		
		SistemaCaronas.getInstance().adicionarCarona(carona);
		SistemaCaronas.getInstance().adicionarCarona(carona2);
		SistemaCaronas.getInstance().adicionarCarona(carona3);


		Solicitacao solicitacao = new Solicitacao(carona, usuario3);
		Solicitacao solicitacao2 = new Solicitacao(carona, usuario2);
		Solicitacao solicitacao3 = new Solicitacao(carona3, usuario);

		SistemaSolicitacao.getInstance().adicionarSolicitacao(solicitacao);
		SistemaSolicitacao.getInstance().adicionarSolicitacao(solicitacao2);
		SistemaSolicitacao.getInstance().adicionarSolicitacao(solicitacao3);
		
	}

	public static SistemaUsuarios getInstance(){
		if (sistemaUsuarios == null){
			sistemaUsuarios = new SistemaUsuarios();
		}
		return sistemaUsuarios;
	}

	public List<Usuario> getUsuarios(){
		return Usuario.find.findList();
	}

	public void adicionarUsuario(Usuario usr) throws DadosInvalidosException{
		
		
		if(!Util.isValidEmailAddress(usr.getEmail())){
            throw new EmailInvalidoException("Email inválido");
        }
        
        if(!Util.isValidMatricula(usr.getMatricula())){
            throw new MatriculaInvalidaException("Matricula inválida");
        }

        if(!Util.isValidPassword(usr.getSenha())){
            throw new SenhaInvalidaException("Senha inválida");
        }
        
        if(!Util.isValidTelefone(usr.getTelefone())){
            throw new TelefoneInvalidoException("Telefone inválido");
        }
        

        if (isMatriculaRepetida(usr)){
        	throw new MatriculaRepetidaException("Matrícula repetida");
        }

        if (isEmailRepetido(usr)){
        	throw new EmailRepetidoException("E-mail repetido");
        }

		if(usr.getEmail() == null){
			throw new EmailRepetidoException("Informe um email valido");
		}

		if(usr.getEndereco().getBairro() == null){
			throw new BairroInvalidoException("Informe um bairro válido");
		}

		usr.save();
	}

	public void removerUsuario(Usuario usr) {
		usr.delete();
	}

	public void atualizarUsuario(Usuario usr) {
		usr.update();
	}

	public Usuario recuperarUsuarioPelaPosicao(int index){
		return getUsuarios().get(index);
	}

	public int recuperarPosicaoDoUsuario(Usuario usuario){
		return getUsuarios().indexOf(usuario);
	}

	private boolean isEmailRepetido(Usuario usuario){
		for (Usuario usr: getUsuarios()){
			try {
				if (usr.getEmail().equals(usuario.getEmail())) {
					return true;
				}	
			} catch (NullPointerException e){
				return true;
			}
			
		}

		return false;

	}

	private boolean isMatriculaRepetida(Usuario usuario){
		for (Usuario usr: getUsuarios()){
			try {
				if (usr.getMatricula().equals(usuario.getMatricula())) {
					return true;
				}	
			} catch (NullPointerException e){
				return true;
			}
			
		}

		return false;
	}


}