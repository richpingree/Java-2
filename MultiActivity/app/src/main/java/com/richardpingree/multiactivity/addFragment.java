package com.richardpingree.multiactivity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by richardpingree on 1/21/15.
 */
public class addFragment extends Fragment  {

    public static final String TAG = "AddFragment.TAG";

//    String mTitle;
//    String mFormat;
//    String mGenre;
//    EditText titleInput;
//    EditText formatInput;
//    EditText genreInput;
//    Button addBtn;

    //Movie movieObject = new Movie(mTitle, mFormat, mGenre);





    public static addFragment newInstance() {
        addFragment frag = new addFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_add, container, false);
//        titleInput = (EditText)view.findViewById(R.id.editText);
//        formatInput = (EditText)view.findViewById(R.id.editText2);
//        genreInput = (EditText)view.findViewById(R.id.editText3);
//
//        addBtn = (Button)view.findViewById(R.id.button);
//        addBtn.setOnClickListener(this);

        return view;
    }



//    @Override
//    public void onClick(View v) {
//        mTitle = titleInput.getText().toString();
//        movieObject.setTitle(mTitle);
//        mFormat = formatInput.getText().toString();
//        movieObject.setFormat(mFormat);
//        mGenre = genreInput.getText().toString();
//        movieObject.setGenre(mGenre);
//
//        Intent toList = new Intent();
//        toList.putExtra("movie", movieObject);
//
//
//    }


}
