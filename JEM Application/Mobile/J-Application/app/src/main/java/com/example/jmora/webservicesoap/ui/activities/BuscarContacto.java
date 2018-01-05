package com.example.jmora.webservicesoap.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jmora.webservicesoap.R;

public class BuscarContacto extends AppCompatActivity {

    EditText etDni;
    TextView tvNombre,
             tvApellido,
             tvEdad,
             tvSexo,
             tvDivicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_contacto);

        etDni = (EditText)findViewById(R.id.etDni);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvApellido = (TextView) findViewById(R.id.tvApellido);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvSexo = (TextView) findViewById(R.id.tvSexo);
        tvDivicion = (TextView) findViewById(R.id.tvDivicion);
    }
}
