package com.kanaa;

import org.json.JSONObject;

public class WUSite extends Site {

    public WUSite(Connection conn) {
        super(conn);
        url = "wunderground.com";
    }

    @Override
    public String getUrlCity(String cityName) {
        return String.format(
                "http://api.wunderground.com/api/bf926c867532af8d/conditions/q/%s.json",
                cityName);
    }

    @Override
    protected boolean hasError() {
        return (data.length() == 0
                || (data.has("response") && data.getJSONObject("response").has("error"))
                || !data.has("current_observation"));
    }

    @Override
    protected String getErrorMessage() {
        String errorMessage = "";
        if (data.has("response") && data.getJSONObject("response").has("error")) {
            JSONObject error = data.getJSONObject("response").getJSONObject("error");
            errorMessage = String.format(
                    "Ошибка: %s. %s.", error.getString("type"), error.getString("description"));
        } else if (data.has("response") && (data.getJSONObject("response").has("results"))) {
            errorMessage = "Существует несколько городов с таким названием. Укажите страну.";
        }
        return errorMessage;
    }

    @Override
    public String getSiteName() {
        return "Weather API - Weather Underground (https://www.wunderground.com)";
    }

    @Override
    public double getTemp() {
        return data.getJSONObject("current_observation").getDouble("temp_c");
    }

    @Override
    public int getPressurePa() {
        return data.getJSONObject("current_observation").getInt("pressure_mb");
    }

    @Override
    protected Weather getSpecificWeather(JSONObject data) {
        return new WUWeather(data);
    }

}
