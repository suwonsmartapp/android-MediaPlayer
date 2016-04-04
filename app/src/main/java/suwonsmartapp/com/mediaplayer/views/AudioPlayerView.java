package suwonsmartapp.com.mediaplayer.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.media.MediaMetadataCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import suwonsmartapp.com.mediaplayer.R;
import suwonsmartapp.com.mediaplayer.models.AudioInfo;
import suwonsmartapp.com.mediaplayer.sevices.MusicService;

public class AudioPlayerView extends LinearLayout implements View.OnClickListener {

    private final Context mContext;
    private TextView mTitleText;
    private TextView mArtistText;
    private ImageView mImageView;
    private ImageButton mPlayButton;

    private AudioInfo mAudioInfo;

    private boolean isPlaying = false;

    public AudioPlayerView(Context context) {
        this(context, null);
    }

    public AudioPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AudioPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.audio_player, this, true);
        mTitleText = (TextView) view.findViewById(R.id.title_text);
        mArtistText = (TextView) view.findViewById(R.id.artist_text);
        mImageView = (ImageView) view.findViewById(R.id.imageView);
        mPlayButton = (ImageButton) view.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(this);
    }

    public void setTitle(String title) {
        mTitleText.setText(title);
    }

    public void setArtist(String artist) {
        mArtistText.setText(artist);
    }

    public void setImage(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    public void setAudioInfo(AudioInfo audioInfo) {
        mAudioInfo = audioInfo;

        setTitle(audioInfo.getTitle());
        setArtist(audioInfo.getArtist());
        setImage(audioInfo.getImage());
    }

    @Override
    public void onClick(View v) {
        isPlaying = !isPlaying;
        if (isPlaying) {
            v.setBackgroundResource(R.drawable.ic_pause_black_24dp);
        } else {
            v.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
        }

        MediaMetadataCompat metadataCompat = new MediaMetadataCompat.Builder()
                .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, mAudioInfo.getImage())
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, mAudioInfo.getTitle())
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, mAudioInfo.getArtist())
                .build();

        Intent intent = new Intent(mContext, MusicService.class);
        intent.putExtra("uri", mAudioInfo.uri);
        intent.putExtra("metadata", metadataCompat);
        mContext.startService(intent);
    }
}
