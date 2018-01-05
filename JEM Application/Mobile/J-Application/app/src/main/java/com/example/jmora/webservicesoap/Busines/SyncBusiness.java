package com.example.jmora.webservicesoap.Busines;

import android.content.Context;
import android.os.Handler;

import com.example.jmora.webservicesoap.Data.Constants;
import com.example.jmora.webservicesoap.Data.DataBaseManager;
import com.example.jmora.webservicesoap.Data.Repositories.EmpleadoRepository;
import com.example.jmora.webservicesoap.Data.SharedPreferences.JemPreferences;

import com.example.jmora.webservicesoap.Models.AppVersion;
import com.example.jmora.webservicesoap.Models.DaoSession;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.validator.Exceptions.AppVersionException;
import com.example.jmora.webservicesoap.validator.Exceptions.CredentialsException;
import com.example.jmora.webservicesoap.validator.Exceptions.ServiceErrorException;
import com.example.jmora.webservicesoap.validator.Exceptions.ValidationException;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by JMora on 05/09/2017.
 */

public class SyncBusiness {

    public static void synkData(Handler progress,
                                Context context) throws
            ServiceErrorException, CredentialsException, ValidationException, InterruptedException, IOException, JSONException, AppVersionException {
        String user = JemPreferences.getString(context, JemPreferences.KEY_USER, "").trim();
        String pwd = JemPreferences.getString(context, JemPreferences.KEY_PASS, "").trim();
        try {
            AppVersion appVersion = VersionBusiness.getAppVersion(context);
            if(appVersion!=null){
                if(appVersion.StatusId == Constants.APP_VERSION_ERROR){
                    throw new AppVersionException(appVersion.StatusDescription);
                }
            }

            UserBusiness.postUser(user, pwd, context);
            //QueueAssetsBusiness.syncQueueData(user,progress, Constants.COEF_PROGRESS_UPLOAD, R.string.uploading_data, context);
        } catch (CredentialsException e) {
            throw e;
        } catch (ServiceErrorException e) {
            throw e;
        }

        try {
            SyncBusiness.deleteData(context);

            EmpleadoBusiness.syncUsers(user,progress,Constants.COEF_PROGRESS_USERS,context);


        } catch (ServiceErrorException e) {
            deleteData(context);
            throw e;
        } catch (Exception e) {
            deleteData(context);
            e.printStackTrace();
            throw new ServiceErrorException(context.getResources().getString(R.string.unexpected_error));
        }
    }

    public static void deleteAll(Context context) {
        deleteData(context);
        //deleteQueue(context);

    }

    public static void deleteData(Context context) {
        //Delete db info
        EmpleadoRepository.deleteEmpleado(context);

    }
}
