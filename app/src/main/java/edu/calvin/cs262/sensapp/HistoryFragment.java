package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
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
        Log.d("HistoryFramgent", "onCreate");
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

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean history_tutorial = prefs.getBoolean("history_tutorial", true);
        if (history_tutorial) {
            startActivity(new Intent(context, HistoryTutorial.class));
        }

        Log.d("HistoryFragment", "onCreateView");

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
                // Update the cached copy of the histories in the adapter.
                adapter.setHistories(histories);
            }
        });

        // Add the functionality to swipe items in the
        // recycler view to delete that item
        // From lab 9 / hw 3
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        History myHistory = adapter.getHistoryAtPosition(position);
                        Toast.makeText(context, "Deleting record " +
                                myHistory.getId(), Toast.LENGTH_LONG).show();

                        // Delete the player
                        mDatabaseViewModel.deleteHistory(myHistory);
                    }
                });
        helper.attachToRecyclerView(recyclerView);

        return view;
    }
}
