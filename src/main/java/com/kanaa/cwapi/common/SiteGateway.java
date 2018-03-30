package com.kanaa.cwapi.common;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Шлюз внешнего сайта
 */
public abstract class SiteGateway {

  protected String url;
  protected final Connection connection;

  public SiteGateway(Connection conn) {
    connection = conn;
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
    String answer = connection.getAnswer(getUrlCity(cityName));
    JSONObject data = new JSONObject(answer);
    if (hasError(data)) {
      throw new UserException(getErrorMessage(data));
    }
    return getSpecificWeather(data);
  }

  public abstract String getUrlCity(String cityName);

  public abstract String getSiteName();

  protected abstract String getErrorMessage(JSONObject data);

  protected abstract boolean hasError(JSONObject data);

  protected abstract Weather getSpecificWeather(JSONObject data);

}
