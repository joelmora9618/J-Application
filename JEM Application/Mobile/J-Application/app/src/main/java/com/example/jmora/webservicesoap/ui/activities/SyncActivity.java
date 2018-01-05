package com.example.jmora.webservicesoap.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.jmora.webservicesoap.Busines.SyncBusiness;
import com.example.jmora.webservicesoap.Models.ProgressMessage;
import com.example.jmora.webservicesoap.Parent.MasterAppCompatActivity;
import com.example.jmora.webservicesoap.ui.activities.LoginActivity;
import com.example.jmora.webservicesoap.ui.activities.NavigationDrawerActivity;
import com.example.jmora.webservicesoap.validator.Exceptions.AppVersionException;
import com.example.jmora.webservicesoap.validator.Exceptions.CredentialsException;
import com.example.jmora.webservicesoap.validator.Exceptions.ServiceErrorException;

/**
 * Created by JMora on 07/12/2017.
 */

public class SyncActivity extends MasterAppCompatActivity{

    protected void syncData(final LoginActivity mActivity, final ProgressDialog progress) {
        //TODO should we change into intentService

        final Handler progressHandler = new Handler(getApplicationContext().getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                ProgressMessage prg = (ProgressMessage) msg.obj;
                progress.setProgress(prg.nProgress);
                progress.setMessage(getResources().getString(prg.tittle));
                //  Toast.makeText(getApplicationContext(),"Alert Dialog",Toast.LENGTH_SHORT).show();
            }
        };

        try {
            SyncBusiness.synkData(progressHandler, getApplicationContext());


        } catch (ServiceErrorException ex) {
            showCrouton(_activity, ex.getMessage());
        } catch (CredentialsException ex) {
            showCrouton(_activity, ex.getMessage());
            Handler mainHandler = new Handler(getApplicationContext().getMainLooper());

            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    //main thread

                    Intent i = new Intent(_activity, LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            };
            mainHandler.post(myRunnable);
        } catch (AppVersionException ex) {
            showCrouton(_activity, ex.getMessage());
        } catch (Exception ex) {
            showCrouton(_activity, null);
        }
    }

}
