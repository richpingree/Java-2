package com.richardpingree.java2fragmentsandfiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richardpingree on 1/17/15.
 */
public class ArtistData {


    public static List<Artist> parseFeed(String result) {

        try {
            JSONObject obj = new JSONObject(result);
            JSONArray array = obj.getJSONArray("");
            List<Artist> listOfArtists = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                obj = array.getJSONObject(i);
                Artist artist = new Artist();
                artist.setArtistName(obj.getString("name"));
                artist.setArtistGenre(obj.getString("genre"));

                listOfArtists.add(artist);
            }
            return listOfArtists;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
