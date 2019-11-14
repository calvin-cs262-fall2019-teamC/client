package edu.calvin.cs262.sensapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository to access the Activity Table through DAO
 */
public class ActivityRepository {

    private ActivityDao mActivityDao;
    private LiveData<List<Activity>> mAllActivities;

    /**
     * Create this Repository by getting a database and all Activities
     *
     * @param application Current Application
     */
    ActivityRepository(Application application) {
        SensappRoomDatabase db = SensappRoomDatabase.getDatabase(application);
        mActivityDao = db.activityDao();
        mAllActivities = mActivityDao.getAllActivities();
    }

    /**
     * Return all Activities in DB
     *
     * @return LiveData List of all Activities
     */
    LiveData<List<Activity>> getAllActivities() {
        return mAllActivities;
    }

    /**
     * Spawn AsyncTask to delete all Activities
     */
    public void deleteAll()  {
        new deleteAllActivitiesAsyncTask(mActivityDao).execute();
    }

    /**
     * Spawn AsyncTask to insert one Activity
     *
     * @param activity Activity to be inserted
     */
    public void insert (Activity activity) {
        new insertAsyncTask(mActivityDao).execute(activity);
    }

    /**
     * Spawn AsyncTask to delete one Activity
     *
     * @param activity Activity to be deleted
     */
    public void deleteActivity(Activity activity)  {
        new deleteActivityAsyncTask(mActivityDao).execute(activity);
    }

    /**
     * AsyncTask to insert new Activities
     */
    private static class insertAsyncTask extends AsyncTask<Activity, Void, Void> {

        private ActivityDao mAsyncTaskDao;

        insertAsyncTask(ActivityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Activity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * AsyncTask to delete all Activities
     */
    private static class deleteAllActivitiesAsyncTask extends AsyncTask<Void, Void, Void> {
        private ActivityDao mAsyncTaskDao;

        deleteAllActivitiesAsyncTask(ActivityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * AsyncTask to delete a single Activity
     */
    private static class deleteActivityAsyncTask extends AsyncTask<Activity, Void, Void> {
        private ActivityDao mAsyncTaskDao;

        deleteActivityAsyncTask(ActivityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Activity... params) {
            mAsyncTaskDao.deleteActivity(params[0]);
            return null;
        }
    }
}
