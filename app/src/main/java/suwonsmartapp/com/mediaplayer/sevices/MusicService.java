package suwonsmartapp.com.mediaplayer.sevices;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.NotificationCompat;

import suwonsmartapp.com.mediaplayer.R;

public class MusicService extends Service {

    private MediaPlayer mMediaPlayer;
    private MediaSessionCompat mSession;

    @Override
    public void onCreate() {
        super.onCreate();

        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() != null) {
            if (intent.getAction().equals("stop")) {
                stopSelf(); // 서비스 종료
                return START_NOT_STICKY;
            }
        }

        MediaMetadataCompat metadataCompat = intent.getParcelableExtra("metadata");
        if (mSession == null) {
            mSession = new MediaSessionCompat(this, "tag", null, null);
            mSession.setMetadata(metadataCompat);
        }

        Intent stopIntent = new Intent(this, MusicService.class);
        stopIntent.setAction("stop");

        PendingIntent pendingIntent = PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentTitle(metadataCompat.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
        builder.setContentText(metadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setShowWhen(false);
        builder.setStyle(new NotificationCompat.MediaStyle()
                .setMediaSession(mSession.getSessionToken())
                .setShowActionsInCompactView(0, 1, 2)
                .setShowCancelButton(true));
        builder.setColor(0xFFDB4437);
        builder.setLargeIcon(metadataCompat.getBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART));
        builder.addAction(R.drawable.ic_pause_black_24dp, "pause", pendingIntent);
        builder.addAction(android.R.drawable.ic_media_previous, "prew", null);
        builder.addAction(android.R.drawable.ic_media_next, "next", null);

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
