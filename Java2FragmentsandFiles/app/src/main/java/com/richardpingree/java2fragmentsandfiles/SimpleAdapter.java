package com.richardpingree.java2fragmentsandfiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by richardpingree on 1/18/15.
 */
public class SimpleAdapter extends ArrayAdapter<Artist> {

    private List<Artist> artistList;
    private Context context;

    public SimpleAdapter(List<Artist> artistList, Context ctx){
        super(ctx, android.R.layout.simple_list_item_1, artistList);
        this.artistList = artistList;
        this.context = ctx;
    }


    public int getCount() {
        if(artistList != null)
            return artistList.size();
        return 0;
    }

    public Artist getArtist(int position){
        if(artistList != null)
            return artistList.get(position);
        return null;
    }

    public long getArtistId(int position){
        if(artistList != null)
            return artistList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

        Artist a = artistList.get(position);
        TextView text = (TextView) v.findViewById(R.id.name);
        text.setText(a.getArtistName());

        return v;

    }

    public List<Artist> getArtistList(){
        return artistList;
    }

    public void setArtistList(List<Artist> artistList){
        this.artistList = artistList;
    }
}
