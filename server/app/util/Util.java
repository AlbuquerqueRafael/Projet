package util;

import models.Carona;
import play.data.validation.Constraints.EmailValidator;
import play.mvc.Controller;

/**
 * Classe utilitaria para validar as informacoes
 */
public class Util extends Controller {
    	private static final char CAMPUSCAMPINA = '1';
    	private static final char PRIMEIROPERIODO = '1' , SEGUNDOPERIODO = '2';
    	private static final char CODIGOEXATAS = '1', CODIGOBIOLOGICAS= '2', CODIGOHUMANAS= '4', CODIGOCIENCIASAGRARIAS= '3';


    public static boolean isValidEmailAddress(String email) {
        EmailValidator validator = new EmailValidator();

        return validator.isValid(email);
    }

    public static boolean isValidTelefone(String phone) {
        boolean result = false;
        String regexStr = "^[0-9]*$";
        if (phone.matches(regexStr)){
            result = true;
        }
        return result;
    }

    public static boolean isValidPassword(String password) {
        boolean result = false;
        int minimumSizePassword = 8;
        if (password.length() >= minimumSizePassword){
            result = true;
        }
        return result;
    }

    public static boolean isValidMatricula(String matricula) {
        boolean result = false;
        String regexStr = "[1-7,9][0-1][0-6][0-1][1-4][0-9]{4}";
        if (matricula.matches(regexStr)){
            result = true;
        }
        return result;
    }

    public static boolean isValidHorarioCarona(Carona carona, Carona comparada){
        if(carona.getHorario().getDia().equals(comparada.getHorario().getDia()) &&
                carona.getHorario().getAula().equals(comparada.getHorario().getAula())){
            return false;
        }

        return true;
    }

    public static boolean isEnderecoCompativel(Carona carona, Carona comparada){
        if(carona.getEndereco().equals(comparada.getEndereco()) || carona.getRota().contains(comparada) ){
            return true;
        }


        return false;
    }


    public static boolean isCaronaLotada(Carona c){
        int numero_vagas = c.getVagas();
        int ocupadas = c.getListaPassageiros().size();

        if (ocupadas == numero_vagas){
            return true;
        }

        return false;
    }


}
