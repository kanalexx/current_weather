package com.kanaa.cwapi.common;

public class Site {
  private String name;
  private String url;
  private String appId;
  private String weatherRequest;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

}