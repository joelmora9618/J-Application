package com.example.jmora.webservicesoap.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jmora.webservicesoap.Busines.GrupoBusiness;
import com.example.jmora.webservicesoap.Models.Grupo;
import com.example.jmora.webservicesoap.R;

public class AddNewGroup extends AppCompatActivity {
    Activity _activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_group);

        _activity = this;
        initControls();
    }

    public void initControls()
    {
        final EditText etGName = (EditText)findViewById(R.id.etGName);
        Button btnAddGroup = (Button)findViewById(R.id.btnAddGroup);

        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage(etGName.getText().toString());
            }
        });
    }

    public void showMessage(final String nameGroup)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
        builder.setTitle("Agregar nuevo grupo");
        builder.setMessage(Html.fromHtml("Seguro que quiere agregar el grupo <font color='#d70000'>"+nameGroup+"</font> ?"))
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addGroup(nameGroup);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                }).show();
    }

    public void addGroup(String nameGroup)
    {
        Grupo newGroup = new Grupo();
        newGroup.setNombre_grupo(nameGroup);
        newGroup.setEstado(1);
        GrupoBusiness grupoBusiness = new GrupoBusiness();
        grupoBusiness.insertGrupo(newGroup,_activity);
    }
}
