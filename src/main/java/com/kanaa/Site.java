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

    public Weather getWeather(String cityName) throws IOException, UserException {
        String answer = connection.getAnswer(getUrlCity(cityName));
        data = new JSONObject(answer);
        if (hasError()) {
            throw new UserException(getErrorMessage());
        }
        return getSpecificWeather(data);
    }

    abstract String getUrlCity(String cityName);
    protected abstract String getErrorMessage();
    abstract String getSiteName();
    protected abstract boolean hasError();
    abstract double getTemp();
    abstract int getPressurePa();

    protected abstract Weather getSpecificWeather(JSONObject data);

}
