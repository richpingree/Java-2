//created by Richard Pingree

package com.richardpingree.java2fragmentsandfiles;

import android.app.Activity;
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
    private static final String ARG_GENRE = "DisplayFragment.ARG_Genre";



    private OnListViewClickListener mListener;


    public static Fragment newInstance(String[] _names) {
        ListFragment frag = new ListFragment();

        Bundle args = new Bundle();
        args.putStringArray(ARG_NAME, _names);

        //args.putStringArray(ARG_GENRE, _genres);
        frag.setArguments(args);

        Log.i(TAG, Arrays.toString(_names));

        return frag;
    }

    public interface OnListViewClickListener{
        public void displayText(String name);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof OnListViewClickListener){
            mListener = (OnListViewClickListener) activity;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.list_fragment, _container, false);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        View view = getView();

        ListView list = (ListView) view.findViewById(R.id.list);

        Bundle args = getArguments();
        String[] _names = args.getStringArray(ARG_NAME);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, _names);
        list.setAdapter(adapter);
    }





}
