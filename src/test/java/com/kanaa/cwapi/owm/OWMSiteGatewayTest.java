package com.kanaa.cwapi.owm;

import com.kanaa.cwapi.common.*;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kanaa.cwapi.common.ConstForTest.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OWMSiteGatewayTest {

  private SiteGateway siteGateway;
  private Connection conn = mock(Connection.class);

  @Before
  public void setUp() throws Exception {
    when(conn.getAnswer(anyString())).thenReturn(OWM_VALID_JSON);
    siteGateway = new OWMSiteGateway(conn);
  }

  @Test
  public void getUrlCity() throws Exception {
    String cityName = "Moscow";
    String url = siteGateway.getUrlCity(cityName);
    Pattern p;
    Matcher m;
    // начало
    p = Pattern.compile("^http://api\\.openweathermap\\.org/data/2\\.5/weather\\?.+&.+&.+");
    m = p.matcher(url);
    assertTrue(m.matches());
    // вхождение запроса
    p = Pattern.compile("q=" + cityName);
    m = p.matcher(url);
    assertTrue(m.find());
    // вхождение парметра ед. измерения
    p = Pattern.compile("units=metric");
    m = p.matcher(url);
    assertTrue(m.find());
    // вхождение appid
    p = Pattern.compile("APPID=\\w{32}");
    m = p.matcher(url);
    assertTrue(m.find());
  }

  @Test
  public void getWeather() throws Exception {
    Weather weather = siteGateway.getWeather("Moscow");
    assertNotNull(weather);
  }

  @Test(expected = UserException.class)
  public void getUserExceptionWhenInvalidCityName() throws Exception {
    when(conn.getAnswer(anyString())).thenReturn(OWM_INVALID_CITY_JSON);
    siteGateway.getWeather("City");
  }

  @Test(expected = UserException.class)
  public void getUserExceptionWhenInvalidAppID() throws Exception {
    when(conn.getAnswer(anyString())).thenReturn(OWM_INVALID_APPID_JSON);
    siteGateway.getWeather("City");
  }

}