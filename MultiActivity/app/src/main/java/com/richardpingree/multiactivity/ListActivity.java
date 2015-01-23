package com.richardpingree.multiactivity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ListActivity extends ActionBarActivity {

    public static final String TAG = "ListActivity";


    public ArrayList<Movie> movies = new ArrayList<Movie>();




    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle extras = getIntent().getExtras();

        String mTitle = extras.getString("mTitle");
        String mFormat = extras.getString("mFormat");
        String mGenre = extras.getString("mGenre");

        //data from Form
//        String mTitle = getIntent().getStringExtra("mTitle");
//        String mFormat = getIntent().getStringExtra("mFormat");
//        String mGenre = getIntent().getStringExtra("mGenre");

        Log.e(TAG, "title: " + mTitle + " format: " + mFormat + " genre: " + mGenre);

        String title = mTitle;
        String format = mFormat;
        String genre = mGenre;
        Movie movie = new Movie(title, format, genre);


        Log.i(TAG, movie.toString());

        movies.add(movie);

        //String[] movie = new String[]{mTitle.toString(), mFormat.toString(), mGenre.toString()};
        Log.i(TAG, "Movie Array: " + movies.toString());
        //String movieString = movie.toString();
        //Log.i(TAG, "Movie String: " + movieString);

       // movies.add(new Movie(movieString));

        Log.e(TAG, "list output" + movies.toString());
        list = (ListView)findViewById(R.id.listView);

        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies);
        list.setAdapter(adapter);

    }

    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
