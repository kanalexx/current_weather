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

    @Test
    public void test() throws Exception {

    }
}
