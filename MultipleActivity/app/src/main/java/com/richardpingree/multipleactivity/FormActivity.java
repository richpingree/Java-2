package com.richardpingree.multipleactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by Richard Pingree on 2/19/15.
 */
public class FormActivity extends ActionBarActivity implements FormFragment.FormListener {

    private final String TAG = "FormActivtiy.TAG";

    Hero newHero;
    public EditText first, last, alias, power;

    public FormActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, new FormFragment()).commit();

        }
    }

    @Override
    public void addEntry(Hero newHero) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.ADDHEROEXTRAFIRSTNAME, newHero.mFirst);
        returnIntent.putExtra(MainActivity.ADDHEROEXTRALASTNAME, newHero.mLast);
        returnIntent.putExtra(MainActivity.ADDHEROEXTRAALIAS, newHero.mAlias);
        returnIntent.putExtra(MainActivity.ADDHEROEXTRAPOWER, newHero.mPower);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
