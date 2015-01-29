package com.richardpingree.actionbar.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.richardpingree.actionbar.Items;

import java.util.ArrayList;

/**
 * Created by Richard Pingree on 1/27/15.
 */
public class mainFragment extends ListFragment{

    public static final String TAG = "mainFragment.TAG";

    ArrayList<Items>  itemList = new ArrayList<>();



    public static mainFragment newInstance(){
        mainFragment frag = new mainFragment();
        return frag;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        itemList.add(new Items("Toaster"));

        ArrayAdapter<Items> adapter = new ArrayAdapter<Items>(getActivity(), android.R.layout.simple_list_item_1, itemList);
        setListAdapter(adapter);
    }
}
