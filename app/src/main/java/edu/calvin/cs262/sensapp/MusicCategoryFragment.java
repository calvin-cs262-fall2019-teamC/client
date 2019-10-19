package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MusicCategoryFragment extends Fragment {
    private Context context;
    private TextView textView;

    public MusicCategoryFragment() {
        // must have an empty public constructor, or else get the error "MusicCategoryFragment must be a public static class to be  properly recreated from instance state"
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the necessary resources to check which tab I am.
        context = getContext();

        //build the adapter for this fragment's recycler view

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View frag_layout = inflater.inflate(R.layout.fragment_music_category, container, false);
        //build the RecyclerView for this fragment and provide its adapter
        textView = frag_layout.findViewById(R.id.textView);
        getData();

        return frag_layout;
    }

    private synchronized void getData() {
        //check which tab I am based on the tab name and what PagerAdapter.java told me I am

        final String category_label = getArguments().getString("Sound_category");

        if (category_label.equals(context.getString(R.string.all_sounds_label))) {
            textView.setText(category_label);
        } else if (category_label.equals(context.getString(R.string.animal_sounds_label))) {
            textView.setText(category_label);
        } else if (category_label.equals(context.getString(R.string.nature_sounds_label))) {
            textView.setText(category_label);
        } else if (category_label.equals(context.getString(R.string.water_sounds_label))) {
            textView.setText(category_label);
        } else if (category_label.equals(context.getString(R.string.music_sounds_label))) {
            textView.setText(category_label);
        } else if (category_label.equals(context.getString(R.string.city_sounds_label))) {
            textView.setText(category_label);
        } else {
            //If I am being used for something else and haven't been informed of that, then I shouldn't be created at all!
            throw new RuntimeException("ERROR: tab fragment created for undetermined purpose.");
        }
    }
}
