package com.kanaa.cwapi.common

import org.junit.Test

import static org.mockito.Matchers.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class SiteGTest extends MyTest {

  @Test
  void testGetUrlCity() throws Exception {
    Site site = mock Site.class
    when(site.getWeatherRequest())
        .thenReturn("http://api.openweathermap.org/data/2.5/weather?units=metric&q=" + Site.CITYNAME_URL_PART + "&APPID="+Site.APPID_URL_PART)
    when(site.getAppId()).thenReturn("ce93f7bfb9ee94a56b6f0f36743b1227")
    when(site.getUrlCity(any(String))).thenCallRealMethod()

    assert site.getUrlCity("Moscow") == ConstForTest.OWM_VALID_URL
  }
}
