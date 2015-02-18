package com.richardpingree.multipleactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends Activity implements MainFragment.HeroListener {

    private final String TAG = "MainActivity.TAG";

    public static  int DELETREQUEST = 1;
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
        mHeroDataList.add(new Hero("Clark", "Kent", "Superman", "Heat Vision"));
        mHeroDataList.add(new Hero("Bruce", "Wayne", "Batman", "Agility"));
        mHeroDataList.add(new Hero("Barry", "Allen", "The Flash", "Super Speed"));
        mHeroDataList.add(new Hero("Oliver", "Queen", "Green Arrow", "Archery"));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == DELETREQUEST){
            mHeroDataList.remove(data.getIntExtra(DELETEHEROEXTRA,0));
            MainFragment mf = (MainFragment) getFragmentManager().findFragmentById(R.id.container);
            mf.updateList();
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
    public ArrayList<Hero> getHeroes() {
        return mHeroDataList;
    }
}
