package com.richardpingree.multipleactivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Richard Pingree on 2/17/15.
 */
public class MainFragment extends Fragment {

    private final String TAG = "MainFragment.TAG";

    private HeroListener mListener;
    private ArrayList<Hero> mHeroList;

    public interface HeroListener{
        public void viewHero(int position);
        public void deleteHero(int position);
        public ArrayList<Hero> getHeroes();

    }

    public MainFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof HeroListener) {
            mListener = (HeroListener) activity;
        }else {
            throw new IllegalArgumentException("Containing activity must implement HeroListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView heroListView = (ListView) getView().findViewById(R.id.heroList);
        HeroAdapter adapter = new HeroAdapter(getActivity(), mListener.getHeroes());
        heroListView.setAdapter(adapter);

        heroListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.viewHero(position);
            }
        });

        heroListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.deleteHero(position);
                return true;
            }
        });



    }

    public void updateList(){
        ListView heroList = (ListView) getView().findViewById(R.id.heroList);
        BaseAdapter adapter = (BaseAdapter) heroList.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
