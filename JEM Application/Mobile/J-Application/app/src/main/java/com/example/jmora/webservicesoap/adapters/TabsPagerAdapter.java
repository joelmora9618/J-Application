package com.example.jmora.webservicesoap.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.ui.fragments.EmpleadosFragment;
import com.example.jmora.webservicesoap.ui.fragments.GaleryFragment;
import com.example.jmora.webservicesoap.ui.fragments.GroupFragment;
import com.example.jmora.webservicesoap.ui.fragments.PublicationFragment;

/**
 * Created by JMora on 04/01/2018.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public TabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new GroupFragment();
        } else {
            return new PublicationFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.category_group);
            case 1:
                return mContext.getString(R.string.category_publication);
          /*  case 2:
                return mContext.getString(R.string.category_publication);
            case 3:
                return mContext.getString(R.string.category_gallery);*/
            default:
                return null;
        }
    }

}
