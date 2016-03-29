package suwonsmartapp.com.mediaplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import suwonsmartapp.com.mediaplayer.R;

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
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contents, new AudioFragment())
                        .addToBackStack("audio")
                        .commit();
                break;

            case R.id.video_card:
                break;
        }
    }
}
