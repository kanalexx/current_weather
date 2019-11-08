package com.kanaa.cwapi.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataMap {

  private Class dataObjectClass;
  /** Связь "имя поля" - "поле объекта" */
  private HashMap<String, Field> classFieldMap = new HashMap<>();
  /** Связь "имя колонки" - "поле объекта" */
  private HashMap<String, FieldMap> fields = new HashMap<>();

  public DataMap(Class dataObjectClass) {
    this.dataObjectClass = dataObjectClass;
    // сканирование всех полей по всей иерархии наследования
    for(Class aClass = dataObjectClass; aClass != null; aClass = aClass.getSuperclass()) {
      for(Field field : aClass.getDeclaredFields()) {
        classFieldMap.put(field.getName(), field);
      }
    }
  }

  public void addField(String columnName, String fieldName) throws UserException {
    try {
      Field field = classFieldMap.get(fieldName);
      field.setAccessible(true);
      fields.put(columnName, createFieldMap(columnName, field));
    } catch (Exception e) {
      throw new UserException("unable to set up field: " + fieldName, e);
    }
  }

  private FieldMap createFieldMap(String columnName, Field field) {
    return new FieldMap(columnName, field);
  }

  public void setField(DataObject dataObject, String columnName, Object columnValue) throws UserException {
    FieldMap fieldMap = fields.get(columnName);
    Field field = fieldMap.getField();
    try {
      Object value = field.getType().getConstructor(columnValue.getClass()).newInstance(columnValue);
      field.set(dataObject, value);
    } catch (Exception e) {
      throw new UserException("Error in setting " + fieldMap.getField().getName(), e);
    }
  }
}
