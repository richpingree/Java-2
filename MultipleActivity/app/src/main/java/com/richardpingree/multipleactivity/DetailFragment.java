package com.richardpingree.multipleactivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by richardpingree on 2/17/15.
 */
public class DetailFragment extends Fragment {

    private final String TAG = "DetailFragment.TAG";

    private DetailListener mListener;

    public interface DetailListener{
        public Hero getHero();
        public int getDelete();
        public void deleteEntry();
    }

    public DetailFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof DetailListener){
            mListener = (DetailListener) activity;
        }else {
            throw new IllegalArgumentException("Containing activity must implement DetailListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getView().findViewById(R.id.detailFirst);
        textView.setText(mListener.getHero().getFirst());
        textView = (TextView) getView().findViewById(R.id.detailLast);
        textView.setText(mListener.getHero().getLast());
        textView = (TextView) getView().findViewById(R.id.detailAlias);
        textView.setText(mListener.getHero().getAlias());
        textView = (TextView) getView().findViewById(R.id.detailPower);
        textView.setText(mListener.getHero().getPower());

        if(mListener.getDelete()> 0){
            Button delBtn = (Button) getView().findViewById(R.id.deleteButton);
            delBtn.setVisibility(View.VISIBLE);
            delBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    mListener.deleteEntry();
                }
            });
        }

    }
}
