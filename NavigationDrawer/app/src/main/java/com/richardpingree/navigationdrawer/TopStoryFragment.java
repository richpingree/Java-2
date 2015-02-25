package com.richardpingree.navigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Richard Pingree on 2/24/15.
 */
public class TopStoryFragment extends Fragment {

    public static final String TAG = "TopStoryFragment.TAG";

    public static final String ARG_SECTION_NUMBER = "section_number";

    ImageView image;
    TextView headline, headlineBody;

    public TopStoryFragment(){

    }

    public static TopStoryFragment newInstance(int sectionNumber){
        TopStoryFragment frag = new TopStoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_story, container, false);
        image = (ImageView) rootView.findViewById(R.id.imageView);
        headline = (TextView) rootView.findViewById(R.id.headline);
        headlineBody = (TextView) rootView.findViewById(R.id.body_headline);

        image.setImageResource(R.drawable.breakfast);
        headline.setText("Breakfast Makes You Smarter!");
        headlineBody.setText("A new study shows that eating a good and healthy breakfast will make individuals smarter.");
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
