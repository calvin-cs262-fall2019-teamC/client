package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class TutorialItem extends Fragment {
    private Context context;
    private int textID;
    private int drawableID;

    /**
     * Empty constructor
     */
    public TutorialItem() {
        // must have an empty public constructor, or else get the error "TutorialItem
        // must be a public static class to be  properly recreated from instance state"
    }

    /**
     * create the tutorial fragment
     *
     * @param savedInstanceState bundle of data
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        assert getArguments() != null;
        textID = getArguments().getInt("textID");

        // if no image is needed, drawableID will not be in the arguments.
        // But as far as I can tell I don't need to modify the code to handle this case.
        drawableID = getArguments().getInt("drawableID");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tutorial_item, container, false);

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(textID);
        GifImageView gifView = view.findViewById(R.id.gifView);
        gifView.setImageResource(drawableID);

        return view;
    }
}
