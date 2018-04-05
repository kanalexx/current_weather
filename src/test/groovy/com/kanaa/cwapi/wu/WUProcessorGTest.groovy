package com.kanaa.cwapi.wu

import com.kanaa.cwapi.common.UserException
import org.junit.Before
import org.junit.Test

import static com.kanaa.cwapi.common.ConstForTest.*

class WUProcessorGTest {

    private WUProcessor processor

    @Before
    void setUp() {
        processor = new WUProcessor()
    }

    @Test
    void process() {
        assert processor.process(WU_VALID_JSON)
    }

    @Test(expected = UserException.class)
    void hasErrorWhenInexactCity() {
        processor.process(WU_INEXACT_CITY_JSON)
    }

    @Test(expected = UserException.class)
    void getUserExceptionWhenInvalidCityName() {
        processor.process(WU_INVALID_CITY_JSON)
    }

    @Test(expected = UserException.class)
    void getUserExceptionWhenInvalidAppID() {
        processor.process(WU_INVALID_APPID_JSON)
    }

}
