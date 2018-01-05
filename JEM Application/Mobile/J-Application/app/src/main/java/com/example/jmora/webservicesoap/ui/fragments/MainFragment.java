package com.example.jmora.webservicesoap.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.jmora.webservicesoap.Models.GrupoEmpleado;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.adapters.GroupAdapter;
import com.example.jmora.webservicesoap.adapters.TabsPagerAdapter;
import com.example.jmora.webservicesoap.ui.activities.AddNewGroup;

import java.util.ArrayList;

/**
 * Created by JMora on 04/01/2018.
 */

public class MainFragment extends Fragment {

    TabLayout tabLayout;
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Initilization
        viewPager = (ViewPager)view.findViewById(R.id.pager);

        mAdapter = new TabsPagerAdapter(getContext(),getFragmentManager());

        viewPager.setAdapter(mAdapter);

        tabLayout = (TabLayout)view.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
