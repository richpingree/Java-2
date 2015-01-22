package com.richardpingree.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddMovieActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "AddMovieActivity";

    EditText titleInput;
    EditText formatInput;
    EditText genreInput;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        if(savedInstanceState == null){

            addFragment frag = addFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.container, frag, addFragment.TAG).commit();

        }

        titleInput = (EditText)findViewById(R.id.editText);
        formatInput = (EditText)findViewById(R.id.editText2);
        genreInput = (EditText)findViewById(R.id.editText3);

        addBtn = (Button)findViewById(R.id.button);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent addIntent = new Intent();
        addIntent.putExtra("movieTitle", titleInput.getText().toString());
        addIntent.putExtra("movieFormat", formatInput.getText().toString());
        addIntent.putExtra("movieGenre", genreInput.getText().toString());
        setResult(RESULT_OK, addIntent);
        finish();
    }
}
