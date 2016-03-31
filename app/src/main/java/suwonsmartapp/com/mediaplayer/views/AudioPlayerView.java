package suwonsmartapp.com.mediaplayer.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import suwonsmartapp.com.mediaplayer.R;
import suwonsmartapp.com.mediaplayer.models.AudioInfo;

public class AudioPlayerView extends LinearLayout {

    private final Context mContext;
    private TextView mTitleText;
    private TextView mArtistText;
    private ImageView mImageView;

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
        setTitle(audioInfo.getTitle());
        setArtist(audioInfo.getArtist());
        setImage(audioInfo.getImage());
    }
}
