package edu.calvin.cs262.sensapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository to access the History Table through DAO
 */
public class HistoryRepository {

    private HistoryDao mHistoryDao;
    private LiveData<List<History>> mAllHistories;

    /**
     * Create this Repository by getting a database and all Histories
     *
     * @param application Current Application
     */
    HistoryRepository(Application application) {
        SensappRoomDatabase db = SensappRoomDatabase.getDatabase(application);
        mHistoryDao = db.historyDao();
        mAllHistories = mHistoryDao.getAllHistories();
    }

    /**
     * Return all Histories in DB
     *
     * @return LiveData List of all Histories
     */
    LiveData<List<History>> getAllHistories() {
        return mAllHistories;
    }

    /**
     * Spawn AsyncTask to delete all Histories
     */
    public void deleteAll()  {
        new deleteAllHistoriesAsyncTask(mHistoryDao).execute();
    }

    /**
     * Spawn AsyncTask to insert one History
     *
     * @param history History to be inserted
     */
    public void insert (History history) {
        new insertAsyncTask(mHistoryDao).execute(history);
    }

    /**
     * Spawn AsyncTask to delete one History
     *
     * @param history History to be deleted
     */
    public void deleteHistory(History history)  {
        new deleteHistoryAsyncTask(mHistoryDao).execute(history);
    }

    /**
     * AsyncTask to insert new Histories
     */
    private static class insertAsyncTask extends AsyncTask<History, Void, Void> {

        private HistoryDao mAsyncTaskDao;

        /**
         * Construct the AsyncTask
         *
         * @param dao HistoryDao to access History Table
         */
        insertAsyncTask(HistoryDao dao) {
            mAsyncTaskDao = dao;
        }

        /**
         * Perform Task Asynchronously
         *
         * @param params History object to insert
         * @return null
         */
        @Override
        protected Void doInBackground(final History... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * AsyncTask to delete all Histories
     */
    private static class deleteAllHistoriesAsyncTask extends AsyncTask<Void, Void, Void> {
        private HistoryDao mAsyncTaskDao;

        /**
         * Construct the AsyncTask
         *
         * @param dao HistoryDao to access History Table
         */
        deleteAllHistoriesAsyncTask(HistoryDao dao) {
            mAsyncTaskDao = dao;
        }

        /**
         * Perform Task Asynchronously
         *
         * @return null
         */
        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * AsyncTask to delete a single History
     */
    private static class deleteHistoryAsyncTask extends AsyncTask<History, Void, Void> {
        private HistoryDao mAsyncTaskDao;

        /**
         * Construct the AsyncTask
         *
         * @param dao HistoryDao to access History Table
         */
        deleteHistoryAsyncTask(HistoryDao dao) {
            mAsyncTaskDao = dao;
        }

        /**
         * Perform Task Asynchronously
         *
         * @param params History object to delete
         * @return null
         */
        @Override
        protected Void doInBackground(final History... params) {
            mAsyncTaskDao.deleteHistory(params[0]);
            return null;
        }
    }
}
