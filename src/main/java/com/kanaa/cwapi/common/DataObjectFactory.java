package com.kanaa.cwapi.common;

/**
 * Интерфейс фабрики объектов данных
 */
public interface DataObjectFactory {

  /**
   * Создать новый объект указанного класса
   */
  <T extends DataObject> T newDataObject(Class<T> clazz);

}
