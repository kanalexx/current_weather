package com.kanaa;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SiteTest {

    private Connection conn = mock(Connection.class);

    @Test
    public void testEquals() throws Exception {
        Site wuSite1 = new WUSite(conn);
        Site owmSite1 = new OWMSite(conn);
        Site wuSite2 = new WUSite(conn);

        assertEquals(wuSite1, wuSite2);
        assertNotEquals(wuSite1, owmSite1);
    }
}