// Created By Richard Pingree

package com.richardpingree.java2fragmentsandfiles;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity implements View.OnClickListener {

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
            new myTask().execute(queryURl);
            userInput.setText("");
        } else
            //message for no network connection
            Toast.makeText(getBaseContext(), "Not Connected to Network!", Toast.LENGTH_LONG).show();
        //Log.i("Test", "internet not available");

    }

    private class myTask extends AsyncTask<URL, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";

            //collect api response
            for (URL queryURL: urls) {
                try {
                    URLConnection conn = queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                    //removes brackets from api data
                    jsonString = jsonString.replace("[","");
                    jsonString = jsonString.replace("]","");
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

                Log.e("Test", "can not convert");
                apiData = null;
            }

            return apiData;
        }

        @Override
        protected void onPostExecute(JSONObject apiData) {
            //returns data to display
            if (apiData != null){
               // Artist result = new Artist(apiData);
                //updateDisplay(result);


            }else{
                //alert to tell user that artist not in database

                AlertDialog.Builder alertView = new AlertDialog.Builder(MainActivity.this);
                alertView.setTitle("Artist Not Found!");
                alertView.setMessage("Please try a different artist.");
                alertView.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertView.show();
            }



        }
    }
}



