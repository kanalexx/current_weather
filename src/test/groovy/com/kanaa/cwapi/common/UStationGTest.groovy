package com.kanaa.cwapi.common

import org.junit.Before
import org.junit.Test

import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.when

class UStationGTest extends MyTest {

    private static final cityName = 'city'

    private Station station
    private SiteGateway site

    @Before
    void setUp() {
        site = mock SiteGateway.class
        station = spy new Station(cityName, site)
    }

    @Test
    void update() {
        assert station.update()
    }

    @Test
    void testSocketExceptionInUpdate() {
        when(site.getWeather(anyString())).thenThrow(new SocketException("Socket is closed."))

        assert !station.update()
        assert "Socket is closed." == station.getErrorMessage()
    }

    @Test
    void testUserExceptionInUpdate() {
        when(site.getWeather(anyString())).thenThrow(new UserException("Ошибка"))

        assert !station.update()
        assert "Ошибка" == station.getErrorMessage()
    }

}
