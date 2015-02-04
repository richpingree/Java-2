package com.richardpingree.fragmentfile;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard Pingree on 2/3/15.
 */
public class JsonParser {

    public static final String TAG = "JsonParser.TAG";

    public static List<Artist> parseResults(String results){

        try{
            JSONArray ar = new JSONArray(results);
            ArrayList<Artist> artistList = new ArrayList<Artist>();

            for(int i = 0; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                Artist artist = new Artist();
                artist.setArtistName(obj.getString("name"));
                artist.setArtistGenre(obj.getString("genre"));
                artist.setArtistLabel(obj.getString("label"));
                artist.setArtistCity(obj.getString("city"));
                artist.setArtistState(obj.getString("state"));

                artistList.add(artist);
            }
            return artistList;

        }catch (Exception e){
            Log.e(TAG, "can not return list!");
            return null;

        }
    }
}
