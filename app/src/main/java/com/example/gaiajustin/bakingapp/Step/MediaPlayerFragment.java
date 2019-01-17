package com.example.gaiajustin.bakingapp.Step;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaiajustin.bakingapp.R;

public class MediaPlayerFragment extends Fragment {

    private static final String TAG = MediaPlayerFragment.class.getSimpleName();


    public static MediaPlayerFragment newInstance() {
        return new MediaPlayerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_media_player, container, false);


        return view;
    }



}
