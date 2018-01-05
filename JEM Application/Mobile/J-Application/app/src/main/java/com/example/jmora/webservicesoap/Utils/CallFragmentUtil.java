package com.example.jmora.webservicesoap.Utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.example.jmora.webservicesoap.R;

/**
 * Created by JMora on 21/04/2017.
 */

public class CallFragmentUtil {
    Activity activity;
    Fragment classFragment;
    ActionBar actionbar;

    public CallFragmentUtil(Activity activity, Fragment classFragment, android.support.v7.app.ActionBar actionbar){
        this.activity = activity;
        this.classFragment = classFragment;
        this.actionbar = actionbar;
        callFragment();
    }

    public CallFragmentUtil(Activity activity, Fragment classFragment){
        this.activity = activity;
        this.classFragment = classFragment;
        callFragment();
    }

    public void callFragment(){

        if ( activity.getFragmentManager().findFragmentById(R.id.contenedor) != null){
            activity.getFragmentManager().beginTransaction().remove(activity.getFragmentManager().findFragmentById(R.id.contenedor)).commit();
        }
        //Paso 1: Obtener la instancia del administrador de fragmentos
        FragmentManager fragmentManager = activity.getFragmentManager();

        //Paso 2: Crear una nueva transacción
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Paso 3: Añadir un nuevo fragmento
        transaction.add(R.id.contenedor, classFragment);

        //Paso 4: Confirmar el cambio
        transaction.commit();
    }
}
