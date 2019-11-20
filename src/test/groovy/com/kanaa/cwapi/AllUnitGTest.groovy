package com.kanaa.cwapi

import com.kanaa.cwapi.common.*
import com.kanaa.cwapi.web.SiteDictionaryTest
import com.kanaa.cwapi.web.SiteGTest
import com.kanaa.cwapi.web.WebGatewayGTest
import com.kanaa.cwapi.web.owm.OWMProcessorGTest
import com.kanaa.cwapi.web.wb.WBProcessorTest
import com.kanaa.cwapi.web.wu.WUProcessorGTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
    // web
    WebGatewayGTest.class,
    SiteGTest.class,
    OWMProcessorGTest.class,
    WUProcessorGTest.class,
    WBProcessorTest.class,
    SiteDictionaryTest.class,
    // common
    DataObjectFactoryTest.class,
    UStationGTest.class,
    StationManagerGTest.class,
])
class AllUnitGTest {
}
