package com.kanaa.cwapi.owm;


import com.kanaa.cwapi.common.Connection;
import com.kanaa.cwapi.common.SiteGateway;
import com.kanaa.cwapi.common.Weather;
import org.json.JSONObject;

/**
 * Шлюз сайта OWM.
 */
public class OWMSiteGateway extends SiteGateway {

  private static final String MESSAGE = "message";

  public OWMSiteGateway(Connection conn) {
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
  protected boolean hasError(JSONObject data) {
    return data.length() == 0 || (data.has("cod") && data.has(MESSAGE));
  }

  @Override
  protected String getErrorMessage(JSONObject data) {
    String errorMessage = "";
    if (data.has("cod") && data.has(MESSAGE))
      errorMessage = String.format("Ошибка: %s. %s.", data.getInt("cod"), data.getString(MESSAGE));
    return errorMessage;
  }

  @Override
  public String getSiteName() {
    return "Weather API - OpenWeatherMap (https://openweathermap.org)";
  }

  @Override
  protected Weather getSpecificWeather(JSONObject data) {
    return new OWMWeather(data);
  }
}