package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Adapter to allow a RecyclerView to display all Histories
 */
public class HistoriesListAdapter
        extends RecyclerView.Adapter<HistoriesListAdapter.HistoryItemHolder> {

    private LayoutInflater mInflater;
    private List<History> mHistories; // Cached copy of Histories
    private List<Activity> mActivities; // Cached copy of Activities
    private Context mContext;

    /**
     * Constructor for a HistoriesListAdapter
     *
     * @param context The current Context
     */
    HistoriesListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Create a view holder
     *
     * @param parent The parent ViewGroup
     * @param viewType The int of the view type
     * @return HistoryItemHolder created
     */
    @NonNull
    @Override
    public HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.history_item,
                parent, false);
        return new HistoryItemHolder(itemView);
    }

    /**
     * Setup the HistoryViewHolder
     *
     * @param holder The HistoryViewHolder to setup
     * @param position The current position within the Histories List
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull HistoryItemHolder holder, int position) {
        int id;
        String time;
        String title;
        Drawable image;
        int satisfaction;
        int activity_id;
        String activity_name;
        Activity activity;

        // Obtain the necessary History information
        if (mHistories != null && mActivities != null) {
            History current = mHistories.get(position);
            activity_id = current.getActivityId();
            id = current.getId();
            satisfaction = current.getSatisfaction();
            activity = getActivityAtPosition(activity_id - 1);

            // Get the image and title of the Activity referenced by the History record
            if (activity != null) {
                activity_name = activity.getName();
                // https://stackoverflow.com/questions/14233062/imagebutton-change-programmatically
                if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_one_title))) {
                    image = mContext.getResources().getDrawable(R.drawable.breathe);
                    title = mContext.getResources().getString(R.string.activity_one_title);
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_two_title))) {
                    image = mContext.getResources().getDrawable(R.drawable.fidget_cube);
                    title = mContext.getResources().getString(R.string.activity_two_title);
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_three_title))) {
                    image = mContext.getResources().getDrawable(R.drawable.bubbles_coming_soon);
                    title = mContext.getResources().getString(R.string.activity_three_title);
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_four_title))) {
                    image = mContext.getResources().getDrawable(R.drawable.stories_coming_soon);
                    title = mContext.getResources().getString(R.string.activity_four_title);
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_five_title))) {
                    image = mContext.getResources().getDrawable(R.drawable.animals);
                    title = mContext.getResources().getString(R.string.activity_five_title);
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_six_title))) {
                    image = mContext.getResources().getDrawable(R.drawable.music);
                    title = mContext.getResources().getString(R.string.activity_six_title);
                } else {
                    image = mContext.getResources().getDrawable(R.drawable.breathe);
                    title = mContext.getResources().getString(R.string.activity_one_title);
                }

                // Determine which time unit to use and how long separates start from end
                LocalDateTime start = LocalDateTime.parse(current.getTimeStart());
                LocalDateTime end = LocalDateTime.parse(current.getTimeEnd());
                long seconds = start.until(end, SECONDS);
                if (seconds < 60) {
                    time = seconds + "sec";
                } else {
                    long minutes = start.until(end, MINUTES);
                    if (minutes < 60) {
                        time = minutes + "min";
                    } else {
                        long hours = start.until(end, HOURS);
                        time = hours + "hrs";
                    }
                }

                // Make a card to display History data found
                holder.makeHistoryItem(id, title, image, satisfaction, time);
            }
        }
    }

    /**
     * HistoryItemHolder is a ViewHolder to hold History records
     */
    public class HistoryItemHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private ImageView imageView;
        private SmileRating smileRating;
        private TextView durationView;

        /**
         * Construct a HistoryItemHolder initializing variables
         *
         * @param itemView View in which to store
         */
        public HistoryItemHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.history_label);
            imageView = itemView.findViewById(R.id.history_image);
            smileRating = itemView.findViewById(R.id.smile_rating);
            durationView = itemView.findViewById(R.id.history_duration_time);
        }

        /**
         * Setup the Views using the data of the History record
         *
         * @param id int of the History record id
         * @param ttl String of activity title
         * @param draw Drawable of activity icon
         * @param rate int of satisfaction
         * @param dur String of duration in seconds or minutes
         */
        public void makeHistoryItem(int id, String ttl, Drawable draw, int rate, String dur) {
            final int mId = id;
            titleView.setText(ttl);
            imageView.setImageDrawable(draw);
            if (rate > 0) {  // if rate == 0, then the smiley is unselected
                // rating of 1 is BaseRating.TERRIBLE, which corresponds to 0.
                smileRating.setSelectedSmile(rate - 1, false);
            }
            durationView.setText(dur);

            // Update the History record when a new satisfaction value is selected
            // From https://github.com/sujithkanna/SmileyRating
            smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    if (!reselected) {
                        int rating = 0;
                        switch (smiley) {
                            case SmileRating.TERRIBLE:
                                rating = 1;
                                break;
                            case SmileRating.BAD:
                                rating = 2;
                                break;
                            case SmileRating.OKAY:
                                rating = 3;
                                break;
                            case SmileRating.GOOD:
                                rating = 4;
                                break;
                            case SmileRating.GREAT:
                                rating = 5;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + smiley);
                        }
                        final int newRating = rating;

                        // Update data using Database Async way
                        // From https://stackoverflow.com/questions/44167111/
                        //          android-room-simple-select-query-cannot-access-database-on-the-main-thread
                        AsyncTask.execute(new Runnable() {
                            /**
                             * Update the selected History
                             */
                            @Override
                            public void run() {
                                SensappRoomDatabase.getDatabase(mContext)
                                        .historyDao().updateRating(mId, newRating);
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * Update Histories List
     *
     * @param histories The new Histories List
     */
    void setHistories(List<History> histories) {
        mHistories = histories;
        notifyDataSetChanged();
    }

    /**
     * Update Activities List
     *
     * @param activities The new Activities List
     */
    void setActivities(List<Activity> activities) {
        mActivities = activities;
        notifyDataSetChanged();
    }

    /**
     * Get the number of Histories
     *
     * @return The int number of Histories
     */
    @Override
    public int getItemCount() {
        // getItemCount() is called many times, and when it is first called,
        // mHistories has not been updated (means initially, it's null, and we can't return null).
        if (mHistories != null)
            return mHistories.size();
        else return 0;
    }

    /**
     * Get the request History from the List
     *
     * @param position The position at which to get a History
     * @return The History at position in the Histories list
     */
    public History getHistoryAtPosition (int position) {
        return mHistories.get(position);
    }

    /**
     * Get the request Activity from the List
     *
     * @param position The position at which to get an Activity
     * @return The Activity at position in the Activities list
     */
    public Activity getActivityAtPosition (int position) {
        if (position != -1) {
            return mActivities.get(position);
        } else {
            return null;
        }
    }
}
