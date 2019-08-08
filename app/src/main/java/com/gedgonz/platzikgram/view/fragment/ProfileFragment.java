package com.gedgonz.platzikgram.view.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        showToolbar("",false,view);

        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);
        PictureAdapterRecyclerView pictureAdapterRecyclerView
                = new PictureAdapterRecyclerView(buildPictures(),R.layout.cardview_picture, getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);
        return view;
    }

    private ArrayList<Picture> buildPictures()
    {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://img.culturacolectiva.com/content/2015/12/fotos-de-viaje.jpg","Gerald Gonzalez", "4 días","3 Me Gusta"));
        pictures.add(new Picture("http://www.bocalista.com/wp-content/uploads/2014/11/photo.jpg","Helen Morales", "1 días","100 Me Gusta"));
        pictures.add(new Picture("https://smoda.elpais.com/wp-content/uploads/images/201544/beautiful_destinations_9781.jpg","Rosario Zeledon", "2 días","10 Me Gusta"));

        return pictures;
    }
    private  void showToolbar(String title,boolean upButton, View view)
    {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
