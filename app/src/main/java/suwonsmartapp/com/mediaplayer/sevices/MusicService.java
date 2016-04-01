package suwonsmartapp.com.mediaplayer.sevices;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;

public class MusicService extends IntentService {

    private MediaPlayer mMediaPlayer;

    public MusicService() {
        super("MusicService");

        mMediaPlayer = new MediaPlayer();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri uri = intent.getParcelableExtra("uri");
        try {
            mMediaPlayer.setDataSource(getApplicationContext(), uri);
            mMediaPlayer.prepare();

            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            } else {
                mMediaPlayer.start();
            }

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "문제가 있습니다", Toast.LENGTH_SHORT).show();
        }
    }

}
