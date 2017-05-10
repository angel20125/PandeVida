package com.example.programador.pandevida;

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
import com.example.programador.pandevida.Interfaces.InterfazComunicacionTabs;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.example.programador.pandevida.MainActivity.biblia;

/**
 * Created by Ratan on 7/27/2015.
 */
public class TabFragment extends Fragment {

    @BindView(R.id.tabs)
    public TabLayout tabLayout;

    @BindView(R.id.viewpager)
    public ViewPager viewPager;

    private InterfazComunicacionTabs interfaz;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         *Inflate tab_layout and setup Views.
         */
        View view =  inflater.inflate(R.layout.tab_layout,null);
        ButterKnife.bind(this,view);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public static Fragment NewInstance() {
        TabFragment fragment = new TabFragment();

        return fragment;
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
        VersiculoFragment   fragmento_versiculo;
        //LecturaFragment     fragmento_lectura;
        //FavoritoFragment    fragmento_favoritos;

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
              //case 3 : return fragmento_lectura;
              //case 4 : return fragmento_favoritos;
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
                /*case 3 :
                   return "Lectura";
                case 4 :
                    return "Favoritos";*/
            }
                return null;
        }

        @Override
        public void IrACapitulo(String libro, int CantidadDeCapitulos) {
            //Log.d("Angel  en TabFragment", "IrACapitulo: interfaz!! Capitulo Final: "+libro);
            fragmento_capitulo.mostrarCapitulo(CantidadDeCapitulos); //Aqui le digo al Fragment que se actualice su informacion
            viewPager.setCurrentItem((viewPager.getCurrentItem()+1));
        }

        @Override
        public void IrAVersiculo(int capitulo) {
            Log.v("Angel  en TabFragment", "IrAVersiculo: interfaz!! libro: "+biblia.getLibro());
            Log.v("Angel  en TabFragment", "IrAVersiculo: interfaz!! capitulo: "+biblia.getCapitulo());
            //Decirle al Fragment que actualice su informacion
            fragmento_versiculo.mostrarVersiculo(capitulo);
            viewPager.setCurrentItem((viewPager.getCurrentItem()+1));
        }

        @Override
        public void IrALectura() {
            Log.v("Angel  en TabFragment", "IrALectura: interfaz!! LLEGO AQUI!!");


        }
    }

}
