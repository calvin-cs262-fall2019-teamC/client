package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * HistoryManager is a utility class to insert new History records into the database
 */
public class HistoryManager {

    // Keep track of start and stop times of the parent Activity
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public String activityTitle;
    private Context context;

    /**
     * Construct a HistoryManager starting timing and setting the Activity title
     *
     * @param title String for the activityTitle
     * @param ctx Current app Context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public HistoryManager(String title, Context ctx) {
        this.startTime = LocalDateTime.now();
        this.activityTitle = title;
        this.context = ctx;
    }

    /**
     * End timing and create a History record of the Activity usage if open for 5 or more seconds
     *
     * @return true if record made, false otherwise
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean createRecord() {
        Log.d("pause", "Leaving " + activityTitle);

        // Determine if activity has been open 5 or more seconds
        endTime = LocalDateTime.now();
        if (startTime.until(endTime, SECONDS) > 5) {
            final String startString = startTime.toString();
            final String endString = endTime.toString();

            // Insert History record using Database Async way
            // From https://stackoverflow.com/questions/44167111/
            //          android-room-simple-select-query-cannot-access-database-on-the-main-thread
            AsyncTask.execute(new Runnable() {
                /**
                 * Insert History record into database
                 */
                @Override
                public void run() {
                    // Get the ID of the Activity selected
                    int id = SensappRoomDatabase.getDatabase(context).activityDao()
                            .getActivityIdByName(activityTitle);

                    // Add History record
                    SensappRoomDatabase.getDatabase(context).historyDao()
                            .insert(new History(0, id, startString, endString, 0));
                }
            });

            return true;

        } else {

            // Activity not open long enough to create record
            return false;
        }
    }

    /**
     * Getters for public variables
     */
    public String getActivityTitle() {
        return activityTitle;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
}
