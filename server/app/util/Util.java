package util;

import play.mvc.Controller;



/**
 * Created by rafael on 04/04/16.
 */
public class Util extends Controller {

    public static boolean isValidEmailAddress(String email) {
        return true;
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
        int exactSizeMatricula = 9;
        if (matricula.length() == exactSizeMatricula){
            result = true;
        }
        return result;
    }

}
