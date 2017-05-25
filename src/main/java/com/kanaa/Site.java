package com.kanaa;

import org.json.JSONObject;

import java.io.IOException;

public abstract class Site {

    protected String url;
    protected final Connection connection;

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
        JSONObject data = new JSONObject(answer);
        if (hasError(data)) {
            throw new UserException(getErrorMessage(data));
        }
        return getSpecificWeather(data);
    }

    abstract String getUrlCity(String cityName);
    abstract String getSiteName();
    protected abstract String getErrorMessage(JSONObject data);
    protected abstract boolean hasError(JSONObject data);
    protected abstract Weather getSpecificWeather(JSONObject data);

}
