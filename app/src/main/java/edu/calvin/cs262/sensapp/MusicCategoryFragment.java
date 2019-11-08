package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicCategoryFragment extends Fragment implements MusicButtonView.OnClickListener {
    private Context context;
    private MusicButtonView musicButtonView;
    private RecyclerView recyclerView;

    public MusicCategoryFragment() {
        // must have an empty public constructor, or else get the error "MusicCategoryFragment must be a public static class to be  properly recreated from instance state"
    }

    /**
     * create the music category fragment
     *
     * @param savedInstanceState bundle of data
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the necessary resources to check which tab I am.
        context = getContext();

        //TODO: build the adapter for this fragment's recycler view

    }

    /**
     * When the view is created, inflate the container and do other setup
     * @param inflater inflates the layout
     * @param container tells which viewgroup the fragment belongs to
     * @param savedInstanceState data bundle
     * @return the established layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View frag_layout = inflater.inflate(R.layout.fragment_music_category, container, false);
        //build the RecyclerView for this fragment and provide its adapter
        musicButtonView = frag_layout.findViewById(R.id.textView);
        recyclerView = frag_layout.findViewById(R.id.musicButtonHolder);
        getData();

        return frag_layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        musicButtonView.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        musicButtonView.setOnClickListener(null);  // stop listening for button clicks because the button can't be clicked
    }

    /**
     * examine which instance of MusicCategoryFragment this is, and select which data to use.
     *
     * @author cmd16
     */
    private synchronized void getData() {
        //check which tab I am based on the tab name and what PagerAdapter.java told me I am

        final String category_label = getArguments().getString("Sound_category");

        if (category_label.equals(context.getString(R.string.all_sounds_label))) {
            musicButtonView.makeMusicButton(R.drawable.cat,
                    R.raw.cat_purring, context.getString(R.string.all_sounds_label));
        } else if (category_label.equals(context.getString(R.string.animal_sounds_label))) {
            musicButtonView.makeMusicButton(R.drawable.cat,
                    R.raw.cat_purring, context.getString(R.string.animal_sounds_label));
        } else if (category_label.equals(context.getString(R.string.nature_sounds_label))) {
            musicButtonView.makeMusicButton(R.drawable.forest,
                    R.raw.forest, context.getString(R.string.nature_sounds_label));
        } else if (category_label.equals(context.getString(R.string.water_sounds_label))) {
            musicButtonView.makeMusicButton(R.drawable.cat,
                    R.raw.cat_purring, context.getString(R.string.all_sounds_label));
        } else if (category_label.equals(context.getString(R.string.music_sounds_label))) {
            musicButtonView.makeMusicButton(R.drawable.cat,
                    R.raw.cat_purring, context.getString(R.string.all_sounds_label));
        } else if (category_label.equals(context.getString(R.string.city_sounds_label))) {
            musicButtonView.makeMusicButton(R.drawable.cat,
                    R.raw.cat_purring, context.getString(R.string.all_sounds_label));
        } else {
            //If I am being used for something else and haven't been informed of that, then I shouldn't be created at all!
            throw new RuntimeException("ERROR: tab fragment created for undetermined purpose.");
        }
    }

    @Override
    public void onClick(View view) {
        musicButtonView.playPause();
    }
}
