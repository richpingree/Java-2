package com.richardpingree.java2fragmentsandfiles;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by richardpingree on 1/17/15.
 */
public class myTask extends AsyncTask<URL, Integer, String> {

    private static final String TAG = "AsyncTask.TAG";
//    @Override
//    protected String doInBackground(URL... urls) {
//
//        String jsonString = "";
//
//        //collect api response
//        for (URL queryURL: urls) {
//            try {
//                URLConnection conn = queryURL.openConnection();
//                jsonString = IOUtils.toString(conn.getInputStream());
//                //removes brackets from api data
//                //jsonString = jsonString.replace("[","");
//                //jsonString = jsonString.replace("]","");
//                break;
//            } catch (Exception e) {
//
//                //Log.e(TAG, "could not connect to network" + queryURL.toString());
//            }
//        }
//        Log.i(TAG, "received data" + jsonString);
//
//
//        //api string to json
//
//        //JSONObject apiData;
//        //JSONArray apiDataArray = null;
//
//        JSONArray apiDataArray;
//        try {
//            apiDataArray = new JSONArray(jsonString);
//            Log.i("Test", "jsonarray" + apiDataArray.toString());
//
//
//        } catch (Exception e){
//            Log.e("Test", "can not convert");
//            apiDataArray = null;
//        }
//            try{
//                apiData = (apiData != null) ? apiData.getJSONObject(""): null;
//                apiDataArray = apiData.getJSONArray("");
//            } catch (JSONException e) {
//                e.printStackTrace();
//                apiDataArray = null;
//            }
//
//
//        return apiDataArray;
//    }

    @Override
    protected String doInBackground(URL... urls) {
        String apiData = "";

        for(URL queryURl: urls){
            try{
                URLConnection conn = queryURl.openConnection();
                apiData = IOUtils.toString(conn.getInputStream());
                apiData = apiData.replace("[", "");
                apiData = apiData.replace("]", "");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.i(TAG, "apidata recieved: " + apiData);



        return apiData;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String apiString = result;

        List<Artist> artistList = ArtistData.parseFeed(result);
        //updateDisplay();
    }


//         @Override
//        protected void onPostExecute(JSONArray apiDataArray) {
//
//
//        //returns data to display
//        if (apiDataArray != null){
//            try {
//                Artist result = new Artist(apiDataArray);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//        else{
//            //alert to tell user that artist not in database
//
//            AlertDialog.Builder alertView = new AlertDialog.Builder(MainActivity.this);
//            alertView.setTitle("Artist Not Found!");
//            alertView.setMessage("Please try a different artist.");
//            alertView.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            alertView.show();
//        }
//
//
//
//    }
}