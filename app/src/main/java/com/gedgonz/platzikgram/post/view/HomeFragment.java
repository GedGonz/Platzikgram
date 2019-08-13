package com.gedgonz.platzikgram.post.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gedgonz.platzikgram.R;
import com.gedgonz.platzikgram.adapter.PictureAdapterRecyclerView;
import com.gedgonz.platzikgram.model.Picture;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;
    private String photPathTemp = "";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false,view);
        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);

        fabCamera = (FloatingActionButton) view.findViewById(R.id.fabCamera);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);
        PictureAdapterRecyclerView pictureAdapterRecyclerView
                = new PictureAdapterRecyclerView(buildPictures(),R.layout.cardview_picture, getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        return view;
    }

    private void takePicture() {
        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Verifica que si el dispositivo tiene camara!
        if(intentTakePicture.resolveActivity(getActivity().getPackageManager())!=null)
        {
            File photoFile = null;

            try
            {

                photoFile = createImageFile();
            }
            catch(Exception e)
            {
                e.printStackTrace();

            }
            if(photoFile!=null)
            {

                Uri photoUri = FileProvider.getUriForFile(getActivity(), "com.gedgonz.platikgram", photoFile);
                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intentTakePicture, REQUEST_CAMERA);
            }

            ;
        }
    }

    private File createImageFile() throws IOException {


            String tomeStamp = new SimpleDateFormat("YYYYMMdd_HH-mm-ss").format(new Date());
            String imageFileName = "JPEG_" + tomeStamp + "_";
            File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

            File photo = File.createTempFile(imageFileName, ".jpg", storageDir);
            
            photPathTemp = "file:"+ photo.getAbsolutePath();

            return photo;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAMERA  && resultCode == getActivity().RESULT_OK)
        {
            Log.d("HomeFragment","Camera Ok!! :)");
            Intent i = new Intent(getActivity(), NewPostActivity.class);
            i.putExtra("PHOTO_PATH_TWMPO", photPathTemp);
            startActivity(i);
        }
    }

    public ArrayList<Picture> buildPictures()
    {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://img.culturacolectiva.com/content/2015/12/fotos-de-viaje.jpg","Gerald Gonzalez", "4 días","3 Me Gusta"));
        pictures.add(new Picture("http://www.bocalista.com/wp-content/uploads/2014/11/photo.jpg","Helen Morales", "1 días","100 Me Gusta"));
        pictures.add(new Picture("https://smoda.elpais.com/wp-content/uploads/images/201544/beautiful_destinations_9781.jpg","Rosario Zeledon", "2 días","10 Me Gusta"));

        return pictures;
    }

    public void showToolbar(String title,boolean upButton, View view)
    {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
