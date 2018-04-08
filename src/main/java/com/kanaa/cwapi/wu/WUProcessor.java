package com.kanaa.cwapi.wu;

import com.kanaa.cwapi.common.*;
import org.json.JSONObject;

public class WUProcessor implements Processor {

  private static final String RESPONSE = "response";
  private static final String ERROR = "error";

  @Override
  public Weather process(String answer) throws UserException {
    JSONObject data = new JSONObject(answer);
    if (hasError(data)) {
      throw new UserException(getErrorMessage(data));
    }
    Weather weather = new Weather();
    weather.setTemp(data.getJSONObject("current_observation").getDouble("temp_c"));
    weather.setPressurePa(data.getJSONObject("current_observation").getInt("pressure_mb"));
    return weather;
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

}
