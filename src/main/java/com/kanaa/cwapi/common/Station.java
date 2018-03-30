package com.kanaa.cwapi.common;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

public class Station {
    private static final Logger log = Logger.getLogger(Station.class);

    /** Период актульности данных о погоде (2 часа)*/
    //TODO Перенести в файл конфигурации
    private static long WEATHER_DATA_ACTUAL_TIME = 7200000L;

    private String cityName;
    private SiteGateway siteGateway;
    private String errorMessage = "";
    private Weather weather;

    public Station(String cityName, SiteGateway siteGateway) {
        this.cityName = cityName;
        this.siteGateway = siteGateway;
    }

    public boolean update() {
        boolean noError = false;
        errorMessage = "";
        try {
            weather = siteGateway.getWeather(cityName);
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
        return siteGateway.getSiteName();
    }

    public String getCityName() {
        return cityName;
    }

    /**
     * Возвращает объект данных о текущей погоде. Если имеющиеся данные не актульные, то обновляется.
     */
    public Weather getWeather() {
        if (weather == null || !isActual(weather)) {
            update();
        }
        return weather;
    }

    /**
     * Определяет актульность данных о погоде.
     */
    public boolean isActual(Weather weather) {
        return new Date().getTime() - weather.getCreateDate().getTime() < WEATHER_DATA_ACTUAL_TIME;
    }

}
