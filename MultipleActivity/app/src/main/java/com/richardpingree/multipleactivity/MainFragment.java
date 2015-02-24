package com.richardpingree.multipleactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Richard Pingree on 2/17/15.
 */
public class MainFragment extends Fragment {

    private final String TAG = "MainFragment.TAG";

    private HeroListener mListener;
    private ArrayList<Hero> mHeroList;
    HeroAdapter mAdapter;
    private ActionMode mActionMode;
    private int mItemSelected = -1;

    public interface HeroListener{
        public void viewHero(int position);
        public void deleteHero(int position);
        public ArrayList<Hero> getHeroes();
        public void addHero();
        //public void goToSite();
        public void createFile() throws IOException;
        public void readFile() throws IOException;

    }

    public MainFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof HeroListener) {
            mListener = (HeroListener) activity;
        }else {
            throw new IllegalArgumentException("Containing activity must implement HeroListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView heroListView = (ListView) getView().findViewById(R.id.heroList);
        mAdapter = new HeroAdapter(getActivity(), mListener.getHeroes());
        heroListView.setAdapter(mAdapter);

        heroListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.viewHero(position);
            }
        });

       heroListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               if(mActionMode != null){
                   return false;
               }
               mItemSelected = position;
               mActionMode = getActivity().startActionMode(mActionModeCallback);
               //mListener.deleteHero(position);
               return true;
           }
       });

//
//        Button addBtn = (Button)getView().findViewById(R.id.addHero);
//        addBtn.setVisibility(View.VISIBLE);
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.addHero();
//            }
//        });
//
//        Button webBtn = (Button)getView().findViewById(R.id.toWeb);
//        webBtn.setVisibility(View.VISIBLE);
//        webBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.goToSite();
//            }
//        });

    }

    public void updateList() throws IOException {
        try {
            mListener.createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListView heroList = (ListView) getView().findViewById(R.id.heroList);
        BaseAdapter adapter = (BaseAdapter) heroList.getAdapter();
        adapter.notifyDataSetChanged();
    }


    public Hero getToDelete(){
        return mAdapter.getItem(mItemSelected);
    }
    //Contextual Action Bar CALLBACK

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback(){


        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //inflates menu resource
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.itemDelete:

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Are you sure you want to delete " + mAdapter.getItem(mItemSelected).toString() + "?")
                            .setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // Log.i(TAG, mAdapter.getItem(mItemSelected).toString());
                            mListener.deleteHero(mItemSelected);
                            mActionMode.finish();
                        }
                    })
                            .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //canceled delete
                                }
                            });
                    builder.show();

                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;

        }
    };
}
