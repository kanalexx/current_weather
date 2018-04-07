package com.kanaa.cwapi.common;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Site {
    private static final Logger log = Logger.getLogger(Site.class);

    public static final String APPID_URL_PART = "{APPID}";
    public static final String CITYNAME_URL_PART = "{CITYNAME}";

    private Context ctx;
    private String name;
    private String url;
    private String appId;
    private String weatherRequest;
    private String processorClassName;

    public Site(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int hashCode() {
        return 35 + url.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        return (getClass() == obj.getClass());
    }

    @Override
    public String toString() {
        return url;
    }

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

  public Weather getWeather(String cityName) throws IOException, UserException {
    String answer = ctx.getAnswer(getUrlCity(cityName));
    try {
      Processor processor = (Processor) Class.forName(this.processorClassName).getConstructor().newInstance();
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
