package com.kanaa.cwapi.common;

import org.apache.log4j.Logger;

/**
 * Объект данных
 * Объект данных может сохранять/загружать свое состояние из разного рода хранилища данных.
 * Обмен с конкретным типом хранилища производится посредством
 */
public abstract class DataObject {

  private static final String STRING_FMT = "[%s %s]";

  protected static final Logger log = Logger.getLogger(DataObject.class);
  protected Context ctx;
  protected Long id;
  protected DataMapper dataMapper;

  public DataObject(Context ctx) throws UserException {
    this.ctx = ctx;
    dataMapper = getDataMapper();
  }

  /**
   * Получить преобразователь данных
   */
  protected abstract DataMapper getDataMapper() throws UserException;

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

  public void save() {
    if (id != null) {
      dataMapper.update(this);
    } else {
      dataMapper.insert(this);
    }
  }

  public void load(Long id) {
    dataMapper.load(this, id);
  }
}
