package services;


import org.apache.log4j.Logger;


public class ServiceLog {

	 final static Logger logger = Logger.getLogger(ServiceLog.class);

	 public static void novaMensagemLog(String message){
	 	logger.info(message);
	 }

	 public static void error(Exception e, String message){
		 logger.error("falha! " + message, e);
	 }
}