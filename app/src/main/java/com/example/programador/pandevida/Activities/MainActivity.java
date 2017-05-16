package com.example.programador.pandevida.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.programador.pandevida.Fragments.LecturaFragment;
import com.example.programador.pandevida.Fragments.TabFragment;
import com.example.programador.pandevida.Interfaces.InterfazComunicacionMainActivity;
import com.example.programador.pandevida.R;
import com.example.programador.pandevida.Utils.Constantes;
import com.example.programador.pandevida.basesdedatos.Biblia;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        InterfazComunicacionMainActivity {

    // variables que se usan globalmente
    public static Biblia biblia= new Biblia();

    //ToDo quitar de static todo lo que pueda causar problemas

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.Toolbar_Title)
    TextView Toolbar_Title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //toggle.setDrawerIndicatorEnabled(false);
        //toolbar.setNavigationIcon(null);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences prefs = getSharedPreferences(Constantes.ULTIMA_LECTURA_PREFERENCE, MODE_PRIVATE);
        biblia.setLibro(prefs.getString("Libro", "Génesis"));
        biblia.setOsis(prefs.getString("Osis", "Gen"));
        biblia.setCapitulo(prefs.getInt("Capitulo", 1));
        biblia.setVerso(prefs.getInt("Verso", 1));

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,LecturaFragment.NewInstance(this)).commit();

        /**
         * Setup click events on the Navigation View Items.
         */

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.fab)
    public void mostrarIndice(){
        Log.d("Sandro", "Main Activity: onclick FAB");
        mostrarFragment("Tabs");
    }
//----------------------------- Metodos Propios ----------------------------------------------------
    public void mostrarFragment(String tipo){
        Log.d("Sandro", "Cambio al Fragment "+tipo);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        switch (tipo){

            case Constantes.FRAGMENT_LECTURA:{
                mFragmentTransaction.replace(R.id.containerView, LecturaFragment.NewInstance(this)).commit();
                fab.setVisibility(View.VISIBLE);
                break;
            }

            case Constantes.FRAGMENT_TABS:{
                mFragmentTransaction.replace(R.id.containerView, TabFragment.NewInstance(this)).commit();
                fab.setVisibility(View.GONE);
                break;
            }

        }

    }

//------------------------------- Metodos de la Interfaz -------------------------------------------

    @Override
    public void IrAFragment(String fragment) {
        mostrarFragment(fragment);
    }

    @Override
    public void CambiarTitulo(String titulo) {
        //toolbar.setTitle(titulo);
        toolbar.setTitle("");
        Toolbar_Title.setText(titulo);

    }

    @Override
    public void CambiarToolbar(Boolean visible){

        if(visible){
            toolbar.setVisibility(View.VISIBLE);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }else {
        toolbar.setVisibility(View.GONE);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }
}
