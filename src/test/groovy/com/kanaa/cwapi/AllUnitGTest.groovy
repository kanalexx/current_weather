package com.kanaa.cwapi

import com.kanaa.cwapi.common.*
import com.kanaa.cwapi.owm.OWMProcessorGTest
import com.kanaa.cwapi.wu.WUProcessorGTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
    WebGatewayGTest.class,
    SiteGTest.class,
    OWMProcessorGTest.class,
    WUProcessorGTest.class,
    SiteGTest.class,

    UStationGTest.class,
    StationManagerGTest.class,
])
class AllUnitGTest {
}
