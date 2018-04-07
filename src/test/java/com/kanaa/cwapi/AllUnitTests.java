package com.kanaa.cwapi;

import com.kanaa.cwapi.common.*;
import com.kanaa.cwapi.owm.OWMSiteTest;
import com.kanaa.cwapi.wu.WUSiteTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WebGatewayTest.class,

        SiteTest.class,
        OWMSiteTest.class,
        WUSiteTest.class,

        UStationTest.class,
        StationManagerTest.class
})
public class AllUnitTests {
}
