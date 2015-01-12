//created by Richard Pingree

package com.richardpingree.java2fragmentsandfiles;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;

/**
 * Created by richardpingree on 1/7/15.
 */
public class ItemListFragment extends ListFragment {

    public static final String TAG = "ItemListFragment.TAG";

    private static final String ARG_NAME = "DisplayFragment.ARG_NAME";
    private static final String ARG_GENRE = "DisplayFragment.ARG_Genre";



    private OnListViewClickListener mListener;


    public static Fragment newInstance(String[] _names, String[] _genres) {
        ListFragment frag = new ListFragment();

        Bundle args = new Bundle();
        args.putStringArray(ARG_NAME, _names);
        args.putStringArray(ARG_GENRE, _genres);
        frag.setArguments(args);
        return frag;
    }

    public interface OnListViewClickListener{
        public void displayText(String name, String genre);
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



}
