package com.kanaa.cwapi.common;

import org.junit.Before;
import org.junit.Test;

import static com.kanaa.cwapi.common.ConstForTest.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StationTest extends MyTest {

    private Context ctx;
    private Site site;

    @Before
    public void setUp() throws Exception {
        ctx = mock(Context.class);
        site = new Site(ctx);
        site.setWeatherRequest(Site.APPID_URL_PART + Site.CITYNAME_URL_PART);
        site.setAppId("");
    }

    private Weather getWeather() throws Exception {
        Station station = new Station("City", site);
        station.update();
        return station.getWeather();
    }

    private void testClassWeather() throws Exception {
        Weather weather = getWeather();
        assertNotNull(weather);
    }

    private void getTemp(double expect) throws Exception {
        Weather weather = getWeather();
        assertEquals(expect, weather.getTemp(), 1e-3);
    }

    private void getPressurePa(int expect) throws Exception {
        Weather weather = getWeather();
        assertEquals(expect, weather.getPressurePa());
    }

    private void getPressureMmHg(int expect) throws Exception {
        Weather weather = getWeather();
        assertEquals(expect, weather.getPressureMmHg());
    }

    @Test
    public void getOWMWeather() throws Exception {
        when(ctx.getAnswer(anyString())).thenReturn(OWM_VALID_JSON);
        site.setProcessorClassName("com.kanaa.cwapi.owm.OWMProcessor");
        testClassWeather();
        getTemp(24);
        getPressurePa(1015);
        getPressureMmHg(761);
    }

    @Test
    public void getWUWeather() throws Exception {
        when(ctx.getAnswer(anyString())).thenReturn(WU_VALID_JSON);
        site.setProcessorClassName("com.kanaa.cwapi.wu.WUProcessor");
        testClassWeather();
        getTemp(20.7);
        getPressurePa(1004);
        getPressureMmHg(753);
    }
}
