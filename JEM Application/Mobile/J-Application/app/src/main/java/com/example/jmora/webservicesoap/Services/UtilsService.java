package com.example.jmora.webservicesoap.Services;

import android.content.Context;

import com.example.jmora.webservicesoap.Data.SharedPreferences.JemPreferences;
import com.example.jmora.webservicesoap.validator.Exceptions.CredentialsException;
import com.example.jmora.webservicesoap.validator.Exceptions.ServiceErrorException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

/**
 * Created by JMora on 08/08/2017.
 */

public class UtilsService {
    public static String HOST = "jemwebapi.somee.com";
    //public static String HOST = "jemwebapi.somee.com";

    public static String getHostWithProtocol(Context context){
        return String.format("http://%s/",getHOST(context));
    }

    public static String checkResponse(InputStream entity) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    entity), 65728);
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("finalResult " + sb.toString());

        return sb.toString();
    }

    public static void addAuthorizationHeader(HttpUriRequest req,
                                              Context context) {

        String token = JemPreferences.getString(context, JemPreferences.KEY_TOKEN, "");
        req.setHeader(
                "Authorization",
                token);

    }

    public static void addHostHeader(HttpUriRequest req,
                                     Context context) {
        req.setHeader(
                "Host",getHOST(context));
    }

    public static String getHOST(Context context){
        return JemPreferences.getString(context, JemPreferences.KEY_URL_HOST, HOST);
    }

    public static void addCacheControlHeader(HttpUriRequest req,
                                             Context context) {
        req.setHeader(
                "Cache-Control",
                "no-cacheWS_URL_AZURE_AD");

    }

    public static void changeAuthorizationHeader(HttpUriRequest req,
                                                 Context context) {
        req.removeHeaders("Authorization");
        addAuthorizationHeader(req, context);
    }

    public static void addContentTypeHeader(HttpUriRequest req, Context context) {
        req.setHeader("Content-Type", "application/json");
    }

    public static void addAceptHeader(HttpUriRequest req, Context context) {
        req.setHeader("Accept", "application/json");
    }

    public static void addClientIdHeader(HttpUriRequest req, Context context) {
        req.setHeader("clientId", "MobileApp");
    }

    public static String addParameters(String url,
                                       List<BasicNameValuePair> params) {
        if (!url.endsWith("?"))
            url += "?";

        String paramString = URLEncodedUtils.format(params, "utf-8");

        url += paramString;
        return url;
    }

    public static void addLanguageHeader(HttpUriRequest req,
                                         Context context) {

        String language = Locale.getDefault().getLanguage();
        req.setHeader(
                "Accept-Language",
                language);
    }

    public static InputStream retrieveStreamGET(HttpGet getRequest,
                                                Context context) throws ServiceErrorException {

        return Proxy.get(getRequest, context);

    }

    public static HttpEntity retrieveStreamEntityGET(HttpGet getRequest,
                                                     Context context) throws ServiceErrorException {

        return Proxy.getEntity(getRequest, context);
    }

    public static InputStream retrieveStreamGET(String url, String token, Context context)
            throws ServiceErrorException {

        HttpGet getRequest = new HttpGet(url);

        return Proxy.get(getRequest, context);

    }

    public static InputStream retrieveStreamDELETE(String url, String token, Context context)
            throws ServiceErrorException {

        HttpDelete deleteRequest = new HttpDelete(url);

        return Proxy.get(deleteRequest, context);

    }

    public static InputStream retrieveStreamDELETE(HttpDelete deleteRequest, String token, Context context)
            throws ServiceErrorException {
        return Proxy.get(deleteRequest, context);
    }

    public static InputStream retrieveStreamPutJson(HttpPut putRequest, String obj, Context context)
            throws ServiceErrorException {

        try {
            StringEntity stringEntity = new StringEntity(obj, HTTP.UTF_8);
            putRequest.setEntity(stringEntity);
            return Proxy.get(putRequest, context);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static InputStream retrieveStreamPostJson(HttpPost postRequest,
                                                     String obj, Context context) throws ServiceErrorException, CredentialsException {
        try {
            StringEntity stringEntity = new StringEntity(obj, HTTP.UTF_8);
            stringEntity.setContentType("application/json");

            postRequest.setEntity(stringEntity);
            return Proxy.post(postRequest, context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpEntity retrieveHttpEntityStreamPostJson(HttpPost postRequest,
                                                              String obj, Context context) throws ServiceErrorException, CredentialsException {
        try {
            StringEntity stringEntity = new StringEntity(obj, HTTP.UTF_8);
            stringEntity.setContentType("application/json");

            postRequest.setEntity(stringEntity);
            return Proxy.postRetriveEntity(postRequest, context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream retrieveStreamPostJson(String url, String obj,
                                                     Context context) throws ServiceErrorException, CredentialsException {
        try {
            HttpPost postRequest = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(obj);
            stringEntity.setContentType("application/json");
            addLanguageHeader(postRequest, context);

            postRequest.setEntity(stringEntity);

            return Proxy.post(postRequest, context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static InputStream retrieveStreamPostJson(HttpPost postRequest,
                                                     Context context) throws ServiceErrorException, CredentialsException {

        return Proxy.post(postRequest, context);

    }

    public static InputStream retrieveStreamPostQueryString(String url,
                                                            String obj, Context context) throws ServiceErrorException, CredentialsException {
        try {
            HttpPost postRequest = new HttpPost(url);
            StringEntity stringEntity;

            stringEntity = new StringEntity(obj);

            stringEntity.setContentType("application/x-www-form-urlencoded");

            postRequest.setEntity(stringEntity);
            return Proxy.post(postRequest, context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream retrieveStreamPost(String url, Context context)
            throws ServiceErrorException, CredentialsException {

        HttpPost postRequest = new HttpPost(url);
        return Proxy.post(postRequest, context);
    }

    public static HttpResponse retrieveStreamPOST(HttpPost postRequest)
            throws ClientProtocolException, IOException {

        DefaultHttpClient client = new DefaultHttpClient();

        HttpResponse postResponse = client.execute(postRequest);

        return postResponse;

    }

    public static HttpResponse retrieveStream(HttpUriRequest http)
            throws ClientProtocolException, IOException {

        DefaultHttpClient client = new DefaultHttpClient();

        return client.execute(http);

    }
}
