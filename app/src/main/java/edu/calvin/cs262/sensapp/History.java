package edu.calvin.cs262.sensapp;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * History table from database
 */
@Entity(tableName = "history_table")
public class History {

    @PrimaryKey
    @ForeignKey(entity = Activity.class, parentColumns = "id", childColumns = "activityId")
    @ColumnInfo(name = "activityId")
    private int mActivityId;

    @ColumnInfo(name = "timeStart")
    private String mTimeStart;

    @ColumnInfo(name = "timeEnd")
    private String mTimeEnd;

    @Nullable
    @ColumnInfo(name = "satisfaction")
    private Integer mSatisfaction;

    /**
     * Constructor for History Table
     *
     * @param activityId The activityId foreign key field
     * @param timeStart The timeStart field (timestamp String)
     * @param timeEnd The timeEnd field (timestamp String)
     * @param satisfaction The satisfaction field (int 1-5 or NULL)
     */
    public History(int activityId, String timeStart,
                   String timeEnd, @Nullable Integer satisfaction) {
        this.mActivityId = activityId;
        this.mTimeStart = timeStart;
        this.mTimeEnd = timeEnd;
        this.mSatisfaction = satisfaction;
    }

    /**
     * Getters for Column values
     */
    public int getActivityId(){return this.mActivityId;}
    public String getTimeStart(){return this.mTimeStart;}
    public String getTimeEnd(){return this.mTimeEnd;}
    public Integer getSatisfaction(){return this.mSatisfaction;}
}
