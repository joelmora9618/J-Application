package com.example.jmora.webservicesoap.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jmora.webservicesoap.MainActivity;
import com.example.jmora.webservicesoap.R;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen);
        LoadApp loadApp = new LoadApp();
        loadApp.execute();
    }


    private class LoadApp extends AsyncTask<Void, Integer, Boolean>
    {
        private ProgressDialog dialog;
/*
        public LoadApp(SplashScreenActivity activity)
        {
            dialog = new ProgressDialog(activity);
        }
*/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
/*            dialog.setTitle("Cargando...");
            dialog.setMessage("Por favor espere!");
            dialog.show();
*/        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                //TIEMPO DE SIMULACIÓN DE CARGA DE LA APLICACIÓN
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                try {
                    throw e;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            try {
                /*
                if (dialog.isShowing())
                {
                    dialog.dismiss();
               */   Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                    startActivity(intent);
                    //CIERRA LA ACTIVIDAD, PARA QUE EL USUARIO NO PUEDA VOLVER
                    finish();
              /*  }else
                {
                    Toast.makeText(SplashScreenActivity.this,"No se ah podido ingresar a la aplicación",Toast.LENGTH_SHORT).show();
                }*/
            }catch (Exception e)
            {
                Toast.makeText(SplashScreenActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
