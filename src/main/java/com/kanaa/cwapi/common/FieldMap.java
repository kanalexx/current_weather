package com.kanaa.cwapi.common;

import java.lang.reflect.Field;

public class FieldMap {

  private final String columnName;
  private final Field field;

  public FieldMap(String columnName, Field field) {
    this.columnName = columnName;
    this.field = field;
  }

  public String getColumnName() {
    return columnName;
  }

  public Field getField() {
    return field;
  }
}
