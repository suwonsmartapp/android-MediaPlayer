package suwonsmartapp.com.mediaplayer.models;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

/**
 * Created by junsuk on 16. 3. 31..
 */
public class AudioInfo {
    private static final String TAG = AudioInfo.class.getSimpleName();
    private String title;
    private String artist;
    private String duration;
    private Bitmap image;
    public Uri uri;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AudioInfo(Context context, Cursor cursor) {
//        title = cursor.getString(
//                cursor.getColumnIndexOrThrow(
//                        MediaStore.Audio.Media.TITLE));
//        duration = cursor.getLong(
//                cursor.getColumnIndexOrThrow(
//                        MediaStore.Audio.Media.DURATION));
//        artist = cursor.getString(
//                cursor.getColumnIndexOrThrow(
//                        MediaStore.Audio.Media.ARTIST));

        Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
        this.uri = uri;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(context, uri);
        String album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

        setTitle(title);
        setArtist(artist);
        setDuration(duration);

        // 앨범 사진
        byte albumImage[] = retriever.getEmbeddedPicture();
        if (null != albumImage) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(albumImage, 0, albumImage.length);
            setImage(bitmap);
        }
    }
}
