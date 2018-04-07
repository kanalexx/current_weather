package com.kanaa.cwapi.common;

import org.json.JSONObject;

import java.io.IOException;

public abstract class Site {

    private String name;
    private String url;
    private String appId;
    private String weatherRequest;
    protected final Context ctx;

    public Site(Context conn) {
        ctx = conn;
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
        String answer = ctx.getAnswer(getUrlCity(cityName));
        JSONObject data = new JSONObject(answer);
        if (hasError(data)) {
            throw new UserException(getErrorMessage(data));
        }
        return getSpecificWeather(data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWeatherRequest() {
        return weatherRequest;
    }

    public void setWeatherRequest(String weatherRequest) {
        this.weatherRequest = weatherRequest;
    }

    public abstract String getUrlCity(String cityName);
    public abstract String getSiteName();
    protected abstract String getErrorMessage(JSONObject data);
    protected abstract boolean hasError(JSONObject data);
    protected abstract Weather getSpecificWeather(JSONObject data);

}
