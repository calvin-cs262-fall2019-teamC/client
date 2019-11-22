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

    public HistoryRecyclerAdapter(@NonNull Context app_context, @NonNull List<HistoryData> historydata_list) {
        super();
        context = app_context;
        historyDataList = historydata_list;
    }

    @NonNull
    @Override
    public HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.history_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean shouldAttachtoParentImmediately = false;
        View view = inflater.inflate(layout, parent, shouldAttachtoParentImmediately);
        return new HistoryItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemHolder holder, int position) {
        HistoryData data = historyDataList.get(position);
        holder.makeHistoryItem(data.getTitle(), data.getDrawable(), data.getRating(), data.getDuration());
    }

    public class HistoryItemHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private ImageView imageView;
        private SmileRating smileRating;
        private TextView durationView;

        public HistoryItemHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.history_label);
            imageView = itemView.findViewById(R.id.history_image);
            smileRating = itemView.findViewById(R.id.smile_rating);
            durationView = itemView.findViewById(R.id.history_duration_time);
        }

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

    @Override
    public int getItemCount() {
        return historyDataList.size();
    }

}