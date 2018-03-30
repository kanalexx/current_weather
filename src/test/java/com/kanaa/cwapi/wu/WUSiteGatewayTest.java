package com.kanaa.cwapi.wu;

import com.kanaa.cwapi.common.Connection;
import com.kanaa.cwapi.common.SiteGateway;
import com.kanaa.cwapi.common.UserException;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kanaa.cwapi.common.ConstForTest.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WUSiteGatewayTest {

  private SiteGateway siteGateway;
  private Connection conn = mock(Connection.class);

  @Before
  public void setUp() throws Exception {
    when(conn.getAnswer(anyString())).thenReturn(WU_VALID_JSON);
    siteGateway = new WUSiteGateway(conn);
  }

  @Test
  public void getUrlCity() throws Exception {
    String cityName = "Moscow";
    String url = siteGateway.getUrlCity(cityName);
    Pattern p;
    Matcher m;
    // общий формат
    p = Pattern.compile("^http://api\\.wunderground\\.com/api/\\w{16}/conditions/q/" + cityName + "\\.json$");
    m = p.matcher(url);
    assertTrue(m.matches());
  }

  @Test(expected = UserException.class)
  public void hasErrorWhenInexactCity() throws Exception {
    when(conn.getAnswer(anyString())).thenReturn(WU_INEXACT_CITY_JSON);
    siteGateway.getWeather("city");
  }

  @Test(expected = UserException.class)
  public void getUserExceptionWhenInvalidCityName() throws Exception {
    when(conn.getAnswer(anyString())).thenReturn(WU_INVALID_CITY_JSON);
    siteGateway.getWeather("city");
  }

  @Test(expected = UserException.class)
  public void getUserExceptionWhenInvalidAppID() throws Exception {
    when(conn.getAnswer(anyString())).thenReturn(WU_INVALID_APPID_JSON);
    siteGateway.getWeather("City");
  }

}