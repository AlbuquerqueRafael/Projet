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
import sistemasInfo.*;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;

import static play.libs.Json.*;
import static play.data.Form.form;

public class Application extends Controller {



    public Result main(String any) {
    	PropertyConfigurator.configure("conf/log4j.properties");  // NAO EXCLUIR

        List<Carona> allCaronas = SistemaCaronas.getInstance().getCaronas();
        for (Carona c: allCaronas) {
            c.delete();
        }

        List<Usuario> allusers = SistemaUsuarios.getInstance().getUsuarios();
        for (Usuario c: allusers) {
            c.delete();
        }


        Endereco endereco = new Endereco("Rua da Flores", "Centro");
        Usuario usuario = new Usuario("Pedro", "123", "99621653", "pedro@hotmail.com", "123", endereco, null);
        usuario.save();


        List<String> rota1 = new ArrayList<String>();
        rota1.add("Centro");
        rota1.add("Prata");
        Horario horario = new Horario("8-10", DiaDaSemana.SEGUNDA);
        Carona carona = new Carona(usuario, horario, 4, rota1, endereco, TipoCarona.IDA);

        carona.save();

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