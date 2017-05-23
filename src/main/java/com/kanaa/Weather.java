package com.kanaa;

import org.json.JSONObject;

public abstract class Weather {
    JSONObject weather;

    public Weather(JSONObject dataWeather) {
        this.weather = dataWeather;
    }

    public abstract double getTemp();
}
