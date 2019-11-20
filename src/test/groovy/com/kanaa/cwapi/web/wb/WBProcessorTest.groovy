package com.kanaa.cwapi.web.wb

import com.kanaa.cwapi.common.MyTest
import com.kanaa.cwapi.common.UserException
import com.kanaa.cwapi.common.Weather
import org.junit.Before
import org.junit.Test

import static com.kanaa.cwapi.common.ConstForTest.*
import static org.junit.Assert.*


/**
 * @author Alexander Kanunnikov
 */
class WBProcessorTest extends MyTest {

  private WBProcessor processor

  @Before
  void setUp() {
    processor = new WBProcessor()
  }

  @Test
  void process() {
    Weather weather = processor.process(WB_VALID_JSON)
    assertNotNull weather
    assertEquals 1.7, weather.getTemp(), 0.01
    assertEquals 101180.0, weather.getPressurePa(), 0.01
  }

//  @Test(expected = UserException.class)
//  void getUserExceptionWhenInvalidCityName() {
//    processor.process(OWM_INVALID_CITY_JSON)
//  }
//
  @Test(expected = UserException.class)
  void getUserExceptionWhenInvalidAppID() {
    processor.process(WB_INVALID_APPID_JSON)
  }
}