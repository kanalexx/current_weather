package com.kanaa.cwapi.web.owm

import com.kanaa.cwapi.common.UserException
import org.junit.Before
import org.junit.Test

import static com.kanaa.cwapi.common.ConstForTest.*

class OWMProcessorGTest {

  private OWMProcessor processor

  @Before
  void setUp() {
    processor = new OWMProcessor()
  }

  @Test
  void process() {
    assert processor.process(OWM_VALID_JSON)
  }

  @Test(expected = UserException.class)
  void getUserExceptionWhenInvalidCityName() {
    processor.process(OWM_INVALID_CITY_JSON)
  }

  @Test(expected = UserException.class)
  void getUserExceptionWhenInvalidAppID() {
    processor.process(OWM_INVALID_APPID_JSON)
  }

}

