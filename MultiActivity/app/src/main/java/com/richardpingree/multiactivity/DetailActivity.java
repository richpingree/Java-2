package com.richardpingree.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailActivity extends Activity {

    public static final String TAG = "DetailActivity.TAG";

    TextView movieTitle;
    TextView movieFormat;
    TextView movieGenre;
    int pos;
    Button delBtn;
    Button launchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        delBtn = (Button)findViewById(R.id.deletebtn);
        launchBtn = (Button)findViewById(R.id.button3);

        Intent intent = getIntent();
        movieTitle = (TextView)findViewById(R.id.textView5);
        movieFormat = (TextView)findViewById(R.id.textView4);
        movieGenre = (TextView)findViewById(R.id.textView6);
        String mTitle = intent.getStringExtra("mTitle");
        String mFormat = intent.getStringExtra("mFormat");
        String mGenre = intent.getStringExtra("mGenre");
        pos = intent.getIntExtra("pos", 0);

        //Log.i(TAG, "Title: " + mTitle + " Format: " + mFormat + " Genre: " + mGenre);

        movieTitle.setText("Movie Title: " + mTitle);
        movieFormat.setText("Movie Format: " + mFormat);
        movieGenre.setText("Movie Genre: " + mGenre);

    }

    public void deleteMovie(View v){
        Intent deleteintent = new Intent(this, ListActivity.class);
        deleteintent.putExtra("pos", pos);
        setResult(RESULT_OK, deleteintent);
        finish();

    }

    public void sendMessage(View v){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Now in " + '\n' + movieTitle.getText().toString() + '\n' +
                movieFormat.getText().toString() + '\n' + movieGenre.getText().toString());
        intent.setType("text/plain");
        startActivity(intent);

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
