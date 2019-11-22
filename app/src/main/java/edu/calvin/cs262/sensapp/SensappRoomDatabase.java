package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Class to store the Sensapp database
 */
@Database(entities = {Activity.class, Favorite.class, History.class}, version = 1, exportSchema = false)
public abstract class SensappRoomDatabase extends RoomDatabase {

    public abstract ActivityDao activityDao();
    public abstract FavoriteDao favoriteDao();
    public abstract HistoryDao historyDao();
    private static Context mContext;
    private static SensappRoomDatabase INSTANCE;

    /**
     * Initialize a DB instance.
     *
     * @param context The current Context.
     * @return The SensappRoomDatabase instance created.
     */
    static SensappRoomDatabase getDatabase(final Context context) {
        mContext = context;
        if (INSTANCE == null) {
            synchronized (SensappRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SensappRoomDatabase.class, "sensapp_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ActivityDao mActivityDao;
        private final FavoriteDao mFavoriteDao;
        private final HistoryDao mHistoryDao;
        int[] historyActivityIds = {1, 4, 6};
        String[] startTimes = {"2006-06-27 08:00:00", "2013-10-01 08:34:22", "2019-11-02 6:50:19"};
        String[] endTimes = {"2006-06-27 08:30:00", "2013-10-01 08:36:33", "2019-11-02 6:53:42"};
        Integer[] satisfactions = {2, null, 5};

        /**
         * Construct PopulateDbAsync Task
         * @param db SensappRoomDatabase to initialize
         */
        PopulateDbAsync(SensappRoomDatabase db) {
            mActivityDao = db.activityDao();
            mFavoriteDao = db.favoriteDao();
            mHistoryDao = db.historyDao();
        }

        /**
         * Initializes hard coded database Tables
         *
         * @return null
         */
        @Override
        protected Void doInBackground(final Void... params) {

            // Get Activity titles
            String[] activityTitles = new String[6];
            activityTitles[0] = mContext.getResources().getString(R.string.activity_one_title);
            activityTitles[1] = mContext.getResources().getString(R.string.activity_two_title);
            activityTitles[2] = mContext.getResources().getString(R.string.activity_three_title);
            activityTitles[3] = mContext.getResources().getString(R.string.activity_four_title);
            activityTitles[4] = mContext.getResources().getString(R.string.activity_five_title);
            activityTitles[5] = mContext.getResources().getString(R.string.activity_six_title);

            // If we have no activities, then create the initial list of activities
            if (mActivityDao.getAnyActivity().length < 1) {
                for (int i = 0; i <= activityTitles.length - 1; i++) {
                    Activity activity = new Activity(0, activityTitles[i]);
                    mActivityDao.insert(activity);
                }
            }

            // If we have no histories, then create the initial list of histories
            if (mHistoryDao.getAnyHistory().length < 1) {
                for (int i = 0; i <= historyActivityIds.length - 1; i++) {
                    History history = new History(
                            historyActivityIds[i], startTimes[i], endTimes[i], satisfactions[i]
                    );
                    mHistoryDao.insert(history);
                }
            }

            return null;
        }
    }

    /**
     * On Callback, launch the create DB async task.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
}
