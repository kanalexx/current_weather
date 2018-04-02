package com.kanaa.cwapi.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Context {
  private static final Logger log = Logger.getLogger(Context.class);
  private WebGateway webConnection;
  private Connection dbConnection;

  public Context() {
    webConnection = new WebGateway();
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      log.error("Ошибка регистрации драйвера БД.", e);
    }
    try {
      dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cw_db", "postgres", "password");
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
}