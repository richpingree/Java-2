package com.richardpingree.java2fragmentsandfiles;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by richardpingree on 1/11/15.
 */
public class DisplayFragment extends Fragment {

    public static final String TAG = "DisplayFragment.TAG";

    public static DisplayFragment newInstance(ArrayList<MainActivity.Artist> arrayApiData){
        DisplayFragment frag = new DisplayFragment();

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.display_fragment, _container, false);
        return view;
    }
}

