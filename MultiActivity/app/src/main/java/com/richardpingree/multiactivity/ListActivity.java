package com.richardpingree.multiactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class ListActivity extends ActionBarActivity {

    public static final String TAG = "ListActivity";


    public ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayAdapter<Movie> adapter;




    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle extras = getIntent().getExtras();

        String mTitle = extras.getString("mTitle");
        String mFormat = extras.getString("mFormat");
        String mGenre = extras.getString("mGenre");


       // Log.e(TAG, "title: " + mTitle + " format: " + mFormat + " genre: " + mGenre);

        String title = mTitle;
        String format = mFormat;
        String genre = mGenre;
        Movie movie = new Movie(title, format, genre);


        //Log.i(TAG, movie.toString());

        movies.add(movie);

        //Log.i(TAG, "Movie Array: " + movies.toString());

        try {
            createFile(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Intent intent = new Intent(getBaseContext(), DetailActivity.class);

        Log.e(TAG, "list output" + movies.toString());
        list = (ListView)findViewById(R.id.listView);

        adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movieSelected = (Movie)list.getItemAtPosition(position);

                String title = movieSelected.getTitle();
                String format = movieSelected.getFormat();
                String genre = movieSelected.getGenre();
                intent.putExtra("mTitle", title);
                intent.putExtra("mFormat", format);
                intent.putExtra("mGenre", genre);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_OK){
            movies.remove(data.getIntExtra("pos", 0));
            adapter.notifyDataSetChanged();


        }
    }

    public void createFile(ArrayList movies) throws Exception{

        String moviesString = movies.toString();

        FileOutputStream fos = openFileOutput("movies.txt", MODE_PRIVATE);

        fos.write(moviesString.getBytes());
        fos.close();

    }

    public ArrayList readFile() throws  Exception{

        FileInputStream fis = openFileInput("movies.txt");

        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();

        while(bis.available() != 0){
            char c =(char) bis.read();
            b.append(c);

            bis.close();
            fis.close();
        }


        ArrayList savedmoives = new ArrayList(Integer.parseInt(b.toString()));

        return savedmoives;
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
