package com.richardpingree.navigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Richard Pingree on 2/24/15.
 */
public class RecentStoriesFragment extends Fragment{

    public static final String TAG = "RecentStoryFragment.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    ListView mList;


    public  RecentStoriesFragment(){

    }

    public static RecentStoriesFragment newInstance(int sectionNumber){
        RecentStoriesFragment frag = new RecentStoriesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_stories, container, false);
        mList = (ListView) rootView.findViewById(R.id.headLineList);

        List<Map<String, String>> newsData = new ArrayList<Map<String, String>>();
        Map<String, String> newsMap = new HashMap<>(2);

        for (int i = 0; i < 10; i++){
            newsMap.put("title", "Static News Headline");
            newsMap.put("story", "Beginning of this story");
            newsData.add(newsMap);
        }

        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), newsData, android.R.layout.simple_list_item_2,
                new String[] {"title", "story"}, new int[] {android.R.id.text1, android.R.id.text2});

        mList.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
