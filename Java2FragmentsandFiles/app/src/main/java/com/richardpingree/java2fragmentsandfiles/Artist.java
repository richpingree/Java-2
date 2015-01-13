package com.richardpingree.java2fragmentsandfiles;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by richardpingree on 1/11/15.
 */
public class Artist  {

    private HashMap mNames = new HashMap();
    private HashMap mGenres = new HashMap();
    private HashMap mLabels = new HashMap();
    private HashMap mCountries = new HashMap();
    private HashMap mCities = new HashMap();
    private HashMap mStates = new HashMap();


    public Artist (){};

    public Artist(JSONArray artistData) throws JSONException {
        //JSONArray artistData = new JSONArray();

        for (int i =0; i<artistData.length(); i++){

            mNames.put("name_" + i, artistData.getJSONObject(i).getString("name"));
            mGenres.put("genre_" + i, artistData.getJSONObject(i).getString("genre"));
            mLabels.put("label_" + i, artistData.getJSONObject(i).getString("label"));
            mCountries.put("country_" + i, artistData.getJSONObject(i).getString("country"));
            mCities.put("city_" + i, artistData.getJSONObject(i).getString("city"));
            mStates.put("state_" + i, artistData.getJSONObject(i).getString("state"));
        }
    }

    public HashMap getNames() {
        return mNames;
    }

    public HashMap getGenres(){
        return mGenres;
    }

    public HashMap getLabels(){
        return mLabels;
    }

    public HashMap getCountries(){
        return mCountries;
    }
    public HashMap getCities(){
        return mCities;
    }

    public HashMap getStates(){
        return mStates;
    }



}
