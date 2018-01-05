package com.example.jmora.webservicesoap.ui.activities;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.widget.ListView;

import com.example.jmora.webservicesoap.Busines.EmpleadoBusiness;
import com.example.jmora.webservicesoap.Models.Empleado;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.adapters.EmpleadoAdapter;
import com.example.jmora.webservicesoap.adapters.TabsPagerAdapter;
import com.example.jmora.webservicesoap.ui.fragments.EmpleadosFragment;
import com.example.jmora.webservicesoap.ui.fragments.GroupFragment;
import com.example.jmora.webservicesoap.ui.fragments.MainFragment;

import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spannable text;
    boolean fragmentMain = true;
    EmpleadoAdapter eAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        changeFragment(new MainFragment());
        changeTitleActionBar("Jem Application");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initListView();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!fragmentMain){
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                changeFragment(new MainFragment());
                changeTitleActionBar("JEM Application");
                fragmentMain = true;

            } else{
                LoadOf();
            }
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_empleados) {
            changeFragment(new EmpleadosFragment());
            changeTitleActionBar("Empleados");
            fragmentMain = false;
        } else if (id == R.id.nav_galeria) {

        } else if (id == R.id.nav_video) {

        } else if (id == R.id.nav_configuracion) {

        } else if (id == R.id.nav_Compartir) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void changeFragment(Fragment targetFragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    public void changeTitleActionBar(CharSequence sequence){
        if (sequence != null) {
            text = new SpannableString(sequence);
        }else{
            text = new SpannableString(getResources().getString(R.string.title_activity_navigation_drawer));
        }
        text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorWhite)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        text.setSpan(boldSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        this.getSupportActionBar().setTitle(text);
    }
}
