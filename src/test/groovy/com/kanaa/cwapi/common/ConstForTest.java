package com.kanaa.cwapi.common;

import org.junit.Test;

public class ConstForTest {
  // Константы для openweathermap.org
  public static final String OWM_VALID_JSON = "{\"coord\":{\"lon\":37.62,\"lat\":55.75},\"weather\":[{\"id\":800,\"main\":\"Clear\"," +
      "\"description\":\"clear sky\"," +
      "\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":24,\"pressure\":1015,\"humidity\":33,\"temp_min\":23,\"temp_max\":25}," +
      "\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":240},\"clouds\":{\"all\":0},\"dt\":1493726400,\"sys\":{\"type\":1,\"id\":7323," +
      "\"message\":0.0029,\"country\":\"RU\",\"sunrise\":1493689392,\"sunset\":1493745060},\"id\":524901,\"name\":\"Moscow\",\"cod\":200}";
  public static final String OWM_INVALID_CITY_JSON = "{\"cod\":\"404\",\"message\":\"city not found\"}";
  public static final String OWM_INVALID_APPID_JSON = "{\"cod\":401, \"message\": \"Invalid API key. Please see http://openweathermap.org/" +
      "faq#error401 for more info.\"}";
  public static final String OWM_VALID_URL = "http://api.openweathermap.org/data/2.5" +
      "/weather?units=metric&q=Moscow&APPID=ce93f7bfb9ee94a56b6f0f36743b1227";
  public static final String OWM_INVALID_APPID_URL = "http://api.openweathermap.org/data/2.5" +
      "/weather?units=metric&q=Moscow&APPID=ce93f7bfb9ee94a56b6f0f36743b1228";
  public static final String OWM_INVALID_CITY_URL = "http://api.openweathermap.org/data/2.5" +
      "/weather?units=metric&q=1111111122222222333333&APPID=ce93f7bfb9ee94a56b6f0f36743b1227";

  // Констатнты для wunderground.com
  public static final String WU_VALID_URL = "http://api.wunderground.com/api/bf926c867532af8d/conditions/q/Moscow,RU.json";
  public static final String WU_VALID_JSON = "{\"current_observation\": {\"temp_c\": 20.7,\"pressure_mb\": \"1004\"}}";
  public static final String WU_INVALID_CITY_URL = "http://api.wunderground.com/api/bf926c867532af8d/conditions/q/RU/qqqq.json";
  public static final String WU_INVALID_CITY_JSON = "{\"response\": {\"error\": {\"type\": \"querynotfound\",\"description\": \"No cities match " +
      "your search query\"}}}";
  public static final String WU_INVALID_APPID_URL = "http://api.wunderground.com/api/bf926c867532af8e/conditions/q/RU/Moscow.json";
  public static final String WU_INVALID_APPID_JSON = "{\"response\": {\"error\": {\"type\": \"keynotfound\",\"description\": \"this key does not " +
      "exist\"}}}";
  public static final String WU_INEXACT_CITY_JSON = "{\"response\": {\"results\": [{\"name\": \"Moscow\"}]}}";

  // Констатнты для weatherbit.io
  public static final String WB_VALID_JSON = "{\"data\":[{\"rh\":62,\"pod\":\"d\",\"lon\":37.61556,\"pres\":1011.8," +
      "\"timezone\":\"Europe\\/Moscow\",\"ob_time\":\"2019-11-20 13:06\",\"country_code\":\"RU\",\"clouds\":14," +
      "\"ts\":1574255160,\"solar_rad\":4.7,\"state_code\":\"48\",\"city_name\":\"Moscow\",\"wind_spd\":0.89," +
      "\"last_ob_time\":\"2019-11-20T13:06:00\",\"wind_cdir_full\":\"south\",\"wind_cdir\":\"S\",\"slp\":1027.5," +
      "\"vis\":5,\"h_angle\":90,\"sunset\":\"13:15\",\"dni\":44.04,\"dewpt\":-4.8,\"snow\":0,\"uv\":2.40774," +
      "\"precip\":0,\"wind_dir\":180,\"sunrise\":\"05:14\",\"ghi\":4.69,\"dhi\":10.97,\"aqi\":30,\"lat\":55.75222," +
      "\"weather\":{\"icon\":\"c02d\",\"code\":\"801\",\"description\":\"Few clouds\"},\"datetime\":\"2019-11-20:13\"," +
      "\"temp\":1.7,\"station\":\"E8051\",\"elev_angle\":0.74,\"app_temp\":1.3}],\"count\":1}";
  public static final String WB_INVALID_APPID_JSON = "{\"error\":\"API key not valid, or not yet activated.\"}";

  @Test
  public void test() throws Exception {

  }
}
