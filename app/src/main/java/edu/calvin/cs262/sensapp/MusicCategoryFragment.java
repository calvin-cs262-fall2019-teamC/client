package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
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

//    public int dpToPx(int dp) {
//        float density = Context.getResources()
//                .getDisplayMetrics()
//                .density;
//        return Math.round((float) dp * density);
//    }

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
        recyclerView = frag_layout.findViewById(R.id.musicButtonHolder);
//        LinearLayout layout = new LinearLayout(context);
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)layout.getLayoutParams();
//        layoutParams.height = dpToPx(250);
//        layoutParams.width = dpToPx(250);
//        layout.setLayoutParams(layoutParams);
        //itemView.setLayoutParams(new RecyclerView.LayoutParams(width/3, RecyclerView.LayoutParams.WRAP_CONTENT));

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

        final String category_label = getArguments().getString("Sound_category");

        List<MusicButtonData> list = MusicButtonFactory.getInstance().getMusicButtonData(category_label);

        if (musicRecyclerAdapter == null) {
            musicRecyclerAdapter = new MusicRecyclerAdapter(context, list);
        }
    }
}
