package com.kanaa.cwapi.common;

import org.junit.Before;
import org.junit.Test;
import java.net.SocketException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UStationTest extends MyTest {

    private Station station;
    private Site site;

    @Before
    public void setUp() throws Exception {
        site = mock(Site.class);
        station = spy(new Station("Moscow", site));
    }

    @Test
    public void update() throws Exception {
        assertTrue(station.update());
    }

    @Test
    public void testSocketExceptionInUpdate() throws Exception {
        when(site.getWeather(anyString())).thenThrow(new SocketException("Socket is closed."));

        assertFalse(station.update());
        assertEquals("Socket is closed.", station.getErrorMessage());
    }

    @Test
    public void testUserExceptionInUpdate() throws Exception {
        when(site.getWeather(anyString())).thenThrow(new UserException("Ошибка"));

        assertFalse(station.update());
        assertEquals("Ошибка", station.getErrorMessage());
    }

}