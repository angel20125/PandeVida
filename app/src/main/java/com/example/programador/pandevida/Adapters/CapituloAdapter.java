package com.example.programador.pandevida.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.programador.pandevida.Interfaces.InterfazComunicacionTabs;
import com.example.programador.pandevida.R;
import java.util.List;
import static com.example.programador.pandevida.MainActivity.biblia;



public class CapituloAdapter extends RecyclerView.Adapter<CapituloAdapter.CapituloViewHolder>{

    //Atriburtos
    List<String> capitulos;
    InterfazComunicacionTabs interfaz;

    //Constructor
    /*
        Constructutor de CapituloAdapter, su fucnion  es:  recibir por parametro, los datos que se van
        a mostrar en este caso los libros.
        y la InterfazComunicacionTabs, que es la encargada de comunicar los fragmentos
    */
    public CapituloAdapter(List<String> capitulos, InterfazComunicacionTabs interfaz) {
        this.capitulos = capitulos;
        this.interfaz = interfaz;
    }
    //Clase
    /*
        CapituloAdapter que extiende de RecyclerView.ViewHolder, esto  un "sostendor", para la vista
        de los item que estan diseñados en capitulo_item, se recuerda, que estos, son los pre-diseños
        de los items  que se mostraran en capitulo_layout
    */
    public class CapituloViewHolder extends RecyclerView.ViewHolder{
        //Atributos
        TextView numero_capitulo;
        FrameLayout fondo;
        //Constructor
        public CapituloViewHolder(View itemView) {
            super(itemView);
            /*
             enlace logico de los elementos que componen capitulo_item, que son
               los pre-diseños de los items que se mostraran en Capitulo_Layout
            */
            //numero_capitulo= ButterKnife.findById(itemView,R.id.capituloTV);//con ButterKnife
            //fondo=ButterKnife.findById(itemView,R.id.layoutCapitulo);//con ButterKnife
            numero_capitulo= (TextView) itemView.findViewById(R.id.capituloTV);
            fondo= (FrameLayout) itemView.findViewById(R.id.layoutCapitulo);
        }
    }
    @Override
    public CapituloViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Se hace el enlace logico, y se infla la vista del layout libro_item
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.capitulo_item, viewGroup, false);
        // Se crea la variable  "holder" de  tipo "LibroViewHolder" y le pasa ṕor parametro la vista
        Integer numero = new Integer(0);
        final CapituloViewHolder holder = new CapituloViewHolder(v);



        return holder;
    }
    //Asigna la informacion a los componentes del Holder
    @Override
    public void onBindViewHolder(CapituloViewHolder holder, final int position) {
            holder.numero_capitulo.setText(capitulos.get(position));

        /*
        *En esta seccion se hace que la variable fondo que es  contenedor Framelayout  reacione al evento de tipo click y
        *tambien en esta seccion es donde se envia la informacion atraves de la InterfazComunicacionTabs para que los otros
        *fragmentos puedan recibir la informacion.
        */

        /**
         * Nota: se usa la variable fondo, que es  un contenedor de tipo FrameLayout dentro de capitulo_item
         * para definir el evento del click, debido a que es mas funcional ya cuando se haga click
         * en una posicion de la pantalla que este vacio pero hay un texto al lado tomara el texto igualmente.
         * mientras que si el evento fuera sobre el TextView entonces se tiene hacer el click obligatoriamente sobre el el texto
         */
        holder.fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Your stuff here
                if(interfaz!=null){
                    Log.v("CapituloAdapter","CapituloAdapter Click al item! "+ (position+1));
                    biblia.setCapitulo(position+1);
                    Log.v("CapituloAdapter","DATOS SON  "+ biblia.getCapitulo(), null);
                    interfaz.IrAVersiculo(position+1);//donde se envia la informacion atraves de interfaz
                }else{
                    Log.v("CapituloAdapter","Interfaz nula!");
                }
            }
        });

    }

    // importante si retorna  cero no se muestra nada
    @Override
    public int getItemCount() {
        //Log.v("Angel"," tamaño de la lista en CAPITULO ADAPTER "+capitulos.size() );
        //return 0
        return capitulos.size();
    }




}
// consulta para obternewr el ultimo verso
/*
*                     try {

                            MyDataBase mdb = new MyDataBase(biblia.getApplicationContext(), 0);
                            SQLiteDatabase db = mdb.getWritableDatabase();
                            Cursor cursor;
                            cursor = db.rawQuery("SELECT book, chapter, verse  FROM 'bible_fts'"+"WHERE"
                                    +"book"+"="+"'"+biblia.getLibro()+"'"
                                    +"AND"
                                    +"chapter"+"="+"'"+ getCapitulo()+"'",null);
                            cursor.moveToFirst();
                            cursor.moveToLast();
biblia.setVersoFinal(cursor.getInt(2));

Log.v("Angel CapituloAdapter","EL ULTIMO VERSO : "+biblia.getVersoFinal());

                            db.close();
                    }catch (Exception e) {

                    }
*/