package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter to allow a RecyclerView to display all Favorites
 */
public class FavoritesListAdapter
        extends RecyclerView.Adapter<FavoritesListAdapter.FavoriteViewHolder> {

    private LayoutInflater mInflater;
    private List<Favorite> mFavorites; // Cached copy of Favorites
    private List<Activity> mActivities; // Cached copy of Activities
    private Context mContext;

    /**
     * Constructor for a FavoritesListAdapter
     *
     * @param context The current Context
     */
    FavoritesListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Create a view holder
     *
     * @param parent The parent ViewGroup
     * @param viewType The int of the view type
     * @return FavoriteViewHolder created
     */
    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.favorite_recycler_item,
                parent, false);
        return new FavoriteViewHolder(itemView);
    }

    /**
     * Setup the FavoriteViewHolder
     *
     * @param holder The FavoriteViewHolder to setup
     * @param position The current position within the Favorites List
     */
    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        int activity_id;
        String activity_name;
        Activity activity;
        if (mFavorites != null && mActivities != null) {
            Favorite current = mFavorites.get(position);
            activity_id = current.getActivityId();
            activity = getActivityAtPosition(activity_id - 1);
            if (activity != null) {
                activity_name = activity.getName();
                // https://stackoverflow.com/questions/14233062/imagebutton-change-programmatically
                if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_one_title))) {
                    holder.favoriteItemView.setImageResource(R.drawable.breathe);
                    holder.favoriteItemView.setTag(
                            mContext.getResources().getString(R.string.activity_one_title));
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_two_title))) {
                    holder.favoriteItemView.setImageResource(R.drawable.fidget_cube);
                    holder.favoriteItemView.setTag(
                            mContext.getResources().getString(R.string.activity_two_title));
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_three_title))) {
                    holder.favoriteItemView.setImageResource(R.drawable.bubbles);
                    holder.favoriteItemView.setTag(
                            mContext.getResources().getString(R.string.activity_three_title));
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_four_title))) {
                    holder.favoriteItemView.setImageResource(R.drawable.stories);
                    holder.favoriteItemView.setTag(
                            mContext.getResources().getString(R.string.activity_four_title));
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_five_title))) {
                    holder.favoriteItemView.setImageResource(R.drawable.animals);
                    holder.favoriteItemView.setTag(
                            mContext.getResources().getString(R.string.activity_five_title));
                } else if (activity_name.equals(
                        mContext.getResources().getString(R.string.activity_six_title))) {
                    holder.favoriteItemView.setImageResource(R.drawable.music);
                    holder.favoriteItemView.setTag(
                            mContext.getResources().getString(R.string.activity_six_title));
                }
            }
        }
    }

    /**
     * Update Favorites List
     *
     * @param favorites The new Favorites List
     */
    void setFavorites(List<Favorite> favorites) {
        mFavorites = favorites;
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
     * Get the number of Favorites
     *
     * @return The int number of Favorites
     */
    @Override
    public int getItemCount() {
        // getItemCount() is called many times, and when it is first called,
        // mFavorites has not been updated (means initially, it's null, and we can't return null).
        if (mFavorites != null)
            return mFavorites.size();
        else return 0;
    }

    /**
     * The ViewHolder for our Favorites
     */
    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton favoriteItemView;

        private FavoriteViewHolder(View itemView) {
            super(itemView);
            favoriteItemView = itemView.findViewById(R.id.favorite_activity_button);
        }
    }

    /**
     * Get the request Favorite from the List
     *
     * @param position The position at which to get a Favorite
     * @return The Favorite at position in the Favorites list
     */
    public Favorite getFavoriteAtPosition (int position) {
        return mFavorites.get(position);
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

