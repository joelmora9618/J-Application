package com.example.jmora.webservicesoap.ui.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.jmora.webservicesoap.Busines.GrupoBusiness;
import com.example.jmora.webservicesoap.Models.Grupo;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.adapters.GroupAdapter;
import com.example.jmora.webservicesoap.Models.GrupoEmpleado;
import com.example.jmora.webservicesoap.ui.activities.AddNewGroup;
import com.example.jmora.webservicesoap.ui.activities.ViewGroupActivity;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends Fragment{

    private ListView lvGrupos;
    private GroupAdapter groupAdapter;
    public static String NAME_GROUP = "NAME_GROUP";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        lvGrupos = (ListView)view.findViewById(R.id.lvGrupos);

        initControls();

        FrameLayout flAddGroup = (FrameLayout)view.findViewById(R.id.flAddGroup);
        flAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddNewGroup.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void initControls()
    {
        GrupoBusiness grupoBusiness = new GrupoBusiness();
        List<Grupo>grupoList = grupoBusiness.grupoList(getContext());
        groupAdapter = new GroupAdapter(getContext(),grupoList);
        lvGrupos.setAdapter(groupAdapter);
        lvGrupos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Grupo grupo = (Grupo) groupAdapter.getItem(i);
                Intent intent = new Intent(getContext(), ViewGroupActivity.class);
                intent.putExtra(NAME_GROUP,grupo.getNombre_grupo());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initControls();
    }
}
