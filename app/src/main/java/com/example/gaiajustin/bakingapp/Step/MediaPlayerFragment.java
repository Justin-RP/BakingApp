package com.example.gaiajustin.bakingapp.Step;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gaiajustin.bakingapp.Utils.ImageRequester;
import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MediaPlayerFragment extends Fragment {

    private static final String TAG = MediaPlayerFragment.class.getSimpleName();


    public static MediaPlayerFragment newInstance() {
        return new MediaPlayerFragment();
    }

    StepViewModel stepViewModel;
    CakeViewModel mViewModel;

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    List<Cake> cakeList;

    private ImageRequester imageRequester;

    int currentCake;
    int currentStep;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_media_player, container, false);

        exoPlayerView = view.findViewById(R.id.media_exo_player_view);
        imageRequester = ImageRequester.getInstance();

        stepViewModel = ViewModelProviders.of(getActivity()).get(StepViewModel.class);
        mViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);


        //TODO I need CakeId, StepId




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        stepViewModel.getStep().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.d(TAG, "onChanged: CAN I " + integer);
                currentStep = integer;
                if (cakeList!=null) {
                    loadChange();
                }


            }
        });
        stepViewModel.getCakeId().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                currentCake = integer;
            }
        });
        mViewModel.getCakes().observe(getViewLifecycleOwner(), new Observer<List<Cake>>() {
            @Override
            public void onChanged(@Nullable List<Cake> cakes) {
                cakeList = cakes;
                loadChange();
            }
        });

    }

    private void loadChange() {
        String videoUrl = cakeList.get(currentCake).getSteps().get(currentStep).getVideoURL();
        String thumbnailURL = cakeList.get(currentCake).getSteps().get(currentStep).getThumbnailURL();
        // TODO THUMBNAIL
//        Picasso.get().load("http://image.tmdb.org/t/p/w185/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg").into(exoPlayerView);

        if ( videoUrl == null || videoUrl.equals("") ){
            Log.d(TAG, "loadChange: NoVideo");
            Toast.makeText(getContext(), "There is no Video", Toast.LENGTH_SHORT).show();
            exoPlayerView.setVisibility(View.INVISIBLE);
        } else {
            exoPlayerView.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "Please wait the video is loading", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onChanged: CurrentCake" + videoUrl );
            // TODO DO I NEED IMAGE REQUESTER?
//            imageRequester.setImageFromUrl(holder.productImage, cake.getImageURL());

            try {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveVideoTrackSelection.Factory(bandwidthMeter));
                LoadControl loadControl = new DefaultLoadControl();
                exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
                exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
                Uri videoURI = Uri.parse(videoUrl);

                DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

                exoPlayerView.setPlayer(exoPlayer);
                exoPlayer.prepare(mediaSource);
                exoPlayer.setPlayWhenReady(true);
            } catch (Exception e) {
                Log.e(TAG, " exoplayer error " + e.toString());
            }
        }

    }

}
