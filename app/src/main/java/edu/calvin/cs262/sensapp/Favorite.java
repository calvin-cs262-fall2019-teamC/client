package edu.calvin.cs262.sensapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Favorite table from database
 */
@Entity(tableName = "favorite_table")
public class Favorite {

    @PrimaryKey
    @ForeignKey(entity = Activity.class, parentColumns = "id", childColumns = "activityId")
    @ColumnInfo(name = "activityId")
    private int mActivityId;

    /**
     * Constructor for Favorite Table
     *
     * @param activityId The activityId foreign key field
     */
    public Favorite(int activityId) {
        this.mActivityId = activityId;
    }

    /**
     * Getters for Column values
     */
    public int getActivityId(){return this.mActivityId;}
}
