package com.richardpingree.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class DetailActivity extends Activity {

    public static final String TAG = "DetailActivity.TAG";

    TextView movieTitle;
    TextView movieFormat;
    TextView movieGenre;
    Button delBtn;
    Button launchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        movieTitle = (TextView)findViewById(R.id.textView5);
        movieFormat = (TextView)findViewById(R.id.textView4);
        movieGenre = (TextView)findViewById(R.id.textView6);
        String mTitle = intent.getStringExtra("mTitle");
        String mFormat = intent.getStringExtra("mFormat");
        String mGenre = intent.getStringExtra("mGenre");

        Log.i(TAG, "Title: " + mTitle + " Format: " + mFormat + " Genre: " + mGenre);

        movieTitle.setText("Movie Title: " + mTitle);
        movieFormat.setText("Movie Format: " + mFormat);
        movieGenre.setText("Movie Genre: " + mGenre);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
