package vn.hcmute.web.logic.utils;

import org.springframework.web.servlet.ModelAndView;
import vn.hcmute.core.data.common.Constant;

import java.util.Map;
import java.util.Random;

public class WebCommonUtil {
    public static void addRedirectMsg(ModelAndView mav, String crudaction, Map<String, String> messageMap){
        if(Constant.REDIRECT_UPDATE.equals(crudaction)) {
            mav.addObject(Constant.ALTER, Constant.TYPE_SUCCESS);
            mav.addObject(Constant.MESSAGE_RESPONSE, messageMap.get(Constant.REDIRECT_UPDATE));
        }else if(Constant.REDIRECT_INSERT.equals(crudaction)) {
            mav.addObject(Constant.ALTER, Constant.TYPE_SUCCESS);
            mav.addObject(Constant.MESSAGE_RESPONSE, messageMap.get(Constant.REDIRECT_INSERT));
        }else if(Constant.ACTION_DELETE.equals(crudaction)){
            mav.addObject(Constant.ALTER, Constant.TYPE_SUCCESS);
            mav.addObject(Constant.MESSAGE_RESPONSE, messageMap.get(Constant.ACTION_DELETE));
        }else if(Constant.REDIRECT_ERROR.equals(crudaction)) {
            mav.addObject(Constant.ALTER, Constant.TYPE_DANGER);
            mav.addObject(Constant.MESSAGE_RESPONSE, messageMap.get(Constant.REDIRECT_ERROR));
        }
    }
    public static String generatePassword() {
        String pass = "";
        final String alphabet = "0123456789ABCDE";
        final int N = alphabet.length();
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            pass += alphabet.charAt(r.nextInt(N));
        }
        return pass;
    }
}
