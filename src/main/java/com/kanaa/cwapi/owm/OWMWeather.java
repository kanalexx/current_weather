package com.kanaa.cwapi.owm;

import com.kanaa.cwapi.common.Weather;
import org.json.JSONObject;

public class OWMWeather extends Weather {

  public OWMWeather(JSONObject dataWeather) {
    super(dataWeather);
  }

  @Override
  public double getTemp() {
    return data.getJSONObject("main").getDouble("temp");
  }

  @Override
  public int getPressurePa() {
    return data.getJSONObject("main").getInt("pressure");
  }
}
