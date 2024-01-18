package com.example.kanji_n5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class usageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public usageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment usageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static usageFragment newInstance(String param1, String param2) {
        usageFragment fragment = new usageFragment();
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
    private YouTubePlayerView youTubePlayerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_usage, container, false);
        youTubePlayerView = view.findViewById(R.id.youtubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);

        Bundle args = getArguments();
        String videoUrl = args.getString("usage");

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                // Load and play the video
                if(videoUrl!=null) {
                    youTubePlayer.loadVideo(getVideoIdFromUrl(videoUrl), 0);
                }
            }
        });
        return view;
    }
    private String getVideoIdFromUrl(String url) {
        // Extract video ID from the YouTube video URL
        // This might vary depending on your requirements
        return url.substring(url.lastIndexOf("=") + 1);
    }

    public void onDestroy() {
        if (youTubePlayerView != null) {
            youTubePlayerView.release(); // Release resources related to the YouTube player
        }
        super.onDestroy();
    }
}