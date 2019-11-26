package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

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
    private DatabaseViewModel mDatabaseViewModel;

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

        // Inflate the View
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        // Populate the RecyclerView with Favorites
        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        // https://stackoverflow.com/questions/6495898/
        //     findviewbyid-in-fragment?page=1&tab=votes#tab-top
        RecyclerView recyclerView = view.findViewById(R.id.historyItemHolder);
        final HistoriesListAdapter adapter = new HistoriesListAdapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        mDatabaseViewModel.getAllActivities().observe(this, new Observer<List<Activity>>() {
            @Override
            public void onChanged(@Nullable final List<Activity> activities) {
                // Update the cached copy of the activities in the adapter.
                adapter.setActivities(activities);
            }
        });
        mDatabaseViewModel.getAllHistories().observe(this, new Observer<List<History>>() {
            @Override
            public void onChanged(@Nullable final List<History> histories) {
                // Update the cached copy of the favorites in the adapter.
                adapter.setHistories(histories);
            }
        });

        return view;
    }
}
