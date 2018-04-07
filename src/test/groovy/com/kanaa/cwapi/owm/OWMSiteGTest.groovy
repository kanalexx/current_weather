package com.kanaa.cwapi.owm

import com.kanaa.cwapi.common.Context
import com.kanaa.cwapi.common.Site
import com.kanaa.cwapi.common.UserException
import org.junit.Before
import org.junit.Test

import static com.kanaa.cwapi.common.ConstForTest.*
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class OWMSiteGTest {

    private static final String cityName = 'City'

    private Site site
    private Context ctx = mock Context.class

    @Before
    void setUp() {
        when(ctx.getAnswer(anyString())).thenReturn(OWM_VALID_JSON)
        site = new OWMSite(ctx)
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
        when(ctx.getAnswer(anyString())).thenReturn(OWM_INVALID_CITY_JSON)
        site.getWeather(cityName)
    }

    @Test(expected = UserException.class)
    void getUserExceptionWhenInvalidAppID() {
        when(ctx.getAnswer(anyString())).thenReturn(OWM_INVALID_APPID_JSON)
        site.getWeather(cityName)
    }

}

