package com.kanaa.cwapi.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataMap {

  private Class dataObjectClass;
  private List<FieldMap> fields = new ArrayList<>();

  public DataMap(Class dataObjectClass) {
    this.dataObjectClass = dataObjectClass;
  }

  public void addField(String columnName, String fieldName) throws UserException {
    try {
      Field field = dataObjectClass.getDeclaredField(fieldName);
      field.setAccessible(true);
      fields.add(createFieldMap(columnName, field));
    } catch (Exception e) {
      throw new UserException("unable to set up field: " + fieldName, e);
    }
  }

  private FieldMap createFieldMap(String columnName, Field field) {
    return new FieldMap(columnName, field);
  }
}
