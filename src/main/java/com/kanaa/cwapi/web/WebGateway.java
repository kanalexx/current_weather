package com.kanaa.cwapi.web;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class WebGateway {

  public String getAnswer(String request) throws IOException {
    if (!request.isEmpty()) {
      URL url = new URL(request);
      URLConnection connection = url.openConnection();
      try (InputStream is = getInputStream(connection)) {
        final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        return readFromBuffer(rd);
      }
    }
    return "";
  }

  InputStream getInputStream(URLConnection connection) throws IOException {
    InputStream is = null;
    try {
      is = connection.getInputStream();
    } catch (IOException ioe) {
      if (connection instanceof HttpURLConnection) {
        HttpURLConnection httpConn = (HttpURLConnection) connection;
        int statusCode = httpConn.getResponseCode();
        if (statusCode != 200) {
          is = httpConn.getErrorStream();
        } else {
          throw ioe;
        }
      }
    }
    return is;
  }

  private static String readFromBuffer(final Reader rd) throws IOException {
    final StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
}
