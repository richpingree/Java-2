package com.richardpingree.multiactivity;

import java.io.Serializable;

/**
 * Created by richardpingree on 1/21/15.
 */
public class Movie implements Serializable {

    private String mTitle;
    private String mFormat;
    private String mGenre;



    public Movie(){
        mTitle = "";
        mFormat = "";
        mGenre = "";
    }

    public Movie(String title, String format, String genre){
        mTitle = title;
        mFormat = format;
        mGenre = genre;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String mTitle){
        this.mTitle = mTitle;
    }

    public String getFormat(){
        return mFormat;
    }

    public void setFormat(String mFormat){
        this.mFormat = mFormat;
    }

    public String getGenre(){
        return mGenre;
    }

    public void setGenre(String mGenre){
        this.mGenre = mGenre;
    }
}
