package com.kanaa.cwapi.common;

import java.io.IOException;

/**
 * Интерфейс для получения данных о погоде от web-сайта с сервисом API Weather
 */
public interface WebSite {

  /** Получить текущую погоду в указанном городе */
  Weather getWeather(String cityName) throws IOException, UserException;
  /** Получить имя сайта */
  String getName();
  /** Получить url-адресс сайта */
  String getUrl();
}
