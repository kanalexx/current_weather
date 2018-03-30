package com.kanaa.cwapi.common;

import static com.kanaa.cwapi.common.ConstForTest.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class ConnectionTest {

  private Connection connection;

  private JSONObject getAnswerAsJSON(String request) throws IOException {
    String answer = connection.getAnswer(request);
    return new JSONObject(answer);
  }

  private InputStream getInputStream(String inputString) {
    return new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
  }

  // Тесты для openweathermap.org

  @Before
  public void setUp() throws Exception {
    connection = mock(Connection.class);
    when(connection.getAnswer(anyString())).thenCallRealMethod();
  }

  @Test
  public void testGetAnswer() throws Exception {
    when(connection.getInputStream(any(URLConnection.class)))
        .thenReturn(getInputStream(OWM_VALID_JSON));
    String answer = connection.getAnswer(OWM_VALID_URL);
    JSONObject data = new JSONObject(answer);
    assertNotNull(data);
    assertTrue(data.has("main"));
  }

  @Test
  public void testEmptyRequest() throws Exception {
    String answer = connection.getAnswer("");
    assertEquals("", answer);
  }

  @Test
  public void testInvalidAppIDRequest() throws Exception {
    when(connection.getInputStream(any(URLConnection.class)))
        .thenReturn(getInputStream(OWM_INVALID_APPID_JSON));
    JSONObject data = getAnswerAsJSON(OWM_INVALID_APPID_URL);
    assertNotNull(data);
    assertTrue(data.has("cod") && data.has("message"));
  }

  @Test
  // Возможно лишний тест, т.к. повторяет предыдущий
  public void testInvalidCityRequest() throws Exception {
    when(connection.getInputStream(any(URLConnection.class)))
        .thenReturn(getInputStream(OWM_INVALID_CITY_JSON));
    JSONObject data = getAnswerAsJSON(OWM_INVALID_CITY_URL);
    assertNotNull(data);
    assertTrue(data.has("cod") && data.has("message"));
  }

  // Тесты для wunderground.com

  @Test
  public void testGetAnswerWU() throws Exception {
    when(connection.getInputStream(any(URLConnection.class)))
        .thenReturn(getInputStream(WU_VALID_JSON));
    JSONObject data = getAnswerAsJSON(WU_VALID_URL);
    assertNotNull(data);
    assertTrue(data.has("current_observation"));
  }

  @Test
  public void testInvalidCityRequestWU() throws Exception {
    when(connection.getInputStream(any(URLConnection.class)))
        .thenReturn(getInputStream(WU_INVALID_CITY_JSON));
    JSONObject data = getAnswerAsJSON(WU_INVALID_CITY_URL);
    assertNotNull(data);
    assertTrue(data.getJSONObject("response").has("error"));
    assertTrue(data.getJSONObject("response").getJSONObject("error").has("description"));
  }

  @Test
  // Возможно лишний тест, т.к. повторяет предыдущий
  public void testInvalidAppIDRequestWU() throws Exception {
    when(connection.getInputStream(any(URLConnection.class)))
        .thenReturn(getInputStream(WU_INVALID_APPID_JSON));
    JSONObject data = getAnswerAsJSON(WU_INVALID_APPID_URL);
    assertNotNull(data);
    assertTrue(data.getJSONObject("response").has("error"));
    assertTrue(data.getJSONObject("response").getJSONObject("error").has("description"));
  }

}
