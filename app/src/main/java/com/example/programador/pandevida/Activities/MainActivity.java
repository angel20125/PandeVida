package com.example.programador.pandevida.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.programador.pandevida.Fragments.LecturaFragment;
import com.example.programador.pandevida.Fragments.TabFragment;
import com.example.programador.pandevida.Interfaces.InterfazComunicacionMainActivity;
import com.example.programador.pandevida.R;
import com.example.programador.pandevida.Utils.Constantes;
import com.example.programador.pandevida.basesdedatos.Biblia;
import com.example.programador.pandevida.basesdedatos.Libro;
import com.example.programador.pandevida.basesdedatos.SQLiteManager;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        InterfazComunicacionMainActivity {

    // variables que se usan globalmente
    //public static Biblia biblia= new Biblia();

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

    @BindView(R.id.ToolbarFrame)
    FrameLayout ToolbarFrame;

    @BindView(R.id.Toolbar_Back)
    ImageView Toolbar_Back;

    @BindView(R.id.Toolbar_Next)
    ImageView Toolbar_Next;

    @BindView(R.id.ToolbarLayout)
    AppBarLayout ToolbarLayout;

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
        //new Biblia();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences prefs = getSharedPreferences(Constantes.ULTIMA_LECTURA_PREFERENCE, MODE_PRIVATE);
        Biblia.setLibro(prefs.getString("Libro", "Génesis"));
        Biblia.setOsis(prefs.getString("Osis", "Gen"));
        Biblia.setCapitulo(prefs.getInt("Capitulo", 1));
        Biblia.setVerso(prefs.getInt("Verso", 1));

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
        mostrarFragment(Constantes.FRAGMENT_TABS);
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
        Toolbar_Title.setText(titulo);
    }

    @Override
    public void CambiarToolbar(Boolean visible){

        if(visible){
            ToolbarFrame.setVisibility(View.VISIBLE);
            //ToolbarLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }else {
            //ToolbarLayout.animate().translationY(-ToolbarLayout.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
            ToolbarFrame.setVisibility(View.GONE);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }

    @Override
    public void CambiarBotonNext(Boolean visible) {

        if(visible){
            Toolbar_Next.setVisibility(View.VISIBLE);
        }else {
            Toolbar_Next.setVisibility(View.GONE);
        }
    }

    @Override
    public void CambiarBotonBack(Boolean visible) {

        if(visible){
            Toolbar_Back.setVisibility(View.VISIBLE);
        }else {
            Toolbar_Back.setVisibility(View.GONE);
        }
    }

//--------------------------------------OnClicks----------------------------------------------------

    @OnClick(R.id.Toolbar_Back)
    public void clickedBack(){
        Log.d("Sandro", "clicked back");

        SQLiteManager Bdd = SQLiteManager.getInstance();

        List<Libro> libros = Bdd.getLibros(getApplicationContext());

        //int capitulos=0;
        int /*libro =0,*/ i=0;
        //Busco entre todos los libros hasta llegar al libro en el que estoy
        for(Libro item : libros){
            if(item.getOsis().equals(Biblia.getOsis())){
                //Guardo la cantidad de capitulos que tiene ese Libro
                //capitulos = item.getCapitulos();
                //libro=i;
                break;
            }
            i++;
        }

        //Si aun tiene capitulos por leer, paso al anterior.
        if(Biblia.getCapitulo()>1){
            Biblia.setCapitulo(Biblia.getCapitulo()-1);
            Biblia.setVerso(1);
            Log.d("Sandro", "clickedNext: verso:"+Biblia.getVerso());
        }else{
            //De lo contrario, paso al Libro anterior, capitulo 1, versiculo 1.
            Biblia.setLibro(libros.get(i-1).getNombre());
            Biblia.setOsis(libros.get(i-1).getOsis());
            Biblia.setCapitulo(libros.get(i-1).getCapitulos());
            Biblia.setVerso(1);
            Log.d("Sandro", "clickedBack: verso:"+Biblia.getVerso());
        }
        mostrarFragment(Constantes.FRAGMENT_LECTURA);
    }

    @OnClick(R.id.Toolbar_Next)
    public void clickedNext(){
        Log.d("Sandro", "clicked next");
            SQLiteManager Bdd = SQLiteManager.getInstance();

            List<Libro> libros = Bdd.getLibros(getApplicationContext());

            int capitulos=0;
            int /*libro =0,*/ i=0;
            //Busco entre todos los libros hasta llegar al libro en el que estoy
            for(Libro item : libros){
                if(item.getOsis().equals(Biblia.getOsis())){
                    //Guardo la cantidad de capitulos que tiene ese Libro
                    capitulos = item.getCapitulos();
                    //libro=i;
                    break;
                }
                i++;
            }

            //Si aun tiene capitulos por leer, paso al proximo.
            if(Biblia.getCapitulo()<capitulos){
                Biblia.setCapitulo(Biblia.getCapitulo()+1);
                Biblia.setVerso(1);
                Log.d("Sandro", "clickedNext: verso:"+Biblia.getVerso());
            }else{
                //De lo contrario, paso al proximo Libro, capitulo 1, versiculo 1.
                Biblia.setLibro(libros.get(i+1).getNombre());
                Biblia.setOsis(libros.get(i+1).getOsis());
                Biblia.setCapitulo(1);
                Biblia.setVerso(1);
                Log.d("Sandro", "clickedNext: verso:"+Biblia.getVerso());
            }

            mostrarFragment(Constantes.FRAGMENT_LECTURA);

    }

}
