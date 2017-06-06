package com.kanaa.cwapi.common;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Station {
    private static final Logger log = Logger.getLogger(Station.class);

    private String cityName;
    private Site site;
    private String errorMessage = "";
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
            noError = true;
        } catch (IOException | UserException e) {
            errorMessage = e.getMessage();
            log.error(errorMessage, e);
        }
        return noError;
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

    public Weather getWeather() {
        return weather;
    }
}
