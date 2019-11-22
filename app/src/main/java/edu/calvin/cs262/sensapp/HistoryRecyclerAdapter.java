package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hsalf.smilerating.SmileRating;

import java.util.List;

/**
 * A RecyclerAdapter for History items
 */
public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryItemHolder> {
    private Context context;
    private List<HistoryData> historyDataList;

    /**
     * Construct a HistoryRecyclerAdapter
     * @param app_context current app Context
     * @param historydata_list List of HistoryData objects
     */
    public HistoryRecyclerAdapter(@NonNull Context app_context, @NonNull List<HistoryData> historydata_list) {
        super();
        context = app_context;
        historyDataList = historydata_list;
    }

    /**
     * Create ViewHolder
     *
     * @param parent ViewGroup of parent
     * @param viewType int of the type of View
     * @return a HistoryItemHolder
     */
    @NonNull
    @Override
    public HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.history_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachtoParentImmediately = false;
        View view = inflater.inflate(layout, parent, shouldAttachtoParentImmediately);
        return new HistoryItemHolder(view);
    }

    /**
     * Bind the ViewHolder
     *
     * @param holder HistoryItemHolder to hold data
     * @param position int of position in data
     */
    @Override
    public void onBindViewHolder(@NonNull HistoryItemHolder holder, int position) {
        HistoryData data = historyDataList.get(position);
        holder.makeHistoryItem(data.getTitle(), data.getDrawable(), data.getRating(), data.getDuration());
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
         * @param ttl String of activity title
         * @param draw Drawable of activity icon
         * @param rate int of satisfaction
         * @param dur String of duration in seconds or minutes
         */
        public void makeHistoryItem(String ttl, Drawable draw, int rate, String dur) {
            titleView.setText(ttl);
            imageView.setImageDrawable(draw);
            if (rate > 0) {  // if rate == 0, then the smiley is unselected
                // rating of 1 is BaseRating.TERRIBLE, which corresponds to 0.
                smileRating.setSelectedSmile(rate - 1, false);
            }
            durationView.setText(dur);
        }
    }

    /**
     * Get the item count of History records
     * @return int of HistoryData list size
     */
    @Override
    public int getItemCount() {
        return historyDataList.size();
    }

}