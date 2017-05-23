package com.kanaa;

class ConstForTest {
	// Константы для openweathermap.org
	static final String OWM_VALID_JSON = "{\"coord\":{\"lon\":37.62,\"lat\":55.75},\"weather\":[{\"id\":800,\"main\":\"Clear\"," +
            "\"description\":\"clear sky\"," +
            "\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":24,\"pressure\":1015,\"humidity\":33,\"temp_min\":23,\"temp_max\":25}," +
            "\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":240},\"clouds\":{\"all\":0},\"dt\":1493726400,\"sys\":{\"type\":1,\"id\":7323," +
            "\"message\":0.0029,\"country\":\"RU\",\"sunrise\":1493689392,\"sunset\":1493745060},\"id\":524901,\"name\":\"Moscow\",\"cod\":200}";
    static final String OWM_INVALID_CITY_JSON = "{\"cod\":\"404\",\"message\":\"city not found\"}";
    static final String OWM_INVALID_APPID_JSON = "{\"cod\":401, \"message\": \"Invalid API key. Please see http://openweathermap.org/" +
            "faq#error401 for more info.\"}";
    static final String OWM_VALID_URL = "http://api.openweathermap.org/data/2.5" +
            "/weather?units=metric&q=Moscow&APPID=ce93f7bfb9ee94a56b6f0f36743b1227";
    static final String OWM_INVALID_APPID_URL = "http://api.openweathermap.org/data/2.5" +
            "/weather?units=metric&q=Moscow&APPID=ce93f7bfb9ee94a56b6f0f36743b1228";
    static final String OWM_INVALID_CITY_URL = "http://api.openweathermap.org/data/2.5" +
            "/weather?units=metric&q=1111111122222222333333&APPID=ce93f7bfb9ee94a56b6f0f36743b1227";
    
    // Констатнты для wunderground.com
    static final String WU_VALID_URL = "http://api.wunderground.com/api/bf926c867532af8d/conditions/q/Moscow,RU.json";
    static final String WU_VALID_JSON = "{\"response\": {\"version\": \"0.1\",\"termsofService\": \"http://www.wunderground.com/weather/api/d/terms.html\",\"features\": {\"conditions\": 1}},\"current_observation\": {\"image\": {\"url\": \"http://icons.wxug.com/graphics/wu2/logo_130x80.png\",\"title\": \"Weather Underground\",\"link\": \"http://www.wunderground.com\"},\"display_location\": {\"full\": \"Moscow, Russia\",\"city\": \"Moscow\",\"state\": \"\",\"state_name\": \"Russia\",\"country\": \"RS\",\"country_iso3166\": \"RU\",\"zip\": \"00000\",\"magic\": \"164\",\"wmo\": \"27612\",\"latitude\": \"55.75000000\",\"longitude\": \"37.61999893\",\"elevation\": \"181.1\"},\"observation_location\": {\"full\": \"SNT Kolos, Moscow, \",\"city\": \"SNT Kolos, Moscow\",\"state\": \"\",\"country\": \"RU\",\"country_iso3166\": \"RU\",\"latitude\": \"55.750000\",\"longitude\": \"37.616600\",\"elevation\": \"555 ft\"},\"estimated\": {},\"station_id\": \"IMOSCOW235\",\"observation_time\": \"Last Updated on May 6, 4:40 PM MSK\",\"observation_time_rfc822\": \"Sat, 06 May 2017 16:40:31 +0300\",\"observation_epoch\": \"1494078031\",\"local_time_rfc822\": \"Sat, 06 May 2017 16:40:52 +0300\",\"local_epoch\": \"1494078052\",\"local_tz_short\": \"MSK\",\"local_tz_long\": \"Europe/Moscow\",\"local_tz_offset\": \"+0300\",\"weather\": \"Clear\",\"temperature_string\": \"69.3 F (20.7 C)\",\"temp_f\": 69.3,\"temp_c\": 20.7,\"relative_humidity\": \"39%\",\"wind_string\": \"From the West at 3.0 MPH\",\"wind_dir\": \"West\",\"wind_degrees\": 270,\"wind_mph\": 3.0,\"wind_gust_mph\": 0,\"wind_kph\": 4.8,\"wind_gust_kph\": 0,\"pressure_mb\": \"1004\",\"pressure_in\": \"29.65\",\"pressure_trend\": \"0\",\"dewpoint_string\": \"43 F (6 C)\",\"dewpoint_f\": 43,\"dewpoint_c\": 6,\"heat_index_string\": \"NA\",\"heat_index_f\": \"NA\",\"heat_index_c\": \"NA\",\"windchill_string\": \"NA\",\"windchill_f\": \"NA\",\"windchill_c\": \"NA\",\"feelslike_string\": \"69.3 F (20.7 C)\",\"feelslike_f\": \"69.3\",\"feelslike_c\": \"20.7\",\"visibility_mi\": \"N/A\",\"visibility_km\": \"N/A\",\"solarradiation\": \"--\",\"UV\": \"-1\",\"precip_1hr_string\": \"0.00 in ( 0 mm)\",\"precip_1hr_in\": \"0.00\",\"precip_1hr_metric\": \" 0\",\"precip_today_string\": \"0.00 in (0 mm)\",\"precip_today_in\": \"0.00\",\"precip_today_metric\": \"0\",\"icon\": \"clear\",\"icon_url\": \"http://icons.wxug.com/i/c/k/clear.gif\",\"forecast_url\": \"http://www.wunderground.com/global/stations/27612.html\",\"history_url\": \"http://www.wunderground.com/weatherstation/WXDailyHistory.asp?ID=IMOSCOW235\",\"ob_url\": \"http://www.wunderground.com/cgi-bin/findweather/getForecast?query=55.750000,37.616600\",\"nowcast\": \"\"}}";
    static final String WU_INVALID_CITY_URL = "http://api.wunderground.com/api/bf926c867532af8d/conditions/q/RU/qqqq.json";
    static final String WU_INVALID_CITY_JSON = "{\"response\": {\"version\": \"0.1\",\"termsofService\": \"http://www.wunderground.com/weather/api/d/terms.html\",\"features\": {\"conditions\": 1},\"error\": {\"type\": \"querynotfound\",\"description\": \"No cities match your search query\"}}}";
    static final String WU_INVALID_APPID_URL = "http://api.wunderground.com/api/bf926c867532af8e/conditions/q/RU/Moscow.json";
    static final String WU_INVALID_APPID_JSON = "{\"response\": {\"version\": \"0.1\",\"termsofService\": \"http://www.wunderground.com/weather/api/d/terms.html\",\"features\": {},\"error\": {\"type\": \"keynotfound\",\"description\": \"this key does not exist\"}}}";
    static final String WU_INEXACT_CITY_JSON = "{\"response\": {\"version\": \"0.1\",\"termsofService\": \"http://www.wunderground.com/weather/api/d/terms.html\",\"features\": {\"conditions\": 1},\"results\": [{\"name\": \"Moscow\",\"city\": \"Moscow\",\"state\": \"\",\"country\": \"RS\",\"country_iso3166\": \"RU\",\"country_name\": \"Russia\",\"zmw\": \"00000.164.27612\",\"l\": \"/q/zmw:00000.164.27612\"}]}}";

}
