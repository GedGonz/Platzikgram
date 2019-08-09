package com.gedgonz.platzikgram.view.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gedgonz.platzikgram.R;
import com.gedgonz.platzikgram.adapter.PictureAdapterRecyclerView;
import com.gedgonz.platzikgram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private int numberOfColums = 2;
    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        showToolbar("", false, view);

        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureSearchRecycler);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColums);

        picturesRecycler.setLayoutManager(gridLayoutManager);

        PictureAdapterRecyclerView picturesAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());

        picturesRecycler.setAdapter(picturesAdapterRecyclerView);

        return view;
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://img.culturacolectiva.com/content/2015/12/fotos-de-viaje.jpg","Gerald Gonzalez", "4 días","3 Me Gusta"));
        pictures.add(new Picture("http://www.bocalista.com/wp-content/uploads/2014/11/photo.jpg","Helen Morales", "1 días","100 Me Gusta"));
        pictures.add(new Picture("https://smoda.elpais.com/wp-content/uploads/images/201544/beautiful_destinations_9781.jpg","Rosario Zeledon", "2 días","10 Me Gusta"));
         return pictures;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


}
