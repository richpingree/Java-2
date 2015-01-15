//created by Richard Pingree

package com.richardpingree.java2fragmentsandfiles;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Created by richardpingree on 1/7/15.
 */
public class ItemListFragment extends ListFragment {

    public static final String TAG = "ItemListFragment.TAG";

    private static final String ARG_NAME = "DisplayFragment.ARG_NAME";
    private static final String ARG_GENRE = "DisplayFragment.ARG.GENRE";
    private static final String ARG_LABEL = "DisplayFragment.ARG_LABEL";
    private static final String ARG_COUNTRY = "DisplayFragment.ARG_COUNTRY";
    private static final String ARG_CITY = "DisplayFragment.ARG_CITY";
    private static final String ARG_STATE = "DisplayFragment.ARG_STATE";



    //private OnListViewClickListener mListener;


    public static Fragment newInstance(String[] _names, String[] _genres, String[] _labels, String[] _countries, String[] _cities, String[] _states) {
        ListFragment frag = new ListFragment();

        Bundle args = new Bundle();
        args.putStringArray(ARG_NAME, _names);
        args.putStringArray(ARG_GENRE, _genres);
        args.putStringArray(ARG_LABEL, _labels);
        args.putStringArray(ARG_COUNTRY, _countries);
        args.putStringArray(ARG_CITY, _cities);
        args.putStringArray(ARG_STATE, _states);

        frag.setArguments(args);

        Log.i(TAG, Arrays.toString(_names));

        return frag;
    }

//    public interface OnListViewClickListener{
//        public void displayText(String name, String genre, String label, String country, String city, String state);
//    }

//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//
//        if(activity instanceof OnListViewClickListener){
//            mListener = (OnListViewClickListener) activity;
//        }
//        else{
//            throw new IllegalArgumentException();
//        }
//    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.list_fragment, _container, false);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        View view = getListView();

        ListView list = (ListView) view.findViewById(R.id.list);

        Bundle args = getArguments();
        String[] _names = args.getStringArray(ARG_NAME);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, _names);
        list.setAdapter(adapter);
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        Bundle args = getArguments();
//        String[] names = args.getStringArray(ARG_NAME);
//        String[] genres = args.getStringArray(ARG_GENRE);
//        String[] labels = args.getStringArray(ARG_LABEL);
//        String[] countries = args.getStringArray(ARG_COUNTRY);
//        String[] cities = args.getStringArray(ARG_CITY);
//        String[] states = args.getStringArray(ARG_STATE);
//
//        mListener.displayText(names[position], genres[position], labels[position], countries[position], cities[position], states[position]);
//    }

}
