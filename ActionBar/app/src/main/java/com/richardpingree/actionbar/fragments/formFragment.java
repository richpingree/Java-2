package com.richardpingree.actionbar.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.richardpingree.actionbar.R;

/**
 * Created by Richard Pingree on 1/27/15.
 */
public class formFragment extends Fragment {

    public static final String TAG = "formFragment.TAG";

    String mName;

    EditText nameInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);
        nameInput = (EditText)view.findViewById(R.id.editText);
        return view;
    }
}
