package com.richardpingree.java2fragmentsandfiles;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by richardpingree on 1/11/15.
 */
public class DisplayFragment extends Fragment {

    public static final String TAG = "DisplayFragment.TAG";

    private static final String ARG_NAME = "DisplayFragment.ARG_NAME";
    private static final String ARG_GENRE = "DisplayFragment.ARG_GENRE";

    public static DisplayFragment newInstance(String _name, String _genre){
        DisplayFragment frag = new DisplayFragment();

        Bundle args = new Bundle();
        args.putString(ARG_NAME, _name);
        args.putString(ARG_GENRE, _genre);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.display_fragment, _container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_NAME)){
            setDisplayText(args.getString(ARG_NAME), args.getString(ARG_GENRE));
        }
    }

    public void setDisplayText(String _name, String _genre){
        getArguments().putString(ARG_NAME, _name);
        getArguments().putString(ARG_GENRE, _genre);

        TextView nameText = (TextView) getView().findViewById(R.id.nameView);
        TextView genreText = (TextView) getView().findViewById(R.id.genreView);

        nameText.setText(_name);
        genreText.setText(_genre);
    }
}

