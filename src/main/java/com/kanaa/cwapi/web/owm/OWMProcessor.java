package com.kanaa.cwapi.web.owm;

import com.kanaa.cwapi.web.Processor;
import com.kanaa.cwapi.common.UserException;
import com.kanaa.cwapi.common.Weather;
import org.json.JSONObject;

public class OWMProcessor implements Processor {

  private static final String MESSAGE = "message";

  @Override
  public Weather process(String answer) throws UserException {
    JSONObject data = new JSONObject(answer);
    if (hasError(data)) {
      throw new UserException(getErrorMessage(data));
    }
    Weather weather = new Weather();
    weather.setTemp(data.getJSONObject("main").getDouble("temp"));
    weather.setPressurePa(data.getJSONObject("main").getInt("pressure"));
    return weather;
  }

  protected boolean hasError(JSONObject data) {
    return data.length() == 0 || (data.has("cod") && data.has(MESSAGE));
  }

  protected String getErrorMessage(JSONObject data) {
    String errorMessage = "";
    if (data.has("cod") && data.has(MESSAGE))
      errorMessage = String.format("Ошибка: %s. %s.", data.getInt("cod"), data.getString(MESSAGE));
    return errorMessage;
  }
}
