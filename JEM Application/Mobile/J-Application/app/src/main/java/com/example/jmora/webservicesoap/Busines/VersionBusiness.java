package com.example.jmora.webservicesoap.Busines;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.jmora.webservicesoap.BuildConfig;
import com.example.jmora.webservicesoap.Models.AppVersion;
import com.example.jmora.webservicesoap.Services.UtilsService;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by JMora on 22/08/2017.
 */

public class VersionBusiness {

    private static String API_VERSIONS = "api/versions?number=%s";

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static AppVersion getAppVersion(Context context) {
        try {

            //BuildConfig.VERSION_NAME
            String url = UtilsService.getHostWithProtocol(context) + String.format(API_VERSIONS, BuildConfig.VERSION_NAME);

            HttpGet getRequest = new HttpGet(url);

            //heathers
            //***********************
            //***********************
            UtilsService.addAuthorizationHeader(getRequest, context);

            //UtilsService.addLanguageHeader(getRequest, context);
            UtilsService.addCacheControlHeader(getRequest, context);
            UtilsService.addHostHeader(getRequest, context);
            //***********************
            //***********************
            //heathers

            HttpEntity getResponseEntity = UtilsService.retrieveStreamEntityGET(getRequest, context);
            InputStream source = getResponseEntity.getContent();

            BufferedReader r = new BufferedReader(new InputStreamReader(source));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }

            String finalStr = total.toString();

            Gson gson = new Gson();
            //Reader reader = new InputStreamReader(source);

            AppVersion[] appVersion = gson.fromJson(finalStr, AppVersion[].class);

            if (appVersion!=null && appVersion.length > 0) {
                return appVersion[0];
            }
        } catch (Exception ex) {
        }
        return null;
    }
}
