package com.kanaa;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;

public class Station {
    private static final Logger log = Logger.getLogger(Station.class);

    private String cityName;
    private Site site;
    private String errorMessage = "";
    private JSONObject data;

    private double temp;
    private int pressure;
    private Weather weather;

    public Station(String cityName, Site site) {
        this.cityName = cityName;
        this.site = site;
    }

    public boolean update() {
        boolean noError = false;
        errorMessage = "";
        try {
            weather = site.getWeather(cityName);
            updateFields();
            noError = true;
        } catch (IOException | UserException e) {
            errorMessage = e.getMessage();
            log.error(errorMessage, e);
        }
        return noError;
    }

    private void updateFields() {
        temp = site.getTemp();
        pressure = site.getPressurePa();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSiteName() {
        return site.getSiteName();
    }

    public String getCityName() {
        return cityName;
    }

    public double getTemp() {
        return temp;
    }

    public int getPressurePa() {
        return pressure;
    }

    public int getPressureMmHg() {
        return (int) Math.round(pressure * 7.5006 / 10);
    }

    public Weather getWeather() {
        return weather;
    }
}
