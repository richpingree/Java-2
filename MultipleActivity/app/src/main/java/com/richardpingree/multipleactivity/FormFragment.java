package com.richardpingree.multipleactivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Richard Pingree on 2/19/15.
 */
public class FormFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "FormFragment.TAG";

    public EditText first, last, alias, power;


    public Hero newHero;

    private FormListener mListener;


    public interface FormListener {
        public void addEntry(Hero newHero);

    }

    public FormFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FormListener) {
            mListener = (FormListener) activity;
        } else {
            throw new IllegalArgumentException("Containing activity must implement FormListener interface");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

//        Button saveBtn = (Button) getView().findViewById(R.id.saveButton);
//        saveBtn.setVisibility(View.VISIBLE);
//        saveBtn.setOnClickListener(this);

        first = (EditText) getView().findViewById(R.id.firstText);
        last = (EditText) getView().findViewById(R.id.lastText);
        alias = (EditText) getView().findViewById(R.id.aliasText);
        power = (EditText) getView().findViewById(R.id.powerText);



    }

    @Override
    public void onClick(View v) {
        newHero = new Hero();

        newHero.mFirst = first.getText().toString();
        newHero.mLast = last.getText().toString();
        newHero.mAlias = alias.getText().toString();
        newHero.mPower = power.getText().toString();

        mListener.addEntry(newHero);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                newHero = new Hero();

                newHero.mFirst = first.getText().toString();
                newHero.mLast = last.getText().toString();
                newHero.mAlias = alias.getText().toString();
                newHero.mPower = power.getText().toString();

                mListener.addEntry(newHero);
                break;
            case R.id.action_clear:
                first.setText("");
                last.setText("");
                alias.setText("");
                power.setText("");

                break;


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}