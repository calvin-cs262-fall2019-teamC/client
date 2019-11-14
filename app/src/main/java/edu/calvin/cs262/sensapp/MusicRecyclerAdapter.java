package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A PagerAdapter for {@link MusicCategoryFragment}, so we can navigate to different pages of music categories
 */
public class MusicRecyclerAdapter extends RecyclerView.Adapter<MusicRecyclerAdapter.MusicButtonHolder> {
    private Context context;
    private List<MusicButtonData> musicButtonData;

//    public interface MusicButtonClickInterface {
//
//    }

    public MusicRecyclerAdapter(@NonNull Context app_context, @NonNull List<MusicButtonData> musicbuttondata_list) {
        super();
        context = app_context;
        musicButtonData = musicbuttondata_list;
    }

    @NonNull
    @Override
    public MusicButtonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.music_button;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachtoParentImmediately = false;
        View view = inflater.inflate(layout, parent, shouldAttachtoParentImmediately);
        return new MusicButtonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicButtonHolder holder, int position) {
        MusicButtonData data = musicButtonData.get(position);
        holder.makeMusicButton(data.getDrawableID(), data.getAudioID(), data.getLabel());
    }

    public class MusicButtonHolder extends RecyclerView.ViewHolder implements MusicButtonView.OnClickListener {
        private MusicButtonView musicButtonView;

        public MusicButtonHolder(@NonNull View itemView) {
            super(itemView);
            musicButtonView = itemView.findViewById(R.id.musicButton);
            musicButtonView.setOnClickListener(this);
        }

        public void makeMusicButton(int drawableID, int audioID, String label) {
            musicButtonView.makeMusicButton(drawableID, audioID, label, context);
        }

        @Override
        public void onClick(View view) {
            musicButtonView.playPause();
        }
    }

    @Override
    public int getItemCount() {
        return musicButtonData.size();
    }

    public void setMusicButtonViewList(List<MusicButtonData> musicbuttondata_list) {
        musicButtonData = musicbuttondata_list;
    }
}
