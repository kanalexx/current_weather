package com.kanaa;

import org.json.JSONObject;

public abstract class Weather {
    protected JSONObject data;

    public Weather(JSONObject dataWeather) {
        this.data = dataWeather;
    }

    public abstract double getTemp();
    public abstract int getPressurePa();
    public int getPressureMmHg() {
        return (int) Math.round(getPressurePa() * 7.5006 / 10);
    }
}
