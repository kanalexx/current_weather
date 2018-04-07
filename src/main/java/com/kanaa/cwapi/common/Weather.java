package com.kanaa.cwapi.common;

import org.json.JSONObject;

import java.util.Date;

public class Weather {
    protected JSONObject data;
    protected Date createDate;

    private double temp;
    private int pressure;

    public Weather() {
        this.createDate = new Date();
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressurePa() {
        return pressure;
    }

    public void setPressurePa(int pressure) {
        this.pressure = pressure;
    }

    public int getPressureMmHg() {
        return (int) Math.round(getPressurePa() * 7.5006 / 10);
    }

    public Date getCreateDate() {
        return createDate;
    }

}
