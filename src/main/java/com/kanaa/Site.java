package com.kanaa;

import org.json.JSONObject;

import java.io.IOException;

public abstract class Site {

    protected String url;
    protected Connection connection;
    protected JSONObject data;

    public Site(Connection conn) {
        connection = conn;
    }

    @Override
    public int hashCode() {
        return 35 + url.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        return (getClass() == obj.getClass());
    }

    @Override
    public String toString() {
        return url;
    }

    public JSONObject getWeatherData(String cityName) throws IOException {
        String answer = connection.getAnswer(getUrlCity(cityName));
        data = new JSONObject(answer);
        return data;
    }

    abstract String getUrlCity(String cityName);
    abstract String getErrorMessage();
    abstract String getSiteName();
    abstract boolean hasError();
    abstract double getTemp();
    abstract int getPressurePa();

    abstract Weather getWeather();

}
