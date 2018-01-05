package com.example.jmora.webservicesoap.Services;

import android.content.Context;
import android.util.Log;

import com.example.jmora.webservicesoap.Data.SharedPreferences.JemPreferences;
import com.example.jmora.webservicesoap.Models.ErrorResponse;
import com.example.jmora.webservicesoap.validator.Exceptions.CredentialsException;
import com.example.jmora.webservicesoap.validator.Exceptions.ServiceErrorException;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by JMora on 08/08/2017.
 */

public class Proxy {

    public static InputStream post(HttpPost postRequest, Context context)
            throws ServiceErrorException, CredentialsException {
        try {
            HttpResponse res = UtilsService.retrieveStreamPOST(postRequest);
            final int statusCode = res.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {

                if( statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    throw new CredentialsException("Incorrect user or PIN!");
                }else if (statusCode == HttpStatus.SC_BAD_REQUEST) {
                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(res.getEntity()
                            .getContent());
                    ErrorResponse error = gson.fromJson(reader,
                            ErrorResponse.class);

                    throw new ServiceErrorException(error.getMessage());

                } else if (statusCode == HttpStatus.SC_UNAUTHORIZED
                        || statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    //TODO renuevo session
                    //TODO levantarlo de sharedPrederences
                    //TODO actualizar token
                    /*
                    String usuario = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_USUARIO, "");
                    String contrasenia = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_CONTRASENIA, "");

                    NegocioUsuario.getToken(usuario, contrasenia, context);
                    */

                    UtilsService.changeAuthorizationHeader(postRequest, context);

                    res = UtilsService.retrieveStreamPOST(postRequest);

                    // devuelvo el inputStream
                    HttpEntity getResponseEntity = res.getEntity();

                    return getResponseEntity.getContent();
                }

            }

            // devuelvo el inputStream
            HttpEntity getResponseEntity = res.getEntity();

            return getResponseEntity.getContent();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Error internet!
            e.printStackTrace();
        }
        return null;

    }

    public static HttpEntity postRetriveEntity(HttpPost postRequest, Context context)
            throws ServiceErrorException, CredentialsException {
        try {
            HttpResponse res = UtilsService.retrieveStreamPOST(postRequest);
            final int statusCode = res.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {

                if( statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    throw new CredentialsException("Incorrect user or PIN!");
                }else if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    //TODO regionalizar
                    throw new ServiceErrorException("Server error, try later please.");

                }else if (statusCode == HttpStatus.SC_BAD_REQUEST) {
                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(res.getEntity()
                            .getContent());
                    ErrorResponse error = gson.fromJson(reader,
                            ErrorResponse.class);

                    throw new ServiceErrorException(error.getMessage());

                } else if (statusCode == HttpStatus.SC_UNAUTHORIZED
                        || statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    //TODO renuevo session
                    //TODO levantarlo de sharedPrederences
                    //TODO actualizar token
                    /*
                    String usuario = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_USUARIO, "");
                    String contrasenia = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_CONTRASENIA, "");

                    NegocioUsuario.getToken(usuario, contrasenia, context);
                    */

                    UtilsService.changeAuthorizationHeader(postRequest, context);

                    res = UtilsService.retrieveStreamPOST(postRequest);

                    // devuelvo el inputStream
                    HttpEntity getResponseEntity = res.getEntity();

                    return getResponseEntity;
                }

            }

            // devuelvo el inputStream
            HttpEntity getResponseEntity = res.getEntity();

            return getResponseEntity;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Error internet!
            e.printStackTrace();
        }
        return null;

    }

    public static InputStream get(HttpUriRequest http, Context context)
            throws ServiceErrorException {
        try {
            HttpResponse res = UtilsService.retrieveStream(http);
            final int statusCode = res.getStatusLine().getStatusCode();
            // ServicioUtils.checkResponse(res.getEntity().getContent())
            if (statusCode != HttpStatus.SC_OK) {

                if (statusCode == HttpStatus.SC_BAD_REQUEST) {
                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(res.getEntity()
                            .getContent());

                    //TODO chequear que devuelve el WS cuando hay un error
                    ErrorResponse error = gson.fromJson(reader,
                            ErrorResponse.class);

                    throw new ServiceErrorException(error.getMessage());
                } else if (statusCode == HttpStatus.SC_UNAUTHORIZED) {

                    //TODO renuevo session
                    //TODO levantarlo de sharedPrederences
                    //TODO actualizar token
                    /*
                    String usuario = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_USUARIO, "");
                    String contrasenia = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_CONTRASENIA, "");

                    NegocioUsuario.getToken(usuario, contrasenia, context);
                    */

                    UtilsService.changeAuthorizationHeader(http,context);

                    res = UtilsService.retrieveStream(http);

                    // devuelvo el inputStream
                    HttpEntity getResponseEntity = res.getEntity();

                    //TODO sacarlo
                    Log.i("Lenght: ",String.valueOf(getResponseEntity.getContentLength()));

                    return getResponseEntity.getContent();
                }

            }

            // devuelvo el inputStream
            HttpEntity getResponseEntity = res.getEntity();

            //UtilsService.checkResponse(getResponseEntity.getContent());
            return getResponseEntity.getContent();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            throw new ServiceErrorException(e.getMessage());
        }
        return null;
    }

    public static HttpEntity getEntity(HttpUriRequest http, Context context)
            throws ServiceErrorException {
        try {
            HttpResponse res = UtilsService.retrieveStream(http);
            final int statusCode = res.getStatusLine().getStatusCode();
            // ServicioUtils.checkResponse(res.getEntity().getContent())
            if (statusCode != HttpStatus.SC_OK) {

                if (statusCode == HttpStatus.SC_BAD_REQUEST) {
                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(res.getEntity()
                            .getContent());

                    //TODO chequear que devuelve el WS cuando hay un error
                    ErrorResponse error = gson.fromJson(reader,
                            ErrorResponse.class);

                    throw new ServiceErrorException(error.getMessage());
                } else if (statusCode == HttpStatus.SC_UNAUTHORIZED) {

                    //TODO renuevo session
                    //TODO levantarlo de sharedPrederences
                    //TODO actualizar token
                    /*
                    String usuario = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_USUARIO, "");
                    String contrasenia = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_CONTRASENIA, "");

                    NegocioUsuario.getToken(usuario, contrasenia, context);
                    */

                    UtilsService.changeAuthorizationHeader(http,context);

                    res = UtilsService.retrieveStream(http);

                    // devuelvo el inputStream
                    HttpEntity getResponseEntity = res.getEntity();

                    return  getResponseEntity;
                }

            }

            // devuelvo el inputStream
            HttpEntity getResponseEntity = res.getEntity();

            //UtilsService.checkResponse(getResponseEntity.getContent());
            return getResponseEntity;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            throw new ServiceErrorException(e.getMessage());
        }
        return null;
    }

    public static boolean checkResponseMultipart(HttpEntityEnclosingRequestBase post, Context context, int statusCode)
    {
        try {
            boolean respuesta = true;

            if (statusCode != HttpStatus.SC_OK) {
                if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    //TODO renuevo session
                    //TODO levantarlo de sharedPrederences
                    //TODO actualizar token
                    /*
                    String usuario = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_USUARIO, "");
                    String contrasenia = TechintSocialPreferences.getString(context,
                            TechintSocialPreferences.KEY_CONTRASENIA, "");

                    NegocioUsuario.getToken(usuario, contrasenia, context);
                    */

                    String token = JemPreferences.getString(context, JemPreferences.KEY_TOKEN, "");
                    UtilsService.changeAuthorizationHeader(post, context);
                }
                respuesta = false;
            }

            return respuesta;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    public static String executeMultipart(Context context, HttpEntityEnclosingRequestBase httpPost,  HttpEntity entity)
            throws IOException, ServiceErrorException {

        int INTENTOS = 5;
        boolean enviado = false;
        String resultado = "";

        HttpClient client = new DefaultHttpClient();
        httpPost.setEntity(entity);

        while (INTENTOS > 0 && !enviado) {
            HttpResponse response = client.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            int statudCode = response.getStatusLine().getStatusCode();
            resultado = EntityUtils.toString(httpEntity);
            if (!Proxy.checkResponseMultipart(httpPost, context, statudCode)) {
                INTENTOS--;
            }
            else {
                enviado = true;
            }
        }

        if(!enviado)
        {
            throw new ServiceErrorException();
        }
        else
        {
            return resultado;
        }
    }
}
