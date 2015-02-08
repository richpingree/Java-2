package com.richardpingree.fragmentfile;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


/**
 * Created by Richard Pingree on 2/3/15.
 */
public class MainActivity extends ActionBarActivity implements ArtistListFragment.OnItemClickListener, ArtistListFragment.ActivityCommunicator{

    public static final String TAG = "MainActivity.TAG";

    EditText userText;
    Button btn;

    String inputText = "";
    List<Artist> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(savedInstanceState == null){
//            ArtistListFragment frag = ArtistListFragment.newInstance();
//            getFragmentManager().beginTransaction().replace(R.id.container1, frag, ArtistListFragment.TAG).commit();
//        }

        dataToFrag(inputText);

        userText = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inputText = userText.getText().toString();
                Log.i(TAG, inputText);
                dataToFrag(inputText);

            }
        });
    }


    @Override
    public void displayArtist(String name, String genre, String label, String city, String state) {
        Log.i(TAG, "Displaying: " + name + " " + genre + " " + label + " " + city + " " + state);

        DisplayFragment frag = (DisplayFragment) getFragmentManager().findFragmentByTag(DisplayFragment.TAG);

        if(frag == null){
            frag = DisplayFragment.newInstance(name, genre, label, city, state);
            getFragmentManager().beginTransaction().replace(R.id.container2, frag, DisplayFragment.TAG).commit();
        }else {
            frag.setDisplayText(name, genre, label, city, state);
        }

    }

    @Override
    public void dataToFrag(String userInput) {
       // userInput = userText.getText().toString();
        ArtistListFragment frag = (ArtistListFragment) getFragmentManager().findFragmentByTag(ArtistListFragment.TAG);

        if(frag == null){
            frag = ArtistListFragment.newInstance(userInput);
            getFragmentManager().beginTransaction().replace(R.id.container1, frag, ArtistListFragment.TAG).commit();
        }else{
            frag.displayList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
