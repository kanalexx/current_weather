package com.kanaa.cwapi.common;

import org.apache.log4j.Logger;

public class DataObject {

  private static final String STRING_FMT = "[%s %s]";

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

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (this == obj)
      return true;
    if (getClass() != obj.getClass())
      return false;
    return (id.equals(((DataObject) obj).id));
  }

  @Override
  public String toString() {
    return String.format(STRING_FMT, getClass().getSimpleName(), getFieldsStringValues());
  }

  protected String getFieldsStringValues() {
    return "id=" + id;
  }
}
