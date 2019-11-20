package com.kanaa.cwapi.web.wb;

import com.kanaa.cwapi.common.UserException;
import com.kanaa.cwapi.common.Weather;
import com.kanaa.cwapi.web.Processor;
import org.json.JSONObject;

/**
 * Обработчик ответа от weatherbit.io
 */
public class WBProcessor implements Processor {

  @Override
  public Weather process(String answer) throws UserException {
    JSONObject data = new JSONObject(answer);
    if (hasError(data)) {
      throw new UserException(getErrorMessage(data));
    }
    Weather weather = new Weather();
    if (data.getInt("count") > 0) {
      JSONObject jsonWeather = data.getJSONArray("data").getJSONObject(0);
      weather.setTemp(jsonWeather.getDouble("temp"));
      // давление в милибарах (1мб = 100па)
      weather.setPressurePa((int) (jsonWeather.getDouble("pres")*100));
    }
    return weather;
  }

  private String getErrorMessage(JSONObject data) {
    String errorMessage = "";
    if (data.has("error")) {
      String error = data.getString("error");
      errorMessage = String.format(
          "Ошибка: %s ", error);
//    } else if (data.has(RESPONSE) && (data.getJSONObject(RESPONSE).has("results"))) {
//      errorMessage = "Существует несколько городов с таким названием. Укажите страну.";
    }
    return errorMessage;
  }

  private boolean hasError(JSONObject data) {
    return (data.length() == 0 || (data.has("error")));
  }


}