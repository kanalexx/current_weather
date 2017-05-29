package com.kanaa

import org.junit.Before
import org.junit.Test

import static com.kanaa.ConstForTest.*
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class OWMSiteGTest {

    private static final String cityName = 'City'

    private Site site
    private Connection conn = mock Connection.class

    @Before
    void setUp() {
        when(conn.getAnswer(anyString())).thenReturn(OWM_VALID_JSON)
        site = new OWMSite(conn)
    }

    @Test
    void getUrlCity() {
        String url = site.getUrlCity(cityName)
        // начало
        assert url ==~ "http://api.openweathermap.org/data/2.5/weather?.+&.+&.+"
        // вхождение запроса
        assert url =~ /q=${cityName}/
        // вхождение парметра ед. измерения
        assert url =~ /units=metric/
        // вхождение appid
        assert url =~ /APPID=\w{32}/
    }

    @Test
    void getWeather() {
        assert site.getWeather(cityName)
    }

    @Test(expected = UserException.class)
    void getUserExceptionWhenInvalidCityName() {
        when(conn.getAnswer(anyString())).thenReturn(OWM_INVALID_CITY_JSON)
        site.getWeather(cityName)
    }

    @Test(expected = UserException.class)
    void getUserExceptionWhenInvalidAppID() {
        when(conn.getAnswer(anyString())).thenReturn(OWM_INVALID_APPID_JSON)
        site.getWeather(cityName)
    }

}

