package suwonsmartapp.com.mediaplayer.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import suwonsmartapp.com.mediaplayer.R;

/**
 * Created by junsuk on 16. 3. 29..
 */
public class AudioFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = AudioFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_media_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        MyAudioAdapter adapter = new MyAudioAdapter(getActivity().getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null,
                        null,
                        null,
                        null));
        adapter.setOnItemClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
    }

    private static class MyAudioAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private AdapterView.OnItemClickListener mListener;

        private Cursor mCursor;

        public MyAudioAdapter(Cursor cursor) {
            mCursor = cursor;
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
            mListener = listener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_audio, parent, false);
            final MyViewHolder holder = new MyViewHolder(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(null, itemView, holder.getAdapterPosition(), getItemId(holder.getAdapterPosition()));
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            mCursor.moveToPosition(position);
            final Cursor cursor = mCursor;

            holder.id.setText("" + position);
            holder.title.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            MediaStore.Audio.Media.TITLE)));
            holder.time.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            MediaStore.Audio.Media.DURATION)));
        }

        @Override
        public int getItemCount() {
            if (mCursor == null) {
                return 0;
            }
            return mCursor.getCount();
        }

        public void swapCursor(Cursor cursor) {
            mCursor = cursor;
            notifyDataSetChanged();
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView time;

        public MyViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.id_text);
            title = (TextView) itemView.findViewById(R.id.title_text);
            time = (TextView) itemView.findViewById(R.id.time_text);
        }
    }
}
