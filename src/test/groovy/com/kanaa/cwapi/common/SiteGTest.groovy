package com.kanaa.cwapi.common

import com.kanaa.cwapi.owm.OWMSite
import com.kanaa.cwapi.wu.WUSite
import org.junit.Test

import static org.mockito.Mockito.mock

class SiteGTest {
    private Connection conn = mock Connection.class

    @Test
    void testEquals() {
        Site wuSite1 = new WUSite(conn)
        Site owmSite1 = new OWMSite(conn)
        Site wuSite2 = new WUSite(conn)

        assert wuSite1 == wuSite2
        assert wuSite1 != owmSite1
    }
}
