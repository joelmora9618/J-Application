package com.example.jmora.webservicesoap.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


import com.example.jmora.webservicesoap.Busines.EmpleadoBusiness;
import com.example.jmora.webservicesoap.Models.Empleado;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.adapters.EmpleadoAdapter;

import java.util.List;

public class EmpleadosActivity extends AppCompatActivity{

    EmpleadoAdapter eAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        initUi();
    }

    private void initUi(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Empleados");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initListView();
    }

    private void initListView() {

        List<Empleado> empleadoList = EmpleadoBusiness.getEmpleado(this);
        final ListView listItems = (ListView) findViewById(R.id.lvEmpleados);
        eAdapter = new EmpleadoAdapter(this, empleadoList, listItems);
        listItems.setAdapter(eAdapter);

    }

}
