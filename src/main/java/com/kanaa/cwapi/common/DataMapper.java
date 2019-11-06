package com.kanaa.cwapi.common;

/**
 * Преобразователь данных
 * Осуществляет сохранение/загрузку объектов данных из хранилища
 */
public interface DataMapper {

  /**
   * Загрузить объект по идентификатору
   */
  void load(DataObject dataObject, Long id);

  /**
   * Сохранить новый объект
   */
  void insert(DataObject dataObject);

  /**
   * Обновить объект
   */
  void update(DataObject dataObject);

}
