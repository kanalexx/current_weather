package com.kanaa;

import org.json.JSONObject;

public class OWMWeather extends Weather{

    public OWMWeather(JSONObject dataWeather) {
        super(dataWeather);
    }

    @Override
    public double getTemp() {
        return weather.getJSONObject("main").getDouble("temp");
    }
}
