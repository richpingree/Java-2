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
    private static final String ARG_GENRE = "DisplayFragment.ARG.GENRE";
    private static final String ARG_LABEL = "DisplayFragment.ARG_LABEL";
    private static final String ARG_COUNTRY = "DisplayFragment.ARG_COUNTRY";
    private static final String ARG_CITY = "DisplayFragment.ARG_CITY";
    private static final String ARG_STATE = "DisplayFragment.ARG_STATE";


    public static DisplayFragment newInstance(String _name, String _genre, String _label, String _country, String _city, String _state){
        DisplayFragment frag = new DisplayFragment();

        Bundle args = new Bundle();
        args.putString(ARG_NAME, _name);
        args.putString(ARG_GENRE, _genre);
        args.putString(ARG_LABEL, _label);
        args.putString(ARG_COUNTRY, _country);
        args.putString(ARG_CITY, _city);
        args.putString(ARG_STATE, _state);
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
            setDisplayText(args.getString(ARG_NAME), args.getString(ARG_GENRE), args.getString(ARG_LABEL), args.getString(ARG_COUNTRY), args.getString(ARG_CITY), args.getString(ARG_STATE));
        }
    }

    public void setDisplayText(String _name, String _genre, String _label, String _country, String _city, String _state){
        getArguments().putString(ARG_NAME, _name);
        getArguments().putString(ARG_GENRE, _genre);
        getArguments().putString(ARG_LABEL, _label);
        getArguments().putString(ARG_COUNTRY, _country);
        getArguments().putString(ARG_CITY, _city);
        getArguments().putString(ARG_STATE, _state);

        TextView nameText = (TextView) getView().findViewById(R.id.nameView);
        TextView genreText = (TextView) getView().findViewById(R.id.genreView);
        TextView labelText = (TextView) getView().findViewById(R.id.labelView);
        TextView countryText = (TextView) getView().findViewById(R.id.counrtyView);
        TextView cityText = (TextView) getView().findViewById(R.id.cityView);
        TextView stateText = (TextView) getView().findViewById(R.id.stateView);

        nameText.setText(_name);
        genreText.setText(_genre);
        labelText.setText(_label);
        countryText.setText(_country);
        cityText.setText(_city);
        stateText.setText(_state);
    }
}

