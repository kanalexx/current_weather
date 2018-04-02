package com.kanaa.cwapi

import com.kanaa.cwapi.common.*
import com.kanaa.cwapi.owm.OWMSiteGatewayGTest
import com.kanaa.cwapi.wu.WUSiteGatewayGTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
    WebGatewayGTest.class,
    SiteGatewayGTest.class,
    OWMSiteGatewayGTest.class,
    WUSiteGatewayGTest.class,

    UStationGTest.class,
    StationManagerGTest.class,
])
class AllUnitGTest {
}
