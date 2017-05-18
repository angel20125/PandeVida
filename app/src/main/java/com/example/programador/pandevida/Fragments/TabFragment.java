package com.example.programador.pandevida.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.programador.pandevida.Interfaces.InterfazComunicacionMainActivity;
import com.example.programador.pandevida.Interfaces.InterfazComunicacionTabs;
import com.example.programador.pandevida.R;
import com.example.programador.pandevida.Utils.Constantes;
import com.example.programador.pandevida.basesdedatos.Biblia;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
//import static com.example.programador.pandevida.Activities.MainActivity.biblia;

/**
 * Created by Ratan on 7/27/2015.
 */
public class TabFragment extends Fragment {

    @BindView(R.id.tabs)
    public TabLayout tabLayout;

    @BindView(R.id.viewpager)
    public ViewPager viewPager;

    @BindView(R.id.CancelarButton)
    public Button CancelarButton;

    private MyAdapter adapter;
    private static InterfazComunicacionMainActivity MainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         *Inflate tab_layout and setup Views.
         */
        View view =  inflater.inflate(R.layout.tab_layout,null);
        ButterKnife.bind(this,view);
        adapter = new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        MainActivity.CambiarTitulo("Pan de Vida");
        MainActivity.CambiarToolbar(false);
        return view;
    }

    public static Fragment NewInstance(InterfazComunicacionMainActivity interfaz) {
        TabFragment fragment = new TabFragment();
        MainActivity = interfaz;

        return fragment;
    }

    @OnClick(R.id.CancelarButton)
    public void CancelarBusqueda(){
        Log.d("Sandro", "CancelarBusqueda");

        SharedPreferences prefs = getActivity().getSharedPreferences(Constantes.ULTIMA_LECTURA_PREFERENCE, MODE_PRIVATE);
        Biblia.setLibro(prefs.getString("Libro", "Génesis"));
        Biblia.setOsis(prefs.getString("Osis", "Gen"));
        Biblia.setCapitulo(prefs.getInt("Capitulo", 1));
        Biblia.setVerso(prefs.getInt("Verso", 1));

        adapter.IrALectura();
    }

    /**
 * la clase "MyAdapter", tiene como funcion: Mostrar los framentos en el tab, y comunicar los fragmentos entre ellos
 * es quien tiene la implementacion de la interface "InterfazComunicacionTabs".
 * es decir la InterfazComunicacionTabs es la encargada de comunicar los fragmentos
* */

    class MyAdapter extends FragmentPagerAdapter implements InterfazComunicacionTabs {
        /*
         * Se declaran los fragmentos a mostrar,y se hace una vez, esto es para no perder la informacion se debe mostrar
         * para pasar los datos  entre ellos, y no perder la informacion mostrada
        */

        LibroFragment       fragmento_libro;
        CapituloFragment    fragmento_capitulo;
        VersiculoFragment fragmento_versiculo;

        //Constructor: tiene como funcion: inicializar los fragmentos
        public MyAdapter(FragmentManager fm) {
            super(fm);
            /*
            En esta seccion se instancia los fragmentos, la sentencia fragmento_capitulo = CapituloFragment.NewInstance(this) POR EJEMPLO
            hace la instancia  del framento capitulo, y le para por parametro  atravez del constructor NewIntance () la insterfaz de comunicacion.
            es decir (this)<-- hace el referecia literalmente  a la interfaz de comunicacion
           */
            fragmento_libro= LibroFragment.NewInstance(this);// instancia el Fragment y se le pasa por parametro la InterfazComunicacionTabs
            fragmento_capitulo = CapituloFragment.NewInstance(this);// instancia el Fragment y se le pasa por parametro la InterfazComunicacionTabs
            fragmento_versiculo = VersiculoFragment.NewInstance(this);//instancia el Fragment y se le pasa por parametro la InterfazComunicacionTabs
            //fragmento_lectura = LecturaFragment.NewInstance(this);//instancia el Fragment y se le pasa por parametro la InterfazComunicacionTabs
        }

        /**
         * Return fragment with respect to Position .
         */

        //getItem, tiene como funcion: Retornar  un Fragmento en la posicion indicada
        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case 0 : return fragmento_libro;
              case 1 : return fragmento_capitulo;
              case 2 : return fragmento_versiculo;
          }

        return null;
        }

        // getCount, tiene como funcion: Retornar  la cantidad de paginas que se mostraran.
        @Override
        public int getCount() {
            return 3;
        }
        /**
         * This method returns the title of the tab according to the position.
         */

        //getPageTitle, su funcion es: Retornar  un titulo en la parte superior del tab
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Libro";
                case 1 :
                    return "Capitulo";
                case 2 :
                    return "Versiculo";

            }
                return null;
        }

        @Override
        public void IrACapitulo(int CantidadDeCapitulos) {
            fragmento_capitulo.mostrarCapitulo(CantidadDeCapitulos); //Aqui le digo al Fragment que se actualice su informacion
            viewPager.setCurrentItem((viewPager.getCurrentItem()+1));

        }

        @Override
        public void IrAVersiculo(int capitulo) {
            Log.v("Angel  en TabFragment", "IrAVersiculo: interfaz!! libro: "+ Biblia.getLibro());
            Log.v("Angel  en TabFragment", "IrAVersiculo: interfaz!! capitulo: "+Biblia.getCapitulo());
            //Decirle al Fragment que actualice su informacion
            fragmento_versiculo.mostrarVersiculo(capitulo);
            viewPager.setCurrentItem((viewPager.getCurrentItem()+1));
        }

        @Override
        public void IrALectura() {
            Log.v("Angel  en TabFragment", "IrAFragment: interfaz!! LLEGO AQUI!!");

            MainActivity.IrAFragment(Constantes.FRAGMENT_LECTURA);
        }

    }



}
