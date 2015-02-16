package com.richardpingree.fragmentfile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Richard Pingree on 2/7/15.
 */
public class DisplayFragment extends Fragment {

    public static final String TAG = "DisplayFragment.TAG";

    private static final String ARG_NAME = "DisplayFragment.ARG_NAME";
    private static final String ARG_GENRE = "DisplayFragment.ARG_GENRE";
    private static final String ARG_LABEL = "DisplayFragment.ARG_LABEL";
    private static final String ARG_CITY = "DisplayFragment.ARG_CITY";
    private static final String ARG_STATE = "DisplayFragment.ARG_STATE";

    TextView nameTxt, genreTxt, labelTxt, cityTxt, stateTxt;

    public static DisplayFragment newInstance(String name, String genre, String label, String city, String state){
        DisplayFragment frag = new DisplayFragment();

        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_GENRE, genre);
        args.putString(ARG_LABEL, label);
        args.putString(ARG_CITY, city);
        args.putString(ARG_STATE, state);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();

        if(args != null){
            setDisplayText(args.getString(ARG_NAME), args.getString(ARG_GENRE), args.getString(ARG_LABEL), args.getString(ARG_CITY), args.getString(ARG_STATE));
        }

    }

    public void setDisplayText(String name, String genre, String label, String city, String state){
        nameTxt = (TextView) getView().findViewById(R.id.nameText);
        nameTxt.setText("Name: " + name);

        genreTxt = (TextView) getView().findViewById(R.id.genreText);
        genreTxt.setText("Genre: " + genre);

        labelTxt = (TextView) getView().findViewById(R.id.labelText);
        labelTxt.setText("Label: " + label);

        cityTxt = (TextView) getView().findViewById(R.id.cityText);
        cityTxt.setText("City: " + city);

        stateTxt = (TextView) getView().findViewById(R.id.stateText);
        stateTxt.setText("State: " + state);
    }

}
