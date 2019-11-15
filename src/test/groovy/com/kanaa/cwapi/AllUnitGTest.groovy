package com.kanaa.cwapi

import com.kanaa.cwapi.common.*
import com.kanaa.cwapi.web.SiteGTest
import com.kanaa.cwapi.web.WebGatewayGTest
import com.kanaa.cwapi.web.owm.OWMProcessorGTest
import com.kanaa.cwapi.web.wu.WUProcessorGTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
    DataObjectFactoryTest.class,
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
