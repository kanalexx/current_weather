package com.kanaa.cwapi.wu;

import com.kanaa.cwapi.common.Context;
import com.kanaa.cwapi.common.Site;
import com.kanaa.cwapi.common.Weather;
import org.json.JSONObject;

public class WUSite extends Site {

    private static final String RESPONSE = "response";
    private static final String ERROR = "error";

    public WUSite(Context ctx) {
        super(ctx);
    }

    @Override
    public String getUrl() {
        return "wunderground.com";
    }

    @Override
    public String getUrlCity(String cityName) {
        return String.format(
                "http://api.wunderground.com/api/bf926c867532af8d/conditions/q/%s.json",
                cityName);
    }

    protected boolean hasError(JSONObject data) {
        return (data.length() == 0
                || (data.has(RESPONSE) && data.getJSONObject(RESPONSE).has(ERROR))
                || !data.has("current_observation"));
    }

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

    protected Weather getSpecificWeather(JSONObject data) {
        return new WUWeather(data);
    }

}
