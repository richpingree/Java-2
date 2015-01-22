package com.richardpingree.multiactivity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by richardpingree on 1/21/15.
 */
public class addFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "AddFragment.TAG";

    public static addFragment newInstance() {
        addFragment frag = new addFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_add, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();

        if (view != null) {
            EditText userText = (EditText)view.findViewById(R.id.editText);

            userText = (EditText)view.findViewById(R.id.editText2);

            userText = (EditText)view.findViewById(R.id.editText3);

            Button button = (Button)view.findViewById(R.id.button);
            button.setOnClickListener(this);



        }

    }

    @Override
    public void onClick(View v) {
        


    }
}
