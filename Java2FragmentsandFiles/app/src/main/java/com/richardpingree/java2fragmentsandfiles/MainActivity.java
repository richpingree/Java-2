// Created By Richard Pingree

package com.richardpingree.java2fragmentsandfiles;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener{
//public class MainActivity extends Activity implements View.OnClickListener, ItemListFragment.OnListViewClickListener  {

    public static final String TAG = "MainActivity.TAG";

    JSONArray apiArrayData;

    private SharedPreferences settings;



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

        if(isOnline()) {

            String baseURL = "http://api.artistlink.com/home/accounts.json?auth_token=5xVzCSGTz4yNaaxyJbcs";
            URL queryURl = null;
            try {
                queryURl = new URL(baseURL + "&name=" + getInput);
            } catch (MalformedURLException e) {
                //e.printStackTrace();
            }
            new myTask().execute(queryURl);

        }
    }
//    @Override
//    public void onClick(View v) {
//
//        //get and user input
//        String getInput = userInput.getText().toString();
//
//        //replaces spaces with underscore and puts lowercase
//        getInput = getInput.replaceAll(" ", "_").toLowerCase();
//
//        if (isOnline()) {
//            //Log.i("Test", "you have an internet connection.");
//
//            String baseURL = "http://api.artistlink.com/home/accounts.json?auth_token=5xVzCSGTz4yNaaxyJbcs";
//            URL queryURl = null;
//            try {
//                queryURl = new URL(baseURL + "&name=" + getInput);
//            } catch (MalformedURLException e) {
//                //e.printStackTrace();
//            }
//            //executes asynctask
//            try {
//
//                apiArrayData =  new myTask().execute(queryURl).get();
//
//                //JSONArray apiArrayData =  new myTask().execute(queryURl).get();
//
//                userInput.setText("");
//
//                Artist result =  new Artist(apiArrayData);
//                Log.i("Test", "results from Task" + apiArrayData.toString());
//
//                getArtist(result);
//
//
//                try {
//                    createFile(apiArrayData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//
//        }else{
//                //message for no network connection
//                Toast.makeText(getBaseContext(), "Not Connected to Network! Loading saved file!", Toast.LENGTH_LONG).show();
//                //Log.i(TAG, "internet not available");
//                try{
//                    getSavedData();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//    }


//    //reads saved filed
//    private void getSavedData() throws IOException, JSONException{
//        if(readFile() != null){
//            Artist artist = new Artist(readFile());
//            getArtist(artist);
//        }else {
//
//        }
//    }

    //gets artist info and puts it in string arrays for fragments
//    private void getArtist(Artist result) {
//        String[] names = new String[result.getNames().size()];
//        String[] genres = new String[result.getGenres().size()];
//        String[] labels = new String[result.getLabels().size()];
//        String[] countries = new String[result.getCountries().size()];
//        String[] cities = new String[result.getCities().size()];
//        String[] states = new String[result.getStates().size()];
//
//        for(int i=0; i<result.getNames().size(); i++){
//            names[i] = result.getNames().get("name_" + i).toString();
//            //Log.i(TAG, "Names returned" + Arrays.toString(names));
//            genres[i] = result.getGenres().get("genre_" + i).toString();
//            //Log.i(TAG, "Genres returned" + Arrays.toString(genres));
//            labels[i] = result.getLabels().get("label_" + i).toString();
//            //Log.i(TAG, "Labels returned" + Arrays.toString(labels));
//            countries[i] = result.getCountries().get("country_" + i).toString();
//            //Log.i(TAG, "Countries returned" + Arrays.toString(countries));
//            cities[i] = result.getCities().get("city_" + i).toString();
//            //Log.i(TAG, "Cities returned" + Arrays.toString(cities));
//            states[i] = result.getStates().get("state_" + i).toString();
//            //Log.i(TAG, "States returned" + Arrays.toString(states));
//
//        }
//        createListFrag(names, genres, labels, countries, cities, states);
//        //createDisplayFrag();
//    }

    //creates list fragment
    public void createListFrag(String[] _names, String[] _genres, String[] _labels, String[] _countries, String[] _cities, String[] _states){

        //ItemListFragment frag = (ItemListFragment) ItemListFragment.newInstance(_names);
        getFragmentManager().beginTransaction().replace(R.id.frag_container1, ItemListFragment.newInstance(_names, _genres, _labels, _countries, _cities, _states), ItemListFragment.TAG).commit();

    }
    //creates display fragment
//    public void createDisplayFrag(){
//        getFragmentManager().beginTransaction().replace(R.id.frag_container2, DisplayFragment.newInstance("", "", "", "", "", ""), DisplayFragment.TAG).commit();
//    }



    //writes file
   private void createFile(JSONArray apiArrayData) throws IOException{

       String text = apiArrayData.toString();

       FileOutputStream fos = openFileOutput("myfile.txt", MODE_PRIVATE);

       fos.write(text.getBytes());
       fos.close();
    }


    //reads file
    private JSONArray readFile() throws IOException, JSONException {

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

//    @Override
//    public void displayText(String name, String genre, String label, String country, String city, String state) {
//
//        DisplayFragment frag = (DisplayFragment) getFragmentManager().findFragmentByTag(DisplayFragment.TAG);
//
//        if(frag == null) {
//            frag = DisplayFragment.newInstance(name, genre, label, country, city, state);
//            getFragmentManager().beginTransaction().replace(R.id.frag_container2, frag, DisplayFragment.TAG).commit();
//        }else
//            frag.setDisplayText(name, genre, label, country, city, state );
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}



