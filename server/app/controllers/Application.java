package controllers;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import util.Util;
import views.html.index;
import views.html.main;

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
    	PropertyConfigurator.configure("conf/log4j.properties");
        return ok(main.render());
    }

    public Result testaFoto()
    {
        final String DIRECTORY = "/app";
        System.out.println("ss");
        Http.MultipartFormData request = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> targetFiles = request.getFiles();
        System.out.println("ss");
        byte[] data;
        File file = null;
        for (int i = 0; i < targetFiles.size(); i++)
        {
            file = (File) targetFiles.get(i).getFile();

            String fileName = targetFiles.get(i).getFilename();

            data = new byte[(int)file.length()];
            return ok(data).as("image");
        }

        return ok("gg");
    }

}