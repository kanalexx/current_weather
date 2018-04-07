package com.kanaa.cwapi.common;

import com.kanaa.cwapi.owm.OWMSite;
import com.kanaa.cwapi.wu.WUSite;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SiteTest {

    private WebGateway conn = mock(WebGateway.class);

    @Test
    public void testEquals() throws Exception {
        Site wuSite1 = new WUSite(conn);
        Site owmSite1 = new OWMSite(conn);
        Site wuSite2 = new WUSite(conn);

        assertEquals(wuSite1, wuSite2);
        assertNotEquals(wuSite1, owmSite1);
    }
}