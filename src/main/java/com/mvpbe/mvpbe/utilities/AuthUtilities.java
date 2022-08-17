package com.mvpbe.mvpbe.utilities;

import com.mvpbe.mvpbe.entities.MPVUser;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Random;

public class AuthUtilities {

    public static MPVUser GET_LOGGED_USER(Authentication authentication){
        MPVUser user = (MPVUser) authentication.getPrincipal();
        return user;
    }


    public static String generateRandomString(int chars){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(chars);
        for (int i = 0; i < chars; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    public static boolean differsByMinutes(int minutes, Date start, Date end){
        if (end.getTime() - start.getTime() >= minutes*60*1000) {
            return true;
        }
        return false;
    }


}
