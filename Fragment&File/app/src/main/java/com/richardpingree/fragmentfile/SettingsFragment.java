package com.richardpingree.fragmentfile;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by Richard Pingree on 2/13/15.
 */
public class SettingsFragment extends PreferenceFragment {

    public static final String TAG = "SettingsFragment.TAG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load the preverences from XML file
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Preference clearData = findPreference("PREF_CLICK");
        clearData.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((MainActivity) getActivity()).deleteCache();


                return true;
            }
        });

        final ListPreference colorSelect = (ListPreference) findPreference("PREF_LIST");

        colorSelect.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                int position = colorSelect.getEntries().length;

                ((MainActivity) getActivity()).changeColor(position);
                return true;
            }
        });
    }
}
