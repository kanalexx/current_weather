package com.kanaa;

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

    @Test(expected = UserException.class)
    public void hasErrorWhenInexactCity() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(WU_INEXACT_CITY_JSON);
        site.getWeather("city");
    }

    @Test(expected = UserException.class)
    public void getUserExceptionWhenInvalidCityName() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(WU_INVALID_CITY_JSON);
        site.getWeather("city");
    }

    @Test(expected = UserException.class)
    public void getUserExceptionWhenInvalidAppID() throws Exception {
        when(conn.getAnswer(anyString())).thenReturn(WU_INVALID_APPID_JSON);
        site.getWeather("City");
    }

}