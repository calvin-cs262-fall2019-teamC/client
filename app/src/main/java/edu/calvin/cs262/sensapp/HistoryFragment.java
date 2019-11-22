package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A {@link Fragment} subclass for showing user history - which activity they used, for how long,
 * and how they liked it
 */
public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryRecyclerAdapter recyclerAdapter;
    private Context context;

    public HistoryFragment() {

    }

    /**
     * When created initialize data.
     *
     * @param savedInstanceState The Bundle of data to initialize.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        getData();
    }

    /**
     * When created place the History Layout in the MainActivity.
     *
     * @param inflater           The LayoutInflater to place the Layout in the MainActivity.
     * @param container          The ViewGroup in which to place the Layout.
     * @param savedInstanceState The Bundle of data to initialize.
     * @return The root View of the activity_main.xml.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View frag_layout = inflater.inflate(R.layout.fragment_history, container, false);
        //build the RecyclerView for this fragment and provide its adapter
        recyclerView = frag_layout.findViewById(R.id.historyItemHolder);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recyclerAdapter);

        return frag_layout;
    }

    /**
     * Get the data to display on history page
     */
    private synchronized void getData() {

        assert getArguments() != null;
        List<HistoryData> list = new ArrayList<HistoryData>();
        // hardcoded data
        list.add(new HistoryData("Breathe", getResources().getDrawable(R.drawable.breathe), 0, "5 mins"));
        list.add(new HistoryData("Fidget Cube", getResources().getDrawable(R.drawable.fidget_cube), 5, "10 mins"));
        list.add(new HistoryData("Bubbles", getResources().getDrawable(R.drawable.bubbles), 1, "1 min"));

        if (recyclerAdapter == null) {
            recyclerAdapter = new HistoryRecyclerAdapter(context, list);
        }
    }
}
