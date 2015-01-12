//created by Richard Pingree

package com.richardpingree.java2fragmentsandfiles;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by richardpingree on 1/7/15.
 */
public class ItemListFragment extends ListFragment {

    public static final String TAG = "ItemListFragment.TAG";




    public static ItemListFragment newInstance(ArrayList<MainActivity.Artist> arrayApiData){
        ItemListFragment frag = new ItemListFragment();
        return frag;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       ArrayList<MainActivity.Artist> artistArrayList = new ArrayList<MainActivity.Artist>();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, artistArrayList);

        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String artist = (String) l.getItemAtPosition(position);


    }


}
