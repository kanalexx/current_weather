package com.kanaa.cwapi.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Context {
  private static final Logger log = Logger.getLogger(Context.class);
  private WebGateway webConnection;
  private Connection dbConnection;

  public Context() throws IOException {
    webConnection = new WebGateway();
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      log.error("Ошибка регистрации драйвера БД.", e);
    }
    Properties properties;
    try {
      properties = getProperties();
    } catch (IOException e) {
      log.error("Ошибка чтения файла настроек.", e);
      throw e;
    }
    String url = properties.getProperty("url");
    String username = properties.getProperty("username");
    String password = properties.getProperty("password");
    try {
      dbConnection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      log.error("Ошибка создания подключения к БД.", e);
    }
  }

  public WebGateway getWebConnection() {
    return webConnection;
  }

  public Connection getDbConnection() {
    return dbConnection;
  }

  public Properties getProperties() throws IOException {
    Properties props = new Properties();
    try (InputStream in = Files.newInputStream(Paths.get("server.properties")))
    {
      props.load(in);
    }
    return props;
  }
}