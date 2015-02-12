package com.richardpingree.fragmentfile;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    private static final String ARG_SEARCH = "ArtistListFragment.ARG_SEARCH";

    //public Context context;
    public String apiData;
   // public String inputText;
    private OnItemClickListener mListener;
    ArrayList<Artist> artists;

    public interface ActivityCommunicator{

        //Interface methods
        void dataToFrag(String userInput);
    }
    //Interface for selected list item
    public interface OnItemClickListener{
        public void displayArtist(String name, String genre, String label, String city, String state);

    }

    public static ArtistListFragment newInstance(String userInput){
        ArtistListFragment frag = new ArtistListFragment();

        Bundle args = new Bundle();
        args.putString(ARG_SEARCH, userInput);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof OnItemClickListener) {

            mListener = (OnItemClickListener) activity;
        } else {
            throw new IllegalArgumentException("Containing activity must implement OnItemClickListener interface");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
//
        if (isOnline()) {
            try {
                String baseURL = "http://api.artistlink.com/home/accounts.json?auth_token=5xVzCSGTz4yNaaxyJbcs&name=";
                //URL queryURL = new URL(baseURL);
                URL queryURL = new URL(baseURL + args.getString(ARG_SEARCH));
                new myTask().execute(queryURL);



            } catch (Exception e) {
                //Log.e(TAG, "Invalid Search" + inputText);

            }
        }else{
            Toast.makeText(getActivity(), "Please connect to the Internet!", Toast.LENGTH_LONG).show();

            try{
                readFile();
                if(apiData != null){
                    artists = (ArrayList<Artist>) JsonParser.parseResults(apiData);
                    displayList();
                }else{
                    Toast.makeText(getActivity(), "File not found!", Toast.LENGTH_LONG).show();

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
    //creates List
    public void displayList() {
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, artists);

        setListAdapter(adapter);
    }
    //info for the selected list item
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Artist selectedArtist = artists.get(position);
        String name = selectedArtist.artistName;
        String genre = selectedArtist.artistGenre;
        String label = selectedArtist.artistLabel;
        String city = selectedArtist.artistCity;
        String state = selectedArtist.artistState;

        mListener.displayArtist(name, genre, label, city, state);



    }
    //creates file
    public void createFile() throws IOException{
        FileOutputStream fos = getActivity().openFileOutput("apidata.txt", Context.MODE_PRIVATE);
        fos.write(apiData.getBytes());
        fos.close();
    }
    //reads file
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
