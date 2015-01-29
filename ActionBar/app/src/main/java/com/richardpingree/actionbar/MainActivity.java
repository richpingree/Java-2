package com.richardpingree.actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.actionbar.fragments.mainFragment;

import java.util.ArrayList;

/**
 * Created by Richard Pingree on 1/27/15.
 */


public class MainActivity extends ActionBarActivity {

    public static final String TAG = "MainActivity.TAG";
    private static final String ADDITEMREQUEST = "com.richardpingree.actionbar.Add";

    public ArrayList<Items> itemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){

            mainFragment frag = mainFragment.newInstance();

            getFragmentManager().beginTransaction().replace(R.id.container, frag, mainFragment.TAG).commit();
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
        switch (item.getItemId() ){
            case R.id.action_form:

                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startActivityForResult(Intent intent, String data) {

    }

//    protected void startActivityForResults(int requestCode, int resultCode, Intent data) {
//
//    }

}
