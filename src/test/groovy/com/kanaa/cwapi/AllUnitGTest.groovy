package com.kanaa.cwapi

import com.kanaa.cwapi.common.*
import com.kanaa.cwapi.owm.OWMSiteGTest
import com.kanaa.cwapi.wu.WUSiteGTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
    WebGatewayGTest.class,
    SiteGTest.class,
    OWMSiteGTest.class,
    WUSiteGTest.class,
    SiteGTest.class,

    UStationGTest.class,
    StationManagerGTest.class,
])
class AllUnitGTest {
}
