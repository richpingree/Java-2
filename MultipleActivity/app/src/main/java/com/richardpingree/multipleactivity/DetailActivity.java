package com.richardpingree.multipleactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Richard Pingree on 2/17/15.
 */
public class DetailActivity extends Activity implements DetailFragment.DetailListener {

    private final String TAG = "DetailActivity.TAG";

    private Hero mHero;
    private int mDelete;

    public static final String HEROEXTRA = "Hero";
    public static final String DELETEEXTRA = "Delete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, new DetailFragment()).commit();
        }

        Intent detailIntent = getIntent();
        if(detailIntent != null){
            mHero = (Hero) detailIntent.getSerializableExtra(HEROEXTRA);
            mDelete = detailIntent.getIntExtra(DELETEEXTRA, 0);
        }
    }

    //interface methods
    @Override
    public Hero getHero() {
        return mHero;
    }

    @Override
    public int getDelete() {
        return mDelete;
    }

    @Override
    public void deleteEntry() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.DELETEHEROEXTRA, mDelete);
        setResult(RESULT_OK, returnIntent);
        finish();
    }


}
