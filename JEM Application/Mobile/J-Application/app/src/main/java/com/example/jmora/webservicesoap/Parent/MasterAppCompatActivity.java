package com.example.jmora.webservicesoap.Parent;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jmora.webservicesoap.Application.JemApplication;
import com.example.jmora.webservicesoap.R;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by JMora on 05/09/2017.
 */

public class MasterAppCompatActivity extends AppCompatActivity {

    protected Activity _activity;

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain the shared Tracker instance.
        JemApplication application = (JemApplication) getApplication();
        mTracker = application.getDefaultTracker();

        _activity = this;
    }

    public static void showCrouton(final Activity context, final String mensaje) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            // HILO principal!
            showCroutonMainThread(context, mensaje);
        } else {
            Handler mainHandler = new Handler(context.getMainLooper());

            Runnable myRunnable = new Runnable() {

                @Override
                public void run() {
                    showCroutonMainThread(context, mensaje);
                }
            };
            mainHandler.post(myRunnable);

        }
    }

    public static void showCroutonMainThread(Activity context, String mensaje) {

        if (mensaje == null) {
            mensaje = context.getString(R.string.unexpected_error);
        }

        //TODOD agregar lib de crouton
        //Crouton.makeText(context, mensaje, Style.INFO).show();

        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
}
