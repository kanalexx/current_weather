package com.kanaa.cwapi;

import com.kanaa.cwapi.common.*;
import com.kanaa.cwapi.owm.OWMSiteGatewayTest;
import com.kanaa.cwapi.wu.WUSiteGatewayTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ConnectionTest.class,

    SiteGatewayTest.class,
    OWMSiteGatewayTest.class,
    WUSiteGatewayTest.class,

    UStationTest.class,
    StationManagerTest.class
})
public class AllUnitTests {
}
