// Created By Richard Pingree

package com.richardpingree.java2fragmentsandfiles;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "MainActivity.TAG";

//    public static final String ARTIST_NAME = "artistName";
//    public static final String ARTIST_GENRE = "artistGenre";
//    public static final String ARTIST_LABEL = "artistLabel";
//    public static final String ARTIST_COUNTRY = "artistCountry";
//    public static final String ARTIST_CITY = "artistCity";
//    public static final String ARTIST_STATE = "artistState";





    EditText userInput;
    Button btn;

    ArrayList<Artist> arrayApiData = new ArrayList<>();

    public class Artist {
        public String artistName;
        public String artistGenre;
        public String artistLabel;
        public String artistCountry;
        public String artistCity;
        public String artistState;
    }




     public static ArrayList<String> resultsRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.userText);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);



    }

    protected boolean isOnline() {
        ConnectivityManager connMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else
            return false;
    }

    @Override
    public void onClick(View v) {

        //get and user input
        String getInput = userInput.getText().toString();

        //replaces spaces with underscore and puts lowercase
        getInput = getInput.replaceAll(" ", "_").toLowerCase();

        if (isOnline()){
            //Log.i("Test", "you have an internet connection.");

            String baseURL = "http://api.artistlink.com/home/accounts.json?auth_token=5xVzCSGTz4yNaaxyJbcs";
            URL queryURl = null;
            try {
                queryURl = new URL(baseURL + "&name=" + getInput);
            } catch (MalformedURLException e) {
                //e.printStackTrace();
            }
            //executes asynctask
            try {

                JSONArray apiDataArray = new myTask().execute(queryURl).get();

                //puts data into Artist class
//
//                String[]names = new String[10];
//                String[]genres = new String[10];
//                String[]labels = new String[10];
//                String[]countries = new String[10];
//                String[]cities = new String[10];
//                String[]states = new String[10];

                for (int i = 0; i<apiDataArray.length();i++){
                    JSONObject data = apiDataArray.getJSONObject(i);
                    Artist resultsRow = new Artist();

                    resultsRow.artistName = data.getString("name");
                    Log.e("TAG", resultsRow.artistName);
                    resultsRow.artistGenre = data.getString("genre");
                    resultsRow.artistLabel = data.getString("label");
                    resultsRow.artistCountry = data.getString("country");
                    resultsRow.artistCity = data.getString("city");
                    resultsRow.artistState = data.getString("state");

                    arrayApiData.add(resultsRow);
//                    for(int i=0;i<data.get("name".length(); i++)){
//                        names[i] = data.getString("name");
//                    }
//
                }


                for (int i = 0; i <resultsRow.size(); i++){

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                createFile(arrayApiData);
            } catch (IOException e) {
                e.printStackTrace();
            }

            createListFrag(arrayApiData);
            userInput.setText("");



        } else
            //message for no network connection

            Toast.makeText(getBaseContext(), "Not Connected to Network!", Toast.LENGTH_LONG).show();
        //Log.i(TAG, "internet not available");

    }

    public void createListFrag(ArrayList<Artist> arrayApiData){
        getFragmentManager().beginTransaction().replace(R.id.frag_container1, ItemListFragment.newInstance(this.arrayApiData), ItemListFragment.TAG).commit();
    }

    public void createDisplyFrag(){
        getFragmentManager().beginTransaction().replace(R.id.frag_container2, DisplayFragment.newInstance(this.arrayApiData), DisplayFragment.TAG).commit();
    }
    //writes file
   private void createFile(ArrayList<Artist> apiDataArray) throws IOException{

       String text = apiDataArray.toString();

       FileOutputStream fos = openFileOutput("myfile.txt", MODE_PRIVATE);

       fos.write(text.getBytes());
       fos.close();
    }
    //reads file
    private JSONArray readfile() throws IOException, JSONException {

        FileInputStream fis = openFileInput("myfile.txt");

        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();

        while(bis.available() != 0){
            char c = (char) bis.read();
            b.append(c);

            bis.close();
            fis.close();

        }
        JSONArray data = new JSONArray(b.toString());

        return data;
    }


    private class myTask extends AsyncTask<URL, Integer, JSONArray> {

        private static final String TAG = "AsyncTask.TAG";
        @Override
        protected JSONArray doInBackground(URL... urls) {

            String jsonString = "";

            //collect api response
            for (URL queryURL: urls) {
                try {
                    URLConnection conn = queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                    //removes brackets from api data
//                    jsonString = jsonString.replace("[","");
//                    jsonString = jsonString.replace("]","");
                    break;
                } catch (Exception e) {

                    //Log.e(TAG, "could not connect to network" + queryURL.toString());
                }
            }
           Log.i(TAG, "received data" + jsonString);


            //api string to json

            JSONArray apiData;

            try {
                apiData = new JSONArray(jsonString);
                Log.i("Test", "jsonarray" + apiData.toString());


            } catch (Exception e){

                Log.e("Test", "can not convert");
                apiData = null;
            }

//            try{
//                apiData = (apiData != null) ? apiData.getJSONObject(""): null;
//                assert apiData != null;
//                artistArray = apiData.getJSONArray("");
//
//                for(int i=0; i<artistArray.length(); i++){
//                    JSONObject artist = artistArray.getJSONObject(i);
//
//                    String name = artist.getString("artistName");
//                    String genre = artist.getString("artistGenre");
//                    String label = artist.getString("artistLabel");
//                    String country = artist.getString("artistCountry");
//                    String city = artist.getString("artistCity");
//                    String state = artist.getString("artistState");
//
//
//                    HashMap<String, String> artistInfo = new HashMap<String,String>();
//                    artistInfo.put(ARTIST_NAME, name);
//                    artistInfo.put(ARTIST_GENRE, genre);
//                    artistInfo.put(ARTIST_LABEL, label);
//                    artistInfo.put(ARTIST_COUNTRY, country);
//                    artistInfo.put(ARTIST_CITY, city);
//                    artistInfo.put(ARTIST_STATE, state);
//
//                    artistList.add(artistInfo);
//
//                    Log.i(TAG, "retrieved data: " + artistList);
//
//
//                }
//            } catch (JSONException e) {
//                Log.i(TAG, "could not parse" + artistArray);
//                apiData = null;
//
//
//                e.printStackTrace();
//            }
//            //return null;
            return apiData;
        }

//        @Override
//        protected void onPostExecute(JSONObject apiData) {
//
//            JSONArray artistArray;
//            ArrayList<String> artistList;
//
//            try{
//                artistArray = new JSONArray(apiData);
//                for (int i = 0; i<artistArray.length(); i++){
//                    JSONObject artistInfo = artistArray.getJSONObject(i);
//
//
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            ItemListFragment frag = (ItemListFragment) getFragmentManager().findFragmentByTag(ItemListFragment.TAG);
//
//            if(frag == null){
//                frag = ItemListFragment.newInstance(artistList);
//            }
//            //returns data to display
//            //if (apiData != null){
//              //  Artist result = new Artist(apiData);
//              //  updateDisplay(result);
//            //JSONArray resultsArray = new JSONArray();
//            //resultsArray.put(apiData);
//
//
//            }
//        else{
//                //alert to tell user that artist not in database
//
//                AlertDialog.Builder alertView = new AlertDialog.Builder(MainActivity.this);
//                alertView.setTitle("Artist Not Found!");
//                alertView.setMessage("Please try a different artist.");
//                alertView.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                alertView.show();
//            }
//
//
//
//        }
   }


}



