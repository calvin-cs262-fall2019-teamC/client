package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A PagerAdapter for {@link MusicCategoryFragment}, so we can navigate to different pages of music categories
 */
public class MusicRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<MusicButtonView> musicButtonViewList;

    public MusicRecyclerAdapter(@NonNull Context app_context, @NonNull List<MusicButtonView> musicbutton_list) {
        super();
        context = app_context;
        musicButtonViewList = musicbutton_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context parent_context = parent.getContext();
        int layout = R.layout.music_button;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachtoParentImmediately = false;
        View view = inflater.inflate(layout, parent, shouldAttachtoParentImmediately);
        return null;  // TODO: return new MusicButtonView
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    private class MusicButtonHolder extends RecyclerView.ViewHolder {
        private MusicButtonView musicButtonView;

        public MusicButtonHolder(@NonNull View itemView, int drawableID, int audioID, String label) {
            super(itemView);
            musicButtonView = new MusicButtonView(context);
            musicButtonView.makeMusicButton(drawableID, audioID, label);
        }

        // TODO: onClick
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setMusicButtonViewList(List<MusicButtonView> musicbutton_list) {
        musicButtonViewList = musicbutton_list;
    }
}
