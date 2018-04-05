package com.kanaa.cwapi.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Site extends DataObject {

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

  public Weather getWeather(String cityName) throws UserException {
    WebGateway webGateway = ctx.getWebConnection();
    try {
      String answer = webGateway.getAnswer(getUrlCity(cityName));
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

  // Взаимодействие с БД
  private static final String FIND_STATEMENT = "select id, name, short_name, url, appid, weather_request, processor" +
      " from Site where id=?";
  private static final String UPDATE_STATEMENT = "update Site set name=?, short_name=?, url=?, appid=?," +
      " weather_request=?, processor=? where id=?";
  private static final String INSERT_STATEMENT = "insert into Site(id, name, short_name, url, appid," +
      "weather_request, processor) values (?, ?, ?, ?, ?, ?, ?)";
  private static final String NEXT_VAL_STATEMENT = "select nextVal(site_id_seq)";

  public void find(Long id) throws SQLException, UserException {
    /*
    * ToDo: сделать чтение из кэша контекста
    * Site result = (Site) Registry.getSite(id);
    * if (result != null) return result;
    */
    Connection conn = ctx.getDbConnection();
    try (PreparedStatement stmnt = conn.prepareStatement(FIND_STATEMENT)) {
      stmnt.setLong(1, id);
      ResultSet rs = stmnt.executeQuery();
      if (rs.next()) {
        load(rs);
      } else {
        throw new UserException(String.format("Сайт с id=%s не найден.", id));
      }
    }
  }

  public void load(ResultSet rs) throws SQLException {
    Long id = rs.getLong("id");
    /* Site result = (Site) Registry.getSite(id);
    * if (result != null) {
    * this = result;
    * return;
    * };
    */
    setId(id);
    setName(rs.getString("name"));
    setShortName(rs.getString("short_name"));
    setUrl(rs.getString("url"));
    setAppId(rs.getString("appid"));
    setWeatherRequest(rs.getString("weather_request"));
    setProcessorClassName(rs.getString("processor"));
    //Registry.addSite(this);
  }

  public void update() throws SQLException {
    try (PreparedStatement stmnt = ctx.getDbConnection().prepareStatement(UPDATE_STATEMENT)) {
      int idx = 0;
      stmnt.setString(++idx, getName());
      stmnt.setString(++idx, getShortName());
      stmnt.setString(++idx, getUrl());
      stmnt.setString(++idx, getAppId());
      stmnt.setString(++idx, getWeatherRequest());
      stmnt.setString(++idx, getProcessorClassName());
      stmnt.setLong(++idx, getId());
      stmnt.executeUpdate();
    }
  }

  public Long insert() throws SQLException {
    try (PreparedStatement stmnt = ctx.getDbConnection().prepareStatement(INSERT_STATEMENT)) {
      int idx = 0;
      setId(findNextDatabaseId());
      stmnt.setLong(++idx, getId());
      stmnt.setString(++idx, getName());
      stmnt.setString(++idx, getShortName());
      stmnt.setString(++idx, getUrl());
      stmnt.setString(++idx, getAppId());
      stmnt.setString(++idx, getWeatherRequest());
      stmnt.setString(++idx, getProcessorClassName());
      stmnt.execute();
      //Registry.addSite(this);
      return getId();
    }
  }

  public Long findNextDatabaseId() throws SQLException {
    try (PreparedStatement stmnt = ctx.getDbConnection().prepareStatement(NEXT_VAL_STATEMENT)) {
      ResultSet rs = stmnt.executeQuery();
      rs.next();
      return rs.getLong(1);
    }
  }
}