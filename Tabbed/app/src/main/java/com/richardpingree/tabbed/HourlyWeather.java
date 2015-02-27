package com.richardpingree.tabbed;

import org.json.JSONObject;

/**
 * Created by Richard Pingree on 2/26/15.
 */
public class HourlyWeather {

    public String pretty;
    public String condition;
    public String temp;

    public HourlyWeather(){

    }

    public HourlyWeather(String mPretty, String mCondition, String mTemp){
        pretty = mPretty;
        condition = mCondition;
        temp = mTemp;
    }

    public HourlyWeather(JSONObject hourlyweather) {

    }

    public String getPretty(){
        return pretty;
    }

    public void setPretty(){
        this.pretty = pretty;
    }

    public String getCondition(){
        return condition;
    }

    public void setCondition(){
        this.condition = condition;
    }

    public String getTemp(){
        return temp;
    }

    public void setTemp(){
        this.temp = temp;
    }
}
