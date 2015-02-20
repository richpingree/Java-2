package com.richardpingree.multipleactivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Richard Pingree on 2/19/15.
 */
public class FormFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "FormFragment.TAG";

    EditText first, last, alias, power;

    private FormListener mListener;



    public interface FormListener{
        public void addEntry(Hero newHero);
    }

    public FormFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof FormListener){
            mListener = (FormListener) activity;
        }else{
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

        Button saveBtn = (Button) getView().findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(this);

        first = (EditText) getView().findViewById(R.id.firstText);
        last = (EditText) getView().findViewById(R.id.lastText);
        alias = (EditText) getView().findViewById(R.id.aliasText);
        power = (EditText) getView().findViewById(R.id.powerText);
    }

    @Override
      public void onClick(View v) {
        Hero newHero = new Hero();

        newHero.mFirst = first.getText().toString();
        newHero.mLast = last.getText().toString();
        newHero.mAlias = alias.getText().toString();
        newHero.mPower = power.getText().toString();

        mListener.addEntry(newHero);

    }
}
