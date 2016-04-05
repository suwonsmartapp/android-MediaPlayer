package suwonsmartapp.com.mediaplayer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import suwonsmartapp.com.mediaplayer.R;
import suwonsmartapp.com.mediaplayer.activities.MediaListActivity;
import suwonsmartapp.com.mediaplayer.activities.VideoActivity;

public class MainFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.audio_card).setOnClickListener(this);
        view.findViewById(R.id.video_card).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audio_card:
                View view = getView().findViewById(R.id.imageView);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                        view,
                        "share");

                ActivityCompat.startActivity(getActivity(), new Intent(getActivity(), MediaListActivity.class), options.toBundle());
                break;

            case R.id.video_card:

                startActivity(new Intent(getActivity(), VideoActivity.class));
                break;
        }
    }
}
