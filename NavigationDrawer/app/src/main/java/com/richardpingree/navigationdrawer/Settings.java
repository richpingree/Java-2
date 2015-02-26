package com.richardpingree.navigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Richard Pingree on 2/25/15.
 */
public class Settings extends PreferenceFragment {

    public static final String TAG = "Settings.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    SharedPreferences settings;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    public  Settings(){

    }

    public static Settings newInstance(int sectionNumber){
        Settings frag = new Settings();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

        settings = PreferenceManager.getDefaultSharedPreferences(getActivity());

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                   Settings.this.refreshDis(null);
            }
        };
        settings.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public void refreshDis(View v){

        String testData = settings.getString("dataType", "1");


        if(testData.equals("2")){
            ConnectivityManager dataManager;
            dataManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            Method dataMtd = null;
            try{
                dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            dataMtd.setAccessible(false);
            try{
                dataMtd.invoke(dataManager, false);

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
