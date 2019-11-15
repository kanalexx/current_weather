package com.kanaa.cwapi.web

import com.kanaa.cwapi.common.ConstForTest
import com.kanaa.cwapi.common.Context
import com.kanaa.cwapi.common.MyTest
import org.junit.Test

import static org.mockito.Mockito.mock

class SiteGTest extends MyTest {

    @Test
    void testEquals() {
      Site wuSite1 = new Site()
      wuSite1.setId(1);
      Site owmSite1 = new Site()
      owmSite1.setId(2);
      Site wuSite2 = new Site()
      wuSite2.setId(1);

      assert wuSite1 == wuSite2
      assert wuSite1 != owmSite1
    }

  @Test
  void testGetUrlCity() throws Exception {
    Site site = new Site()
    site.weatherRequest = "http://api.openweathermap.org/data/2.5/weather?units=metric&q=${Site.CITYNAME_URL_PART}&APPID=${Site.APPID_URL_PART}"
    site.appId = 'ce93f7bfb9ee94a56b6f0f36743b1227'

    assert site.getUrlCity("Moscow") == ConstForTest.OWM_VALID_URL
  }

}
