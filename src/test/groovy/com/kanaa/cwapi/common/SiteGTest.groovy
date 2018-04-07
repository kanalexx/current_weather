package com.kanaa.cwapi.common

import com.kanaa.cwapi.owm.OWMSite
import com.kanaa.cwapi.wu.WUSite
import org.junit.Test

import static org.mockito.Mockito.mock

class SiteGTest {
    private Context ctx = mock Context.class

    @Test
    void testEquals() {
        Site wuSite1 = new WUSite(ctx)
        Site owmSite1 = new OWMSite(ctx)
        Site wuSite2 = new WUSite(ctx)

        assert wuSite1 == wuSite2
        assert wuSite1 != owmSite1
    }
}
