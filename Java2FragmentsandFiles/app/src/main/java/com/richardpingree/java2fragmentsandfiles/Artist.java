package com.richardpingree.java2fragmentsandfiles;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by richardpingree on 1/11/15.
 */
public class Artist {

    private HashMap mNames = new HashMap();
    private HashMap mGenres = new HashMap();
    private HashMap mLabels = new HashMap();
    private HashMap mCountries = new HashMap();
    private HashMap mCities = new HashMap();
    private HashMap mStates = new HashMap();

    public Artist (){};

    public Artist(JSONArray artistData) throws JSONException {
        for (int i =0; i<artistData.length(); i++){
            mNames.put("name_" + i, artistData.getJSONObject(i).getString("name"));
            mGenres.put("genre_" + i, artistData.getJSONObject(i).getString("genre"));
        }
    }

    public HashMap getNames() {
        return mNames;
    }

    public HashMap getGenres(){
        return mGenres;
    }
}
