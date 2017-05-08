package com.example.programador.pandevida;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.programador.pandevida.R;

import butterknife.ButterKnife;


/**
 * Created by angel20125 on 24/4/17.
 */

public class FavoritoFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFavoritoFragment = inflater.inflate(R.layout.favorito_layout, container,false);

        ButterKnife.bind(this, viewFavoritoFragment);

        //para esconder la vista
        // viewLecturaFragment.setVisibility(View.INVISIBLE);
        //viewLecturaFragment.setVisibility(View.GONE);

        return viewFavoritoFragment;

    }

}
