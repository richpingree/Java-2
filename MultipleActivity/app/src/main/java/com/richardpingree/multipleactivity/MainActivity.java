package com.richardpingree.multipleactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Richard Pingree on 2/17/15.
 */
public class MainActivity extends ActionBarActivity implements MainFragment.HeroListener {


    private final String TAG = "MainActivity.TAG";

    private static final int ADDREQUEST = 1;
    public static String ADDHEROEXTRAFIRSTNAME = "First Name";
    public static String ADDHEROEXTRALASTNAME = "Last Name";
    public static String ADDHEROEXTRAALIAS = "Alias";
    public static String ADDHEROEXTRAPOWER = "Power";
    public static  int DELETREQUEST = 0;
    public static String  DELETEHEROEXTRA = "Delete Hero";

    private ArrayList<Hero> mHeroDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
        }

        mHeroDataList = new ArrayList<Hero>();
//        mHeroDataList.add(new Hero("Clark", "Kent", "Superman", "Heat Vision"));
//        mHeroDataList.add(new Hero("Bruce", "Wayne", "Batman", "Agility"));
//        mHeroDataList.add(new Hero("Barry", "Allen", "The Flash", "Super Speed"));
//        mHeroDataList.add(new Hero("Oliver", "Queen", "Green Arrow", "Archery"));
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == DELETREQUEST){
            mHeroDataList.remove(data.getIntExtra(DELETEHEROEXTRA,0));
            MainFragment mf = (MainFragment) getFragmentManager().findFragmentById(R.id.container);
            try {
                mf.updateList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(resultCode == Activity.RESULT_OK && requestCode == ADDREQUEST){
            Hero newHero = new Hero();

            newHero.mFirst = data.getStringExtra(ADDHEROEXTRAFIRSTNAME);
            newHero.mLast = data.getStringExtra(ADDHEROEXTRALASTNAME);
            newHero.mAlias = data.getStringExtra(ADDHEROEXTRAALIAS);
            newHero.mPower = data.getStringExtra(ADDHEROEXTRAPOWER);

            mHeroDataList.add(newHero);
            MainFragment mf = (MainFragment) getFragmentManager().findFragmentById(R.id.container);
            try {
                mf.updateList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //InterFace Methods
    @Override
    public void viewHero(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.HEROEXTRA, mHeroDataList.get(position));
        startActivity(detailIntent);
    }

    @Override
    public void deleteHero(int position) {
        Intent deleteIntent = new Intent(this, DetailActivity.class);
        deleteIntent.putExtra(DetailActivity.HEROEXTRA, mHeroDataList.get(position));
        deleteIntent.putExtra(DetailActivity.DELETEEXTRA, position);
        startActivityForResult(deleteIntent, DELETREQUEST);
    }

    @Override
    public ArrayList<Hero> getHeroes(){

        return mHeroDataList;
    }

    @Override
    public void addHero() {
       Intent addIntent = new Intent(this, FormActivity.class);
        startActivityForResult(addIntent, ADDREQUEST);
    }

    public void goToSite(){
        String website = "http://www.superherodb.com";
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
        startActivity(webIntent);
    }

    public void createFile() throws IOException{
        FileOutputStream fos = openFileOutput("savedData", Context.MODE_PRIVATE);
        JSONArray ar = new JSONArray(mHeroDataList);
        String json = ar.toString();

        fos.write(json.getBytes());
        fos.close();
        }

    public void readFile() throws IOException{
        FileInputStream fis = openFileInput("SavedData");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while(bis.available() != 0){
            char c = (char) bis.read();
            b.append(c);
        }
        String json = b.toString();
        bis.close();
        fis.close();


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
