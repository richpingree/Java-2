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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;


public class MainActivity extends Activity implements View.OnClickListener, ItemListFragment.OnListViewClickListener  {

    public static final String TAG = "MainActivity.TAG";



    EditText userInput;
    Button btn;


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

        if (isOnline()) {
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
                Artist result;

                JSONArray apiArrayData =  new myTask().execute(queryURl).get();

                userInput.setText("");

                result =  new Artist(apiArrayData);
                Log.i("Test", "results from Task" + apiArrayData.toString());

                getArtist(result);


                try {
                    createFile(apiArrayData);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
                //message for no network connection
                Toast.makeText(getBaseContext(), "Not Connected to Network!", Toast.LENGTH_LONG).show();
                //Log.i(TAG, "internet not available");
                try{
                    getSavedData();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    //reads saved filed
    private void getSavedData() throws IOException, JSONException{
        if(readfile() != null){
            Artist artist = new Artist(readfile());
            getArtist(artist);
        }else {

        }
    }

    //gets artist info and puts it in string arrays for fragments
    private void getArtist(Artist result) {
        String[] names = new String[result.getNames().size()];
        String[] genres = new String[result.getGenres().size()];
        String[] labels = new String[result.getLabels().size()];
        String[] countries = new String[result.getCountries().size()];
        String[] cities = new String[result.getCities().size()];
        String[] states = new String[result.getStates().size()];

        for(int i=0; i<result.getNames().size(); i++){
            names[i] = result.getNames().get("name_" + i).toString();
            //Log.i(TAG, "Names returned" + Arrays.toString(names));
            genres[i] = result.getGenres().get("genre_" + i).toString();
            //Log.i(TAG, "Genres returned" + Arrays.toString(genres));
            labels[i] = result.getLabels().get("label_" + i).toString();
            //Log.i(TAG, "Labels returned" + Arrays.toString(labels));
            countries[i] = result.getCountries().get("country_" + i).toString();
            //Log.i(TAG, "Countries returned" + Arrays.toString(countries));
            cities[i] = result.getCities().get("city_" + i).toString();
            //Log.i(TAG, "Cities returned" + Arrays.toString(cities));
            states[i] = result.getStates().get("state_" + i).toString();
            //Log.i(TAG, "States returned" + Arrays.toString(states));

        }
        //createListFrag(names);
        createDisplayFrag();
    }
    //creates list fragment
    public void createListFrag(String[] _names){

        getFragmentManager().beginTransaction().replace(R.id.frag_container1, ItemListFragment.newInstance(_names), ItemListFragment.TAG).commit();


    }
    //creates display fragment
    public void createDisplayFrag(){
        getFragmentManager().beginTransaction().replace(R.id.frag_container2, DisplayFragment.newInstance(""), DisplayFragment.TAG).commit();
    }
    //writes file
   private void createFile(JSONArray apiArrayData) throws IOException{

       String text = apiArrayData.toString();

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

    @Override
    public void displayText(String name) {

    }


    class myTask extends AsyncTask<URL, Integer, JSONArray> {

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
                    //jsonString = jsonString.replace("[","");
                    //jsonString = jsonString.replace("]","");
                    break;
                } catch (Exception e) {

                    //Log.e(TAG, "could not connect to network" + queryURL.toString());
                }
            }
           Log.i(TAG, "received data" + jsonString);


            //api string to json

            //JSONObject apiData;
            //JSONArray apiDataArray = null;

            JSONArray apiDataArray;
            try {
                apiDataArray = new JSONArray(jsonString);
                Log.i("Test", "jsonarray" + apiDataArray.toString());


            } catch (Exception e){
                Log.e("Test", "can not convert");
                apiDataArray = null;
            }
//            try{
//                apiData = (apiData != null) ? apiData.getJSONObject(""): null;
//                apiDataArray = apiData.getJSONArray("");
//            } catch (JSONException e) {
//                e.printStackTrace();
//                apiDataArray = null;
//            }


            return apiDataArray;
        }

//        @Override
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


}



