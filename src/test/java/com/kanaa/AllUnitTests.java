package com.kanaa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConnectionTest.class,

        SiteTest.class,
        OWMSiteTest.class,
        WUSiteTest.class,

        UStationTest.class,
        StationManagerTest.class
})
public class AllUnitTests {
}
