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

public class OWMSiteTest {

    private Site site;
    private Connection conn = mock(Connection.class);

    @Before
    public void setUp() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(OWM_VALID_JSON);
        site = new OWMSite(conn);
    }

    @Test
    public void getUrlCity() throws Exception {
        String cityName = "Moscow";
        String url = site.getUrlCity(cityName);
        Pattern p;
        Matcher m;
        // начало
        p = Pattern.compile("^http://api\\.openweathermap\\.org/data/2\\.5/weather\\?.+&.+&.+");
        m = p.matcher(url);
        assertTrue(m.matches());
        // вхождение запроса
        p = Pattern.compile("q="+cityName);
        m = p.matcher(url);
        assertTrue(m.find());
        // вхождение парметра ед. измерения
        p = Pattern.compile("units=metric");
        m = p.matcher(url);
        assertTrue(m.find());
        // вхождение appid
        p = Pattern.compile("APPID=\\w{32}");
        m = p.matcher(url);
        assertTrue(m.find());
    }

    @Test
    public void getTemp() throws Exception {
        site.getWeatherData("city");
        assertEquals(24, site.getTemp(), 1e-3);
    }

    @Test
    public void getPressurePa() throws Exception {
        site.getWeatherData("city");
        assertEquals(1015, site.getPressurePa());
    }

    @Test
    public void hasError() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(OWM_INVALID_CITY_JSON);
        //JSONObject dataEmpty = new JSONObject("{}");

        site.getWeatherData("city");
        assertTrue(site.hasError());
        //assertTrue(site.hasError(dataEmpty));
    }

    @Test
    public void getErrorMessage() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(OWM_INVALID_CITY_JSON);
//        JSONObject dataEmpty = new JSONObject("{}");

        site.getWeatherData("City");
        assertFalse(site.getErrorMessage().isEmpty());
//        assertTrue(site.getErrorMessage(dataEmpty).isEmpty());
    }

    @Test
    public void getWeatherData() throws Exception {
        JSONObject data = site.getWeatherData("Moscow");
        assertNotNull(data);
    }

}