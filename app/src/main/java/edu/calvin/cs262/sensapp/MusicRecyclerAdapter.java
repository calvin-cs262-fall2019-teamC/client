package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
        holder.makeMusicButton(data.getDrawableID(), data.getStringID());
    }

    /**
     * Class to hold MusicButtons in a RecyclerView
     */
    public class MusicButtonHolder extends RecyclerView.ViewHolder implements MusicButtonView.OnClickListener {
        private MusicButtonView musicButtonView;
        private final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        private static final String SOUND_BUTTON_CLICKED = "sound button clicked";

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
         *  @param drawableID int ID of drawable image
         * @param stringID ID for String resource of text label
         */
        public void makeMusicButton(int drawableID, int stringID) {
            musicButtonView.makeMusicButton(drawableID, stringID);
        }

        /**
         * Play or pause music upon click
         *
         * @param view View clicked
         */
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SOUND_BUTTON_CLICKED);
            intent.putExtra("button_clicked", musicButtonView.getText());
            broadcastManager.sendBroadcast(intent);
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
