package com.richardpingree.tabbed;

/**
 * Created by richardpingree on 2/26/15.
 */
public class WeeklyWeather {

    public String title;
    public String fctText;

    public WeeklyWeather(){

    }

    public WeeklyWeather(String day, String details){
        title = day;
        fctText = details;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(){
        this.title = title;
    }

    public String getFctText(){
        return fctText;
    }

    public void setFctText(){
        this.fctText = fctText;
    }
}
