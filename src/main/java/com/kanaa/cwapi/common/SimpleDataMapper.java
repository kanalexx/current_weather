package com.kanaa.cwapi.common;

/**
 * Заглушка для преобразователя данных
 */
public class SimpleDataMapper implements DataMapper {

  protected DataObject dataObject;
  protected DataMap dataMap;

  public SimpleDataMapper(DataObject dataObject) throws UserException {
    this.dataObject = dataObject;
    dataMap = new DataMap(dataObject.getClass());
    fillDataMap(dataMap);
  }

  @Override
  public void load(DataObject dataObject, Long id) {

  }

  @Override
  public void insert(DataObject dataObject) {

  }

  @Override
  public void update(DataObject dataObject) {

  }

  /**
   * Заполнить соответсвие полей объекта и полей сущности в хранилище
   * @param dataMap
   */
  protected void fillDataMap(DataMap dataMap) throws UserException {
    dataMap.addField("ID", "id");
  }

}