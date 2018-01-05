package com.example.jmora.webservicesoap.Busines;

import android.content.Context;

import com.example.jmora.webservicesoap.Data.SharedPreferences.JemPreferences;
import com.example.jmora.webservicesoap.Services.UtilsService;
import com.example.jmora.webservicesoap.validator.Exceptions.CredentialsException;
import com.example.jmora.webservicesoap.validator.Exceptions.ServiceErrorException;
import com.example.jmora.webservicesoap.validator.Exceptions.ValidationException;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by JMora on 22/08/2017.
 */

public class UserBusiness {

    public static boolean equalsCredentials(String usr1, String pwd1, String usr2, String pwd2) {
        return (usr1.equalsIgnoreCase(usr2) || usr1.isEmpty() );
    }

    public static void validateCredentials(String user, String pin) throws ValidationException {

        //TODO regionalizar
        if (user.isEmpty() || pin.isEmpty()) {
            throw new ValidationException("Please enter User and Pin");
        }
    }

    private static final String API_LOGIN = "api/login";

    public static void postUser(String user, String pwd, Context context) throws
            ServiceErrorException, IOException, InterruptedException, JSONException, CredentialsException {
        String url = UtilsService.getHostWithProtocol(context) + API_LOGIN;

        String md5Pwd = md5(pwd);
        String payload = String.format("dni_empleado=%s&password=%s", user, md5Pwd); //getPayload(asset);

        InputStream source = UtilsService.retrieveStreamPostQueryString(
                url, payload, context);

        String res = UtilsService.checkResponse(source);

        //THIS MUST COME FROM A SERVICE!
        JemPreferences.setString(context, JemPreferences.KEY_TOKEN, res);

        return;
    }

    private static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

