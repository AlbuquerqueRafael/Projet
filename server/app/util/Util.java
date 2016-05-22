package util;

import models.*;
import play.data.validation.Constraints.EmailValidator;
import play.mvc.Controller;
import models.enums.DiaDaSemana;
import java.util.List;

/**
 * Classe utilitaria para validar as informacoes
 */
public class Util {
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

        try {
            if (phone.matches(regexStr)){
                result = true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        
        return result;
    }
    

    public static boolean isValidPassword(String password) {
        boolean result = false;
        int minimumSizePassword = 8;

        try {
            if (password.length() >= minimumSizePassword){
                result = true;
            } 
        } catch (NullPointerException e){
            return false;
        }
       
        return result;
    }

    public static boolean isValidMatricula(String matricula) {
        boolean result = false;
        String regexStr = "[1-7,9][0-1][0-6][0-1][1-4][0-9]{4}";

        try {
            if (matricula.matches(regexStr)){
            result = true;
            } 
        } catch (NullPointerException e){
            return false;
        }
       
        return result;
    }

    public static boolean isValidHorarioCarona(Carona carona, Carona comparada){
        System.out.println("Entrei no metodo");
        DiaDaSemana dia1 = carona.getHorario().getDia();
        DiaDaSemana dia2 = comparada.getHorario().getDia();
        String aula1 = carona.getHorario().getAula();
        String aula2 = comparada.getHorario().getAula();
        if(dia1 == dia2 && aula1.equals(aula2)){
            return false;
        }

        return true;
    }

    public static boolean isEnderecoCompativel(Carona carona, Carona comparada){
        List<Rota> rotas = carona.getRota();
        String bairro_passageiro = comparada.getEndereco().getBairro();

        if(carona.getEndereco().equals(comparada.getEndereco())){
            return true;
        }

        for (Rota rota: rotas){
            if (rota.getDestino().equals(bairro_passageiro)) return true;
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
