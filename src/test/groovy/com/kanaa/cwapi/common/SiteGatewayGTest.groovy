package com.kanaa.cwapi.common

import com.kanaa.cwapi.owm.OWMSiteGateway
import com.kanaa.cwapi.wu.WUSiteGateway
import org.junit.Test

import static org.mockito.Mockito.mock

class SiteGatewayGTest {
  private WebGateway conn = mock WebGateway.class

  @Test
  void testEquals() {
    SiteGateway wuSite1 = new WUSiteGateway(conn)
    SiteGateway owmSite1 = new OWMSiteGateway(conn)
    SiteGateway wuSite2 = new WUSiteGateway(conn)

    assert wuSite1 == wuSite2
    assert wuSite1 != owmSite1
  }
}
