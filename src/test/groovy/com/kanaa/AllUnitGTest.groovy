package com.kanaa

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
    ConnectionGTest.class,
    SiteTest.class,
    OWMSiteTest.class,
    WUSiteTest.class,

    UStationTest.class,
    StationManagerGTest.class,
])
class AllUnitGTest {
}
