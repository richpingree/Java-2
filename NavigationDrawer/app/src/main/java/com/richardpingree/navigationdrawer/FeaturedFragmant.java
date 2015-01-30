package com.richardpingree.navigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Richard Pingree on 1/29/15.
 */
public class FeaturedFragmant extends Fragment {

    public static final String TAG = "Featured.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    ImageView image;
    TextView headline, description;

    public static FeaturedFragmant newInstance(int sectionNumber){
        FeaturedFragmant fragment = new FeaturedFragmant();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_featured, container, false);
        image = (ImageView) rootView.findViewById(R.id.imageView);
        headline = (TextView) rootView.findViewById(R.id.txtheadline);
        description = (TextView) rootView.findViewById(R.id.textView);

        image.setImageResource(R.drawable.photo1);
        headline.setText("New Celebrity Wax Figures Exhibit Now Open!");
        description.setText("People line up to meet thier favorite celebrities.");
        return rootView;
    }

    public FeaturedFragmant() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
