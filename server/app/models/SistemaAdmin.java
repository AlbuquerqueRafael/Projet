package models;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import play.Logger;
import controllers.AdminController;
import play.Logger.ALogger;


public class SistemaAdmin extends Model {
	Endereco endereco = new Endereco("Rua da Flores", "Centro");
	Usuario usuario = new Usuario("admin", "12345678", "99621653", "admin@caroname.com", "123", endereco);

	private static final ALogger logger = Logger.of(AdminController.class);

	public SistemaAdmin(){

	}

	public static novaAcao(String mensagem){
		logger.info(mensagem);
		salva(mensagem);
	}


	private void salva(String mensagem) throws IOException {
		FileWriter arquivo = new FileWriter("public/logs/logAdmin.txt", true);
		PrintWriter writer = new PrintWriter(arquivo);
		
		writer.println(mensagem);
		
		arquivo.close();
	} 











}