package com.richardpingree.fragmentfile;

/**
 * Created by richardpingree on 2/3/15.
 */
public class Artist {

    public String artistName;
    public String artistGenre;
    public String artistLabel;
    public String artistCity;
    public String artistState;

    public Artist(){}

    public Artist(String name, String genre, String label, String city, String state) {
        this.artistName = name;
        this.artistGenre = genre;
        this.artistLabel = label;
        this.artistCity = city;
        this.artistState = state;
    }

    public String getArtistName(){
        return artistName;
    }

    public void setArtistName(String artistName){
        this.artistName = artistName;
    }

    public String getArtistGenre(){
        return artistGenre;
    }

    public void setArtistGenre(String artistGenre){
        this.artistGenre = artistGenre;
    }

    public String getArtistLabel(){
        return artistLabel;
    }

    public void setArtistLabel(String artistLabel){
        this.artistLabel = artistLabel;
    }

    public String getArtistCity(){
        return artistCity;
    }

    public void setArtistCity(String artistCity){
        this.artistCity = artistCity;
    }

    public String getArtistState(){
        return artistState;
    }

    public void setArtistState(String artistState){
        this.artistState = artistState;
    }

    @Override
    public String toString() {
        return artistName;
    }
}
