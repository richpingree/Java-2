package com.richardpingree.java2fragmentsandfiles;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by richardpingree on 1/17/15.
 */
public class myTask extends AsyncTask<String, Void, List<Artist>> {

    private static final String TAG = "AsyncTask.TAG";

    private SimpleAdapter adpt;



    @Override
    protected List<Artist> doInBackground(String... params) {
        List<Artist> result = new ArrayList<Artist>();

        try {

            URL u = new URL(params[0]);

            HttpURLConnection conn = (HttpURLConnection) u.openConnection();

            conn.connect();
            InputStream is = conn.getInputStream();

            //Read the Stream
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (is.read(b) != -1)
                baos.write(b);

            String JSONResp = new String(baos.toByteArray());

            JSONArray arr = new JSONArray(JSONResp);
            for(int i=0; i< arr.length(); i++){
                result.add(convertArtist(arr.getJSONObject(i)));
            }
            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Artist convertArtist(JSONObject obj) throws JSONException{
        String artistName = obj.getString("name");
        String artistGenre = obj.getString("genre");
        String artistLabel = obj.getString("label");
        String artistCountry = obj.getString("country");
        String artistCity = obj.getString("city");
        String aritstState = obj.getString("state");


        return new Artist(artistName, artistGenre, artistLabel, artistCountry, artistCity, aritstState);
    }

    @Override
    protected void onPostExecute(List<Artist> artists) {
        super.onPostExecute(artists);
        adpt.setArtistList(artists);

    }

    //    @Override
//    protected String doInBackground(URL... urls) {
//        String apiData = "";
//
//        for (URL queryURl : urls) {
//            try {
//                URLConnection conn = queryURl.openConnection();
//                apiData = IOUtils.toString(conn.getInputStream());
//                apiData = apiData.replace("[", "");
//                apiData = apiData.replace("]", "");
//                break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Log.i(TAG, "apidata recieved: " + apiData);
//
//
//        return apiData;
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//
//    }


}