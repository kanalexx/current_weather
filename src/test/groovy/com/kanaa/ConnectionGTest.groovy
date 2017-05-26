package com.kanaa

import org.json.JSONObject
import org.junit.Before
import org.junit.Test

import java.nio.charset.StandardCharsets

import static com.kanaa.ConstForTest.*
import static org.mockito.Matchers.any
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

/**
 *
 * @author Alexander Kanunnikov
 */
class ConnectionGTest {
    private Connection connection

    private JSONObject getAnswerAsJSON(String request) {
        String answer = connection.getAnswer(request)
        new JSONObject(answer)
    }

    private InputStream getInputStream(String inputString) {
        new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8))
    }

    @Before
    void setUp() {
        connection = mock Connection.class
        when(connection.getAnswer(anyString())).thenCallRealMethod()
    }

    // Тесты для openweathermap.org

    @Test
    void testGetAnswer() {
        when(connection.getInputStream(any(URLConnection.class)))
                .thenReturn(getInputStream(OWM_VALID_JSON))
        def answer = connection.getAnswer(OWM_VALID_URL)
        JSONObject data = new JSONObject(answer)
        assert data
        assert data.has("main")
    }

    @Test
    void testEmptyRequest() {
        def answer = connection.getAnswer('')
        assert answer == ''
    }

    @Test
    void testInvalidAppIDRequest() {
        when(connection.getInputStream(any(URLConnection.class)))
                .thenReturn(getInputStream(OWM_INVALID_APPID_JSON))
        JSONObject data = getAnswerAsJSON(OWM_INVALID_APPID_URL)
        assert data
        assert data.has('cod') && data.has('message')
    }

    @Test
    // Возможно лишний тест, т.к. повторяет предыдущий
    void testInvalidCityRequest() throws Exception {
        when(connection.getInputStream(any(URLConnection.class)))
                .thenReturn(getInputStream(OWM_INVALID_CITY_JSON))
        JSONObject data = getAnswerAsJSON(OWM_INVALID_CITY_URL)
        assert data
        assert data.has("cod") && data.has("message")
    }

    // Тесты для wunderground.com

    @Test
    void testGetAnswerWU() throws Exception {
        when(connection.getInputStream(any(URLConnection.class)))
                .thenReturn(getInputStream(WU_VALID_JSON))
        JSONObject data = getAnswerAsJSON(ConstForTest.WU_VALID_URL)
        assert data
        assert data.has("current_observation")
    }

    @Test
    public void testInvalidCityRequestWU() throws Exception {
        when(connection.getInputStream(any(URLConnection.class)))
                .thenReturn(getInputStream(WU_INVALID_CITY_JSON))
        JSONObject data = getAnswerAsJSON(ConstForTest.WU_INVALID_CITY_URL)
        assert data
        assert data.getJSONObject("response").has("error")
        assert data.getJSONObject("response").getJSONObject("error").has("description")
    }

    @Test
    // Возможно лишний тест, т.к. повторяет предыдущий
    void testInvalidAppIDRequestWU() throws Exception {
        when(connection.getInputStream(any(URLConnection.class)))
                .thenReturn(getInputStream(WU_INVALID_APPID_JSON))
        JSONObject data = getAnswerAsJSON(ConstForTest.WU_INVALID_APPID_URL)
        assert data
        assert data.getJSONObject("response").has("error")
        assert data.getJSONObject("response").getJSONObject("error").has("description")
    }

}
