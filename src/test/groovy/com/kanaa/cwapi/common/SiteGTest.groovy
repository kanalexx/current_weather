package com.kanaa.cwapi.common

import com.kanaa.cwapi.owm.OWMSite
import com.kanaa.cwapi.wu.WUSite
import org.junit.Test

import static org.mockito.Matchers.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class SiteGTest extends MyTest {
    private Context ctx = mock Context.class

    @Test
    void testEquals() {
        Site wuSite1 = new WUSite(ctx)
        Site owmSite1 = new OWMSite(ctx)
        Site wuSite2 = new WUSite(ctx)

        assert wuSite1 == wuSite2
        assert wuSite1 != owmSite1
    }

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
