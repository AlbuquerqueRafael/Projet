package controllers;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import models.enums.TipoCarona;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import util.Util;
import views.html.index;
import views.html.main;
import database.*;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import models.enums.DiaDaSemana;
import static play.libs.Json.*;
import static play.data.Form.form;

public class Application extends Controller {



    public Result main(String any) {
    	PropertyConfigurator.configure("conf/log4j.properties");  // NAO EXCLUIR

        List<Usuario> allusers = SistemaUsuarios.getInstance().getUsuarios();
        List<Carona> allCaronas = SistemaCaronas.getInstance().getCaronas();

/*
        List<Usuario> allusers = SistemaUsuarios.getInstance().getUsuarios();
        for (Usuario c: allusers) {
            c.delete();
        }
*/

        Endereco endereco = new Endereco("Rua da Flores", "Centro");
        Endereco endereco1 = new Endereco("Rua da Flores", "Centro");
        Notificacao notifica = new Notificacao("Voce foi notificado");
        Usuario usuario = new Usuario("Pedro", "123", "99621653", "pedro@hotmail.com", "123", endereco);
        List<Notificacao> notificacaos = new ArrayList<>();
        notificacaos.add(new Notificacao("nossa"));
        notificacaos.add(new Notificacao("que isso novinha"));
        usuario.setNovasNotificacoes(notificacaos);
        usuario.save();


        List<Rota> rota1 = new ArrayList<Rota>();
        rota1.add(new Rota("Centro"));
        rota1.add(new Rota("Prata"));
        Horario horario = new Horario("8-10", DiaDaSemana.SEGUNDA);
        Horario horario1 = new Horario("8-10", DiaDaSemana.SEGUNDA);
        Carona carona = new Carona(usuario, horario, 2, rota1, endereco, TipoCarona.IDA);
        Carona carona1 = new Carona(usuario, horario1, 5, rota1, endereco1, TipoCarona.IDA);

        Solicitacao solicitacao = new Solicitacao(carona, usuario);

        carona.save();
        carona1.save();
        solicitacao.save();

        List<Usuario> verifica = SistemaUsuarios.getInstance().getUsuarios();
        for(Usuario s : verifica){
            System.out.println(Json.toJson(s));
        }
        int vagas = carona.getVagas();
        carona.novoPassageiro(usuario);
        carona.setVagas(--vagas);  

        SistemaCaronas.getInstance().atualizarCarona(carona);

       
        return ok(main.render());
    }

    public Result testaFoto()
    {
        Http.MultipartFormData request = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> targetFiles = request.getFiles();
        byte[] data;
        File file = null;
        for (int i = 0; i < targetFiles.size(); i++)
        {
            file = (File) targetFiles.get(i).getFile();

            String fileName = targetFiles.get(i).getFilename();

            data = new byte[(int)file.length()];
            return ok(data).as("image");
        }

        return ok("Imagem inválida");
    }

}