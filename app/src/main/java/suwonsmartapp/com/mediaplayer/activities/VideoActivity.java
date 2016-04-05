package suwonsmartapp.com.mediaplayer.activities;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import suwonsmartapp.com.mediaplayer.R;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
            Uri uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
            videoView.setVideoURI(uri);
            MediaController controller = new MediaController(this);
            videoView.setMediaController(controller);
            videoView.start();
        }


    }
}
