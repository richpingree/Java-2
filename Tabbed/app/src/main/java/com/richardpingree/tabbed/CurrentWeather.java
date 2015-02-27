package com.richardpingree.tabbed;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Richard Pingree on 2/26/15.
 */
public class CurrentWeather {

    public String currentCity;
    public String currentTemp;
    public String currentHumidity;
    public String currentFeels;

    public CurrentWeather(){}

    public CurrentWeather(String city, String temp, String humidity, String feelLike){
        currentCity = city;
        currentTemp = temp;
        currentHumidity = humidity;
        currentFeels = feelLike;
    }

    public CurrentWeather(JSONObject currentWeather){
        try{
            currentCity = currentWeather.getString("city");
            currentTemp = currentWeather.getString("temperature_string");
            currentHumidity = currentWeather.getString("relative_humidity");
            currentFeels = currentWeather.getString("feelLike_string");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentCity(){
        return currentCity;
    }

    public void setCurrentCity(){
        this.currentCity = currentCity;
    }

    public String getCurrentTemp(){
        return currentTemp;
    }

    public void setCurrentTemp(){
        this.currentTemp = currentTemp;
    }
    public String getCurrentHumidity(){
        return currentHumidity;
    }

    public void setCurrentHumidity(){
        this.currentHumidity = currentHumidity;
    }
    public String getCurrentFeels(){
        return currentFeels;
    }

    public void setCurrentFeels(){
        this.currentFeels = currentFeels;
    }

    @Override
    public String toString(){
        return currentCity;
    }
}
