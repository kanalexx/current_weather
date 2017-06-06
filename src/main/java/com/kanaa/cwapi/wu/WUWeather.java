package com.kanaa.cwapi.wu;

import com.kanaa.cwapi.common.Weather;
import org.json.JSONObject;

public class WUWeather extends Weather {

    public WUWeather(JSONObject data) {
        super(data);
    }

    @Override
    public double getTemp() {
        return data.getJSONObject("current_observation").getDouble("temp_c");
    }

    @Override
    public int getPressurePa() {
        return data.getJSONObject("current_observation").getInt("pressure_mb");
    }
}