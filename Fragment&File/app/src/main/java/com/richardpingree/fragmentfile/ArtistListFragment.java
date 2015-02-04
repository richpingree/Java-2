package com.richardpingree.fragmentfile;

import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Richard Pingree on 2/3/15.
 */
public class ArtistListFragment extends ListFragment {

    public static final String TAG = "ArtistListFragment.TAG";

    String apiData;

    List<Artist> artists;

    public static ArtistListFragment newInstance(){
        ArtistListFragment frag = new ArtistListFragment();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isOnline()) {
            try {
                String baseURL = "http://api.artistlink.com/home/accounts.json?auth_token=5xVzCSGTz4yNaaxyJbcs&name=";
                URL queryURL = new URL(baseURL + "journey");
                //URL queryURL = new URL(baseURL + inputText);
                new myTask().execute(queryURL);


            } catch (Exception e) {
                //Log.e(TAG, "Invalid Search" + inputText);

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

            artists = JsonParser.parseResults(results);
            Log.i(TAG, "artists: " + artists.toString());
            displayList();
        }
    }

    private void displayList() {
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, artists);
        setListAdapter(adapter);
    }
}
