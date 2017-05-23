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
        boolean noError = true;
        errorMessage = "";
        try {
            this.data = site.getWeatherData(cityName);
            if (site.hasError()) {
                errorMessage = site.getErrorMessage();
                noError = false;
            } else {
                weather = site.getWeather();
                updateFields();
            }
        } catch (IOException e) {
            this.data = new JSONObject("{}");
            errorMessage = e.getMessage();
            noError = false;
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
