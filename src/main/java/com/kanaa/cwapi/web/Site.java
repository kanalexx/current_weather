package com.kanaa.cwapi.web;

import com.kanaa.cwapi.common.Context;
import com.kanaa.cwapi.common.DataObject;
import com.kanaa.cwapi.common.UserException;
import com.kanaa.cwapi.common.Weather;
import com.kanaa.cwapi.common.WebSite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;

public class Site extends DataObject implements WebSite {

  public static final String APPID_URL_PART = "{APPID}";
  public static final String CITYNAME_URL_PART = "{CITYNAME}";

  private String name;
  private String shortName;
  private String url;
  private String appId;
  private String weatherRequest;
  private String processorClassName;

  public Site(Context ctx) {
    super(ctx);
  }

  @Override
  public int hashCode() {
    return 35 + url.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String getFieldsStringValues() {
    return super.getFieldsStringValues() +
        ",url=" + (url != null ? url : "");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getWeatherRequest() {
    return weatherRequest;
  }

  public void setWeatherRequest(String weatherRequest) {
    this.weatherRequest = weatherRequest;
  }

  public String getProcessorClassName() {
    return processorClassName;
  }

  public void setProcessorClassName(String processorClassName) {
    this.processorClassName = processorClassName;
  }

  @Override
  public Weather getWeather(String cityName) throws IOException, UserException {
    String answer = ctx.getAnswer(getUrlCity(cityName));
    try {
      Processor processor = (Processor) Class.forName(getProcessorClassName()).getConstructor().newInstance();
      return processor.process(answer);
    } catch (Exception ex) {
      log.error(ex);
      throw new UserException(ex);
    }
  }

  public String getUrlCity(String cityName) {
    return getWeatherRequest()
        .replace(CITYNAME_URL_PART, cityName)
        .replace(APPID_URL_PART, getAppId());
  }
}
