package com.kanaa;

import org.json.JSONObject;

public class WUSite extends Site {

    private static final String RESPONSE = "response";
    private static final String ERROR = "error";

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
    protected boolean hasError(JSONObject data) {
        return (data.length() == 0
                || (data.has(RESPONSE) && data.getJSONObject(RESPONSE).has(ERROR))
                || !data.has("current_observation"));
    }

    @Override
    protected String getErrorMessage(JSONObject data) {
        String errorMessage = "";
        if (data.has(RESPONSE) && data.getJSONObject(RESPONSE).has(ERROR)) {
            JSONObject error = data.getJSONObject(RESPONSE).getJSONObject(ERROR);
            errorMessage = String.format(
                    "Ошибка: %s. %s.", error.getString("type"), error.getString("description"));
        } else if (data.has(RESPONSE) && (data.getJSONObject(RESPONSE).has("results"))) {
            errorMessage = "Существует несколько городов с таким названием. Укажите страну.";
        }
        return errorMessage;
    }

    @Override
    public String getSiteName() {
        return "Weather API - Weather Underground (https://www.wunderground.com)";
    }

    @Override
    protected Weather getSpecificWeather(JSONObject data) {
        return new WUWeather(data);
    }

}
