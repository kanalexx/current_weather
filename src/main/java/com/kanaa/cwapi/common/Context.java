package com.kanaa.cwapi.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.kanaa.cwapi.common.dofactory.DataObjectFactoryImpl;
import com.kanaa.cwapi.web.WebGateway;
import org.apache.log4j.Logger;

public class Context {
  private static final Logger log = Logger.getLogger(Context.class);
  private WebGateway webConnection;
  private DataObjectFactory dataObjectFactory;

  public Context() throws IOException {
    webConnection = new WebGateway();
    dataObjectFactory = new DataObjectFactoryImpl(this);
    Properties properties;
    try {
      properties = getProperties();
    } catch (IOException e) {
      log.error("Ошибка чтения файла настроек.", e);
      throw e;
    }
  }

  public WebGateway getWebConnection() {
    return webConnection;
  }

  public Properties getProperties() throws IOException {
    Properties props = new Properties();
    try (InputStream in = new FileInputStream("server.properties")) {
      props.load(in);
    }
    return props;
  }

  public String getAnswer(String request) throws IOException {
    return getWebConnection().getAnswer(request);
  }

  public DataObjectFactory getDataObjectFactory() {
    return dataObjectFactory;
  }
}