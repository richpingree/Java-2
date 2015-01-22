package com.richardpingree.multiactivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by richardpingree on 1/22/15.
 */
public class mainFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "mainFragment.TAG";

    String mTitle;
    String mFormat;
    String mGenre;

    EditText titleInput;
    EditText formatInput;
    EditText genreInput;
    Button addBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        titleInput = (EditText)view.findViewById(R.id.editText);
        formatInput = (EditText)view.findViewById(R.id.editText2);
        genreInput = (EditText)view.findViewById(R.id.editText3);
        addBtn = (Button)view.findViewById(R.id.button);
        addBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        Intent toList = new Intent(getActivity(), ListActivity.class);
        mTitle = titleInput.getText().toString();
        toList.putExtra("mTitle", mTitle);
        mFormat = formatInput.getText().toString();
        toList.putExtra("mFormat", mFormat);
        mGenre = genreInput.getText().toString();
        toList.putExtra("mGenre", mGenre);
        Log.e(TAG, "info returned" + mTitle + mFormat + mGenre );
        startActivity(toList);

    }
}
