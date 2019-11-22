package edu.calvin.cs262.sensapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data accessor for the Activity Table
 */
@Dao
public interface ActivityDao {

    // Insert a Activity obj
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Activity activity);

    // Delete all Activities
    @Query("DELETE FROM activity_table")
    void deleteAll();

    // Get all Activities ordered by ID
    @Query("SELECT * from activity_table ORDER BY id ASC")
    LiveData<List<Activity>> getAllActivities();

    // Get one Activity
    @Query("SELECT * from activity_table LIMIT 1")
    Activity[] getAnyActivity();

    // Get one Activity ID based on a given name
    @Query("SELECT id FROM activity_table WHERE name = :activityName")
    int getActivityIdByName(String activityName);
  
    // Delete a Activity
    @Delete
    void deleteActivity(Activity activity);
}
