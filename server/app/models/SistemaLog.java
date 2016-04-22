package models;


import org.apache.log4j.Logger;
import org.apache.log4j.FileAppender;



public class SistemaLog {

	 final static Logger logger = Logger.getLogger(SistemaLog.class);

	 public static void novaMensagemLog(String message){
	 	logger.info(message);
	 }
}