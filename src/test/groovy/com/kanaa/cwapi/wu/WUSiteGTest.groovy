package com.kanaa.cwapi.wu

import com.kanaa.cwapi.common.WebGateway
import com.kanaa.cwapi.common.Site
import com.kanaa.cwapi.common.UserException
import org.junit.Before
import org.junit.Test

import static com.kanaa.cwapi.common.ConstForTest.*
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class WUSiteGTest {

    private static final String cityName = 'City'

    private Site site
    private WebGateway conn = mock WebGateway.class

    @Before
    void setUp() {
        when(conn.getAnswer(anyString())).thenReturn(WU_VALID_JSON)
        site = new WUSite(conn)
    }

    @Test
    void getUrlCity() {
        def url = site.getUrlCity(cityName)
        // общий формат
        assert url ==~ "http://api.wunderground.com/api/\\w{16}/conditions/q/${cityName}.json"
    }

    @Test(expected = UserException.class)
    void hasErrorWhenInexactCity() {
        when(conn.getAnswer(anyString())).thenReturn(WU_INEXACT_CITY_JSON)
        site.getWeather(cityName)
    }

    @Test(expected = UserException.class)
    void getUserExceptionWhenInvalidCityName() {
        when(conn.getAnswer(anyString())).thenReturn(WU_INVALID_CITY_JSON)
        site.getWeather(cityName)
    }

    @Test(expected = UserException.class)
    void getUserExceptionWhenInvalidAppID() {
        when(conn.getAnswer(anyString())).thenReturn(WU_INVALID_APPID_JSON)
        site.getWeather(cityName)
    }

}
