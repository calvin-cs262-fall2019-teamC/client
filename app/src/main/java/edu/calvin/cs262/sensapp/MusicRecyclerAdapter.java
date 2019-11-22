package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A RecyclerAdapter for {@link MusicButtonView}
 */
public class MusicRecyclerAdapter extends RecyclerView.Adapter<MusicRecyclerAdapter.MusicButtonHolder> {
    private Context context;
    private List<MusicButtonData> musicButtonDataList;

    /**
     * Construct the MusicRecyclerAdapter
     *
     * @param app_context Current Context
     * @param musicbuttondata_list List of MusicButtonData
     */
    public MusicRecyclerAdapter(@NonNull Context app_context, @NonNull List<MusicButtonData> musicbuttondata_list) {
        super();
        context = app_context;
        musicButtonDataList = musicbuttondata_list;
    }

    /**
     * Create ViewHolder for MusicButtons
     *
     * @param parent ViewGroup in which to display
     * @param viewType int of View type
     * @return
     */
    @NonNull
    @Override
    public MusicButtonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.music_button;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachtoParentImmediately = false;
        View view = inflater.inflate(layout, parent, shouldAttachtoParentImmediately);
        return new MusicButtonHolder(view);
    }

    /**
     * Setup the MusicButton in the Holder using the data
     *
     * @param holder MusicButtonHolder
     * @param position int of position in DataList
     */
    @Override
    public void onBindViewHolder(@NonNull MusicButtonHolder holder, int position) {
        MusicButtonData data = musicButtonDataList.get(position);
        holder.makeMusicButton(data.getDrawableID(), data.getAudioID(), data.getLabel());
    }

    /**
     * Class to hold MusicButtons in a RecyclerView
     */
    public class MusicButtonHolder extends RecyclerView.ViewHolder implements MusicButtonView.OnClickListener {
        private MusicButtonView musicButtonView;

        /**
         * Construct the MusicButtonHolder
         *
         * @param itemView View in which MusicButton is
         */
        public MusicButtonHolder(@NonNull View itemView) {
            super(itemView);
            musicButtonView = itemView.findViewById(R.id.musicButton);
            musicButtonView.setOnClickListener(this);
        }

        /**
         * Make a MusicButton
         *
         * @param drawableID int ID of drawable image
         * @param audioID int ID of audio to play
         * @param label String of label text
         */
        public void makeMusicButton(int drawableID, int audioID, String label) {
            musicButtonView.makeMusicButton(drawableID, audioID, label, context);
        }

        /**
         * Play or pause music upon click
         *
         * @param view View clicked
         */
        @Override
        public void onClick(View view) {
            musicButtonView.playPause();
        }
    }

    /**
     * Get the count of MusicData items in the List
     */
    @Override
    public int getItemCount() {
        return musicButtonDataList.size();
    }

}
