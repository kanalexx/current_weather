package com.kanaa;

import org.junit.Test;

import static com.kanaa.ConstForTest.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StationTest extends MyTest {
    @Test
    public void getWeather() throws Exception {
        Connection conn = mock(Connection.class);
        Site site = new OWMSite(conn);
        when(conn.getAnswer(anyString())).thenReturn(OWM_VALID_JSON);
        Station station = new Station("Moscow", site);

        station.update();
        Weather weather = station.getWeather();

        assertNotNull(weather);
        assertTrue(weather instanceof OWMWeather);
        assertEquals(24, weather.getTemp(), 1e-3);
    }
}
