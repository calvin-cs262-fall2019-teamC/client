package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A {@link Fragment} subclass that holds a {@link RecyclerView} for {@link MusicButtonView}s
 * for sounds in a certain category
 */
public class MusicCategoryFragment extends Fragment{
    private Context context;
    private RecyclerView recyclerView;
    private MusicRecyclerAdapter musicRecyclerAdapter;

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
        getData();
    }

    /**
     * When the view is created, inflate the container and do other setup
     *
     * @param inflater           inflates the layout
     * @param container          tells which ViewGroup the fragment belongs to
     * @param savedInstanceState data bundle
     * @return the established layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View frag_layout = inflater.inflate(R.layout.fragment_music_category, container, false);
        //build the RecyclerView for this fragment and provide its adapter
        recyclerView = frag_layout.findViewById(R.id.musicButtonHolder);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(musicRecyclerAdapter);

        return frag_layout;
    }

    /**
     * examine which instance of MusicCategoryFragment this is, and select which data to use.
     *
     * @author cmd16
     */
    private synchronized void getData() {

        assert getArguments() != null;
        final String category_label = getArguments().getString("Sound_category");
        List<MusicButtonData> list = MusicButtonFactory.getInstance().getMusicButtonData(category_label);

        if (musicRecyclerAdapter == null) {
            musicRecyclerAdapter = new MusicRecyclerAdapter(context, list);
        }
    }
}
