package com.kanaa.cwapi.common;

import org.apache.log4j.Logger;

public class DataObject {

  protected static final Logger log = Logger.getLogger(DataObject.class);
  protected Context ctx;
  protected Long id;

  public DataObject(Context ctx) {
    this.ctx = ctx;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
