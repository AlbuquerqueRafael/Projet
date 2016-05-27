package controllers;

import java.io.File;
import play.mvc.*;
import org.apache.log4j.PropertyConfigurator;


public class Application extends Controller {



    public Result main(String any) {
    	PropertyConfigurator.configure("conf/log4j.properties"); 
        return TODO;
    }

}