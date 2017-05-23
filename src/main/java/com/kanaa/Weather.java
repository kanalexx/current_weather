package com.kanaa;

import org.json.JSONObject;

public abstract class Weather {
    JSONObject weather;

    public Weather(JSONObject dataWeather) {
        this.weather = dataWeather;
    }

    public abstract double getTemp();
    public abstract int getPressurePa();
    public int getPressureMmHg() {
        return (int) Math.round(getPressurePa() * 7.5006 / 10);
    }
}
