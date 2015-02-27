package com.richardpingree.tabbed;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Richard Pingree on 2/26/15.
 */
public class HourlyFragment extends Fragment {

    public static final String TAG = "HourlyFragment.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    public HourlyFragment() {
    }

    public static HourlyFragment newInstance(int sectionNumber){
        HourlyFragment frag = new HourlyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        frag.setArguments(args);
        return frag;
    }

    protected boolean isOnline(){
        ConnectivityManager connMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMan.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hourly, container, false);



        Button hRefresh = (Button)rootView.findViewById(R.id.hourlyRefreshButton);
        hRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Refresh Button Clicked");
                if(isOnline()){

                    try {
                        String baseURL = "http://api.wunderground.com/api/8a47fe0a727fc6f0/hourly/q/CA/San_Francisco.json";
                        URL queryURL = new URL(baseURL);
                        new CurrentWeatherTask().execute(queryURL);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


        return rootView;
    }

    private class CurrentWeatherTask extends AsyncTask<URL, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";



            //collect api response
            for (URL queryURL: urls) {
                try {
                    URLConnection conn = queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                    break;
                } catch (Exception e) {

                    //Log.e("Test", "could not connect to network" + queryURL.toString());
                }
            }
            Log.i("Test", "received data" + jsonString);


            //api string to json

            JSONObject apiData;

            try {
                apiData = new JSONObject(jsonString);
            } catch (Exception e){

                //Log.e("Test", "can not convert");
                apiData = null;
            }
            try{
                apiData = (apiData != null) ? apiData.getJSONObject("hourly_forecast"): null;
                Log.i(TAG, "API Json data recieved: " + apiData.toString());
            }catch (Exception e){
                Log.e(TAG, "could not parse the data " + apiData.toString());
            }

            return apiData;
        }

        @Override
        protected void onPostExecute(JSONObject apiData) {
            HourlyWeather result = new HourlyWeather(apiData);


        }
    }
}
