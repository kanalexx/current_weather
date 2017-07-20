package com.kanaa.cwapi.common;

import org.json.JSONObject;

import java.util.Date;

public abstract class Weather {
    protected JSONObject data;
    protected Date createDate;

    public Weather(JSONObject dataWeather) {
        this.data = dataWeather;
        this.createDate = new Date();
    }

    public abstract double getTemp();
    public abstract int getPressurePa();
    public int getPressureMmHg() {
        return (int) Math.round(getPressurePa() * 7.5006 / 10);
    }

    public Date getCreateDate() {
        return createDate;
    }

}
