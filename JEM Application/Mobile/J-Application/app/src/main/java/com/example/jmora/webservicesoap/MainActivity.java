package com.example.jmora.webservicesoap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jmora.webservicesoap.ui.activities.EmpleadosActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAlta,
           btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMostrar = (Button)findViewById(R.id.btnMostrar);
        btnMostrar.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        LoadOf();
    }

    private void LoadOf()
    {
        new AlertDialog.Builder(this)
                .setMessage("Desea Cerrar Sesion?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnMostrar)
        {
            Intent intent = new Intent(this,EmpleadosActivity.class);
            startActivity(intent);
        }
    }
}
