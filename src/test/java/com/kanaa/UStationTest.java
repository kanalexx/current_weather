package com.kanaa;

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
    public void getTemp() throws Exception {
        when(site.getTemp()).thenReturn((double) 24);
        station.update();
        double temp = station.getTemp();
        assertEquals(24, temp, 0.001);
    }

    @Test
    public void getPressure() throws Exception {
        when(site.getPressurePa()).thenReturn(1015);
        station.update();
        int pressurePa = station.getPressurePa();
        int pressureMmHg = station.getPressureMmHg();
        assertEquals(1015, pressurePa);
        assertEquals(761, pressureMmHg);
    }

    @Test
    public void testSocketExceptiontInUpdate() throws Exception {
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
