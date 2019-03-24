package com.kanaa.cwapi.common;

import java.io.IOException;

public interface WebSite {

  Weather getWeather(String cityName) throws IOException, UserException;

}
