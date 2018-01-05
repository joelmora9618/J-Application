package com.example.jmora.webservicesoap.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmora.webservicesoap.Busines.EmpleadoBusiness;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.Models.Empleado;
import com.example.jmora.webservicesoap.adapters.EmpleadoAdapter;
import com.example.jmora.webservicesoap.adapters.EmpleadoFragmentAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class EmpleadosFragment extends Fragment {

    EmpleadoFragmentAdapter eAdapter;
    private ListView listItems;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alumnos, container, false);
        listItems = (ListView) view.findViewById(R.id.lvEmpleados);

        initListView();

        return view;
    }

    private void initListView() {

        List<Empleado> empleadoList = EmpleadoBusiness.getEmpleado(getActivity());
        eAdapter = new EmpleadoFragmentAdapter(getActivity(), empleadoList, listItems);
        listItems.setAdapter(eAdapter);

    }

}
