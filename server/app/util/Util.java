package util;

import play.mvc.Controller;



/**
 * Created by rafael on 04/04/16.
 */
public class Util extends Controller {

    public static boolean isValidEmailAddress(String email) {
        return true;
    }

    public static boolean isValidTelefone(String telefone) {
        boolean result = false;
        String regexStr = "^[0-9]*$";
        if (telefone.matches(regexStr)){
            result = true;
        }

        return result;
    }

}
