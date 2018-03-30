package com.kanaa.cwapi.common;

import com.kanaa.cwapi.owm.OWMSiteGateway;
import com.kanaa.cwapi.wu.WUSiteGateway;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SiteGatewayTest {

  private Connection conn = mock(Connection.class);

  @Test
  public void testEquals() throws Exception {
    SiteGateway wuSiteGateway1 = new WUSiteGateway(conn);
    SiteGateway owmSiteGateway1 = new OWMSiteGateway(conn);
    SiteGateway wuSiteGateway2 = new WUSiteGateway(conn);

    assertEquals(wuSiteGateway1, wuSiteGateway2);
    assertNotEquals(wuSiteGateway1, owmSiteGateway1);
  }
}