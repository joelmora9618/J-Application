package com.example.jmora.webservicesoap.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.adapters.TabsPagerAdapter;


public class PublicationFragment extends Fragment {

    TabLayout tabLayout;
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publication, container, false);


        return view;
    }


}
