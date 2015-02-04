package com.richardpingree.fragmentfile;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


/**
 * Created by Richard Pingree on 2/3/15.
 */
public class MainActivity extends ActionBarActivity {

    public static final String TAG = "MainActivity.TAG";

    EditText userText;
    Button btn;

    String inputText = "";
    List<Artist> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            ArtistListFragment frag = ArtistListFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.container1, frag, ArtistListFragment.TAG).commit();
        }

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userText = (EditText) findViewById(R.id.editText);
                inputText = userText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("userInput", inputText);



            }
        });
    }
    //Interface methods



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
