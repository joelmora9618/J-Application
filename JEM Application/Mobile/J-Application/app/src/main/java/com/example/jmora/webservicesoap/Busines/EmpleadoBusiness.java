package com.example.jmora.webservicesoap.Busines;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.example.jmora.webservicesoap.Data.Constants;
import com.example.jmora.webservicesoap.Data.Repositories.EmpleadoRepository;
import com.example.jmora.webservicesoap.Models.Empleado;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.Services.UtilsService;
import com.example.jmora.webservicesoap.Utils.ProgressUtils;
import com.example.jmora.webservicesoap.validator.Exceptions.ServiceErrorException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JMora on 22/08/2017.
 */

public class EmpleadoBusiness extends ProgressUtils{
    private static String API_USERS_GETBYUSER = "api/Empleados";

    public static void syncUsers(String user, Handler progress, double coef, Context context) throws
            ServiceErrorException, IOException, InterruptedException {

        _progress = progress;
        String url = UtilsService.getHostWithProtocol(context) + API_USERS_GETBYUSER;

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
        long lenghtOfResponse = getResponseEntity.getContentLength();
        InputStream source = getResponseEntity.getContent();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Reader reader = new InputStreamReader(source);

        String result = updateProgress(reader,4096, lenghtOfResponse, coef, R.string.loading_empleados);

        Empleado[] empleados = gson.fromJson(result, Empleado[].class);
        sendProgress(Constants.FIXED_PROGRESS_GSON_MAP, coef, R.string.loading_empleados);
        int size = empleados.length;
        for (int i = 0; i < size; i++) {
            Empleado emp = empleados[i];
            EmpleadoRepository.insertEmpleado(emp, context);
        }
        sendProgress(Constants.FIXED_PROGRESS_DATAACCES, coef, R.string.loading_empleados);
    }

    public static List<Empleado> getEmpleado(Context context) {
        List<Empleado> empleadoList = EmpleadoRepository.getEmpleado(context);

        return empleadoList;
    }

    public static Empleado getEmpleado(long key, Context context) {
        Empleado emp = (Empleado) EmpleadoRepository.getEmpleado(key, context);
        return emp;
    }


}
