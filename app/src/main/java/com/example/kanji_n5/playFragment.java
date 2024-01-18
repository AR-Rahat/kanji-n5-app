package com.example.kanji_n5;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link playFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class playFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public playFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment playFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static playFragment newInstance(String param1, String param2) {
        playFragment fragment = new playFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private String videoUrl;
    private VideoView videoView;
    private CardView cardView;
    private FloatingActionButton play;
    private FloatingActionButton pause;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        // Inflate the layout for this fragment

        videoView = view.findViewById(R.id.strokeView);
        cardView = view.findViewById(R.id.cardView);
        play= view.findViewById(R.id.videoPlay);
        pause = view.findViewById(R.id.videoPause);

        pause.setVisibility(view.GONE);

        Bundle args = getArguments();
        if (args != null) {
            videoUrl = args.getString("video");
            MediaController mediaController = new MediaController(requireContext());
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(Uri.parse(videoUrl));
            videoView.requestFocus();

            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoView.start();
                    play.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);
                }
            });
            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoView.pause();
                    pause.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    pause.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE); // Show play button
                }
            });
            videoView.setMediaController(null);
        }

        return view;
    }
}