package com.example.programador.pandevida.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.programador.pandevida.Interfaces.InterfazComunicacionTabs;
import com.example.programador.pandevida.R;
import com.example.programador.pandevida.basesdedatos.Biblia;
import com.example.programador.pandevida.basesdedatos.Libro;

import java.util.List;


//import static com.example.programador.pandevida.Activities.MainActivity.biblia;
/**
 * Created by angel20125 on 09/04/17.
 */

/**
 *LibroAdapter extiende de un RecyclerView.Adapter es tambien  quien lo implementa
 *la sentencia public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.LibroViewHolder>
 * es debido  a que tiene una clase  interna que se llama LibroViewHolder
 */

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.LibroViewHolder>{
    //Atributos
    List<Libro> libros;
    InterfazComunicacionTabs interfaz;

    //Constructutor de LibroAdapter, su fucnion  es:  recibir por parametro, los datos que se van a mostrar en este caso los libros.
    //y la InterfazComunicacionTabs, que es la encargada de comunicar los fragmentos
    public LibroAdapter(List<Libro> libros, InterfazComunicacionTabs interfaz) {
        this.libros = libros;
        this.interfaz = interfaz;
    }
    //--------------------------clase------------------------------------------
    // LibroAdapter que extiende de RecyclerView.ViewHolder, esto  un "sostendor",
    public class LibroViewHolder extends RecyclerView.ViewHolder {

        TextView nombre_Libro;
        LinearLayout fondo;
        //String Nombre_Consulta
         /*
         *Constructor, tiene como funcion recibir una vista y hacer los enlace lógico de un contendor  tipo
         *FrameLayout, con la variable "fondo", y la variable nombre_Libro con un  TextView, estas dos  item se
         *encuentran dentro de layout item_libro
        */
        public LibroViewHolder(View itemView) {
            super(itemView);
            //nombre_Libro = ButterKnife.findById(itemView, R.id.libroTV);//con ButterKnife
            //fondo = ButterKnife.findById(itemView, R.id.layoutLibro);//con ButterKnife
            nombre_Libro= (TextView) itemView.findViewById(R.id.libroTV);
            fondo= (LinearLayout) itemView.findViewById(R.id.layoutLibro);
        }

    }
    /*
        la clase onCreateViewHolder, su fucion es: crear e inflar la vista
        de los libro_item que son los pre-diseños de cada uno de los items se
        van a mostrar demtro layout libro_layout
    */
    @Override
    public LibroViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Se hace el enlace logico, y se infla la vista del layout libro_item
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.libro_item, viewGroup, false);
        // Se crea la variable  "holder" de  tipo "LibroViewHolder" y le pasa ṕor parametro la vista
        final LibroViewHolder holder = new LibroViewHolder(v);


        return holder;

    }
    //Asigna informacion a los componentes  holder, es decir perzonaliza cada libro_item con la informacion  correspondiente
    @Override
    public void onBindViewHolder(LibroViewHolder holder, final int position) {
        holder.nombre_Libro.setText(libros.get(position).getNombre());

        /*
        *En esta seccion se hace que la variable fondo que es  contenedor Framelayout  reacione al evento de tipo click y
        *tambien en esta seccion es donde se envia la informacion atraves de la InterfazComunicacionTabs para que los otros
        *fragmentos puedan recibir la informacion.
        */
        /**
         * Nota: se usa la variable fondo, que es  un contenedor de tipo FrameLayout
         * para definir el evento del click, debido a que es mas funcional ya cuando se haga click
         * en una posicion de la pantalla que este vacio pero hay un texto al lado tomara el texto igualmente.
         * mientras que si el evento fuera sobre el TextView entonces se tiene hacer el click obligatoriamente sobre el el texto
         */

        holder.fondo.setOnClickListener(new View.OnClickListener() {
            //seccion donde define accion se va hacer cuando se haga el evento del click de click
            @Override
            public void onClick(View v) {
                //Your stuff here
                if(interfaz!=null){
                    Log.v("Angel"," "+libros.get(position).getNombre());

                    Biblia.setLibro(libros.get(position).getNombre());
                    Biblia.setOsis(libros.get(position).getOsis());

                    interfaz.IrACapitulo(libros.get(position).getCapitulos());//donde se envia la informacion atraves de interfaz
                }else{
                    Log.v("Angel"," LibroAdpater Interfaz nula!");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return libros.size();
    }


}
