package com.kanaa;


public class OWMSite extends Site {

    public OWMSite(Connection conn) {
        super(conn);
        url = "openweathermap.org";
    }

    @Override
    public String getUrlCity(String cityName) {
        return String.format(
                "http://api.openweathermap.org/data/2.5/weather?units=metric&q=%s&APPID=ce93f7bfb9ee94a56b6f0f36743b1227",
                cityName);
    }

    @Override
    public boolean hasError() {
        return (data.length() == 0 || (data.has("cod") && data.has("message")));
    }

    @Override
    public String getErrorMessage() {
        String errorMessage = "";
        if (data.has("cod") && data.has("message"))
            errorMessage = String.format("Ошибка: %s. %s.", data.getInt("cod"), data.getString("message"));
        return errorMessage;
    }

    @Override
    public String getSiteName() {
        return "Weather API - OpenWeatherMap (https://openweathermap.org)";
    }

    @Override
    public double getTemp() {
        return data.getJSONObject("main").getDouble("temp");
    }

    @Override
    public int getPressurePa() {
        return data.getJSONObject("main").getInt("pressure");
    }

    @Override
    public Weather getWeather() {
        return new OWMWeather(data);
    }
}
