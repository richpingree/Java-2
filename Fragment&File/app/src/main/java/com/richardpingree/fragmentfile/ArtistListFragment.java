package com.richardpingree.fragmentfile;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Richard Pingree on 2/3/15.
 */
public class ArtistListFragment extends ListFragment {

    public static final String TAG = "ArtistListFragment.TAG";

    String apiData;
    String inputText;
    private UserListener mListener;
    ArrayList<Artist> artists;

    public interface UserListener{

        //Interface methods
        String getInputText();
    }

    public static ArtistListFragment newInstance(){
        ArtistListFragment frag = new ArtistListFragment();
        return frag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        inputText = getArguments().getString("userInput");
//        Log.i(TAG, inputText);

        if(activity instanceof UserListener) {
            mListener = (UserListener) activity;
        } else {
            throw new IllegalArgumentException("Containing activity must implement UserListener interface");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
        if (isOnline()) {
            try {
                String baseURL = "http://api.artistlink.com/home/accounts.json?auth_token=5xVzCSGTz4yNaaxyJbcs&name=";
                //URL queryURL = new URL(baseURL);
                URL queryURL = new URL(baseURL + inputText);
                new myTask().execute(queryURL);



            } catch (Exception e) {
                //Log.e(TAG, "Invalid Search" + inputText);

            }
        }else{
            try{
                readFile();
                if(apiData != null){
                    artists = (ArrayList<Artist>) JsonParser.parseResults(apiData);
                    displayList();
                }else{

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    protected boolean isOnline() {
        ConnectivityManager connMan = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else
            return false;
    }

    private class myTask extends AsyncTask<URL, String, String> {

        final String TAG = "myTask";

        @Override
        protected String doInBackground(URL... urls) {

            String jsonString = "";

            for(URL queryURL:urls){
                try{
                    URLConnection conn =  queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                    //jsonString = jsonString.replace("[","");
                    //jsonString = jsonString.replace("]","");
                    break;
                }catch (Exception e){
                    Log.e(TAG, "Could not establish a connection to " + queryURL.toString());

                }
            }

            Log.i(TAG, "Recieved Data: " + jsonString);


            return jsonString;
        }

        @Override
        protected void onPostExecute(String results) {
            apiData = results;
            try{
                createFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            artists = (ArrayList<Artist>) JsonParser.parseResults(results);
            Log.i(TAG, "artists: " + artists.toString());
            displayList();
        }
    }

    private void displayList() {
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, artists);
        setListAdapter(adapter);
    }

    public void createFile() throws IOException{
        FileOutputStream fos = getActivity().openFileOutput("apidata.txt", Context.MODE_PRIVATE);
        fos.write(apiData.getBytes());
        fos.close();
    }

    public void readFile() throws IOException{
        FileInputStream fis = getActivity().openFileInput("apidata.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0){
            char c = (char) bis.read();
            b.append(c);
        }
        apiData = b.toString();
        bis.close();
        fis.close();
    }

}
