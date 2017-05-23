package com.kanaa;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kanaa.ConstForTest.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WUSiteTest {

    private Site site;
    private Connection conn = mock(Connection.class);

    @Before
    public void setUp() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(WU_VALID_JSON);
        site = new WUSite(conn);
    }

    @Test
    public void getUrlCity() throws Exception {
        String cityName = "Moscow";
        String url = site.getUrlCity(cityName);
        Pattern p;
        Matcher m;
        // общий формат
        p = Pattern.compile("^http://api\\.wunderground\\.com/api/\\w{16}/conditions/q/"+cityName+"\\.json$");
        m = p.matcher(url);
        assertTrue(m.matches());
    }

    @Test
    public void getTemp() throws Exception {
        site.getWeatherData("city");
        assertEquals(20.7, site.getTemp(), 1e-3);
    }

    @Test
    public void getPressurePa() throws Exception {
        site.getWeatherData("city");
        assertEquals(1004, site.getPressurePa());
    }

    @Test
    public void hasError() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(WU_INVALID_CITY_JSON);
//        JSONObject dataEmpty = new JSONObject("{}");

        site.getWeatherData("city");
        assertTrue(site.hasError());
//        assertTrue(site.hasError(dataEmpty));
    }

    @Test
    public void hasErrorWhenInexactCity() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(WU_INEXACT_CITY_JSON);
        site.getWeatherData("city");
        assertTrue(site.hasError());
        assertNotEquals("", site.getErrorMessage());
    }

    @Test
    public void getErrorMessage() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(WU_INVALID_CITY_JSON);
//        JSONObject dataEmpty = new JSONObject("{}");

        site.getWeatherData("city");
        assertFalse(site.getErrorMessage().isEmpty());
//        assertTrue(site.getErrorMessage(dataEmpty).isEmpty());
    }

}