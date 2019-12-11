package edu.calvin.cs262.sensapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * The first page of a tutorial - explains how tutorials work.
 */
public class Tutorial1 extends Fragment {
    private String activity_name;

    /**
     * Empty constructor
     */
    public Tutorial1() {
        // must have an empty public constructor, or else get the error "Tutorial1
        // must be a public static class to be  properly recreated from instance state"
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        int activity_name_id = getArguments().getInt("activity_name_id");
        activity_name = getResources().getString(activity_name_id);
    }

    /**
     * Create the view, replacing the text 'activity_name' with the appropriate activity name.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_1, container, false);

        TextView textView = view.findViewById(R.id.textView);
        String text = getResources().getString(R.string.tutorial1);
        text = text.replace("activity_name", activity_name);
        textView.setText(text);

        return view;
    }
}
