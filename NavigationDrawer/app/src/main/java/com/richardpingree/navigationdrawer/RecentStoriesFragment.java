package com.richardpingree.navigationdrawer;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by Richard Pingree on 1/29/15.
 */

public class RecentStoriesFragment extends ListFragment {

    public static final String TAG = "RecentStoriesFragment.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    ListView list;

    ArrayList<String> recentList = new ArrayList<>();



    public static RecentStoriesFragment newInstance(int sectionNumber) {
        RecentStoriesFragment fragment = new RecentStoriesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RecentStoriesFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_recent_stories, container, false);
        list = (ListView)rootView.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, recentList);
        list.setAdapter(adapter);
        return rootView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public void addList(){
        recentList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            recentList.add("Headline");
        }
    }


}
