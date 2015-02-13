package com.richardpingree.fragmentfile;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Richard Pingree on 2/13/15.
 */
public class SettingsFragment extends PreferenceFragment {

    public static final String TAG = "SettingsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load the preverences from XML file
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
