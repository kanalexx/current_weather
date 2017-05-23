package com.kanaa;

import org.junit.Test;

import static com.kanaa.ConstForTest.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StationTest extends MyTest {

    private Weather getWeather(String connAnswer, Class classSite) throws Exception {
        Connection conn = mock(Connection.class);
        Site site = (Site) classSite.getConstructor(Connection.class).newInstance(conn);
        when(conn.getAnswer(anyString())).thenReturn(connAnswer);
        Station station = new Station("City", site);

        station.update();
        return station.getWeather();
    }

    private void testClassWeather(String connAnswer, Class classSite, Class expectClassWeather) throws Exception {
        Weather weather = getWeather(connAnswer, classSite);
        assertNotNull(weather);
        assertEquals(expectClassWeather, weather.getClass());
    }

    private void getTemp(String connAnswer, Class classSite, double expect) throws Exception {
        Weather weather = getWeather(connAnswer, classSite);
        assertEquals(expect, weather.getTemp(), 1e-3);
    }

    private void getPressurePa(String connAnswer, Class classSite, int expect) throws Exception {
        Weather weather = getWeather(connAnswer, classSite);
        assertEquals(expect, weather.getPressurePa());
    }

    private void getPressureMmHg(String connAnswer, Class classSite, int expect) throws Exception {
        Weather weather = getWeather(connAnswer, classSite);
        assertEquals(expect, weather.getPressureMmHg());
    }

    @Test
    public void getOWMWeather() throws Exception {
        String answer = OWM_VALID_JSON;
        Class site = OWMSite.class;
        testClassWeather(answer, site, OWMWeather.class);
        getTemp(answer, site, 24);
        getPressurePa(answer, site, 1015);
        getPressureMmHg(answer, site, 761);
    }

    @Test
    public void getWUWeather() throws Exception {
        String answer = WU_VALID_JSON;
        Class site = WUSite.class;
        testClassWeather(answer, site, WUWeather.class);
        getTemp(answer, site, 20.7);
        getPressurePa(answer, site, 1004);
        getPressureMmHg(answer, site, 753);
    }
}
