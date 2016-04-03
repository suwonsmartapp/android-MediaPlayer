package suwonsmartapp.com.mediaplayer.sevices;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import suwonsmartapp.com.mediaplayer.R;

public class MusicService extends Service {

    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentTitle("재생 중");
        builder.setContentText("아무 음악 제목임");
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Notification notification = builder.build();


//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
//        notificationManagerCompat.notify(1, notification);

        startForeground(1, notification);

        Uri uri = intent.getParcelableExtra("uri");

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        } else {

            mMediaPlayer.start();

        }

        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
