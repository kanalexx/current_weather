package com.kanaa.cwapi.common.dofactory;

import com.kanaa.cwapi.common.Context;
import com.kanaa.cwapi.common.DataObject;
import com.kanaa.cwapi.common.DataObjectFactory;
import com.kanaa.cwapi.common.SimpleDataMapper;
import com.kanaa.cwapi.common.UserException;

/**
 * Реализация фабрики объектов данных
 */
public class DataObjectFactoryImpl implements DataObjectFactory {

  private Context ctx;

  public DataObjectFactoryImpl(Context ctx) {
    this.ctx = ctx;
  }

  public <T extends DataObject> T newDataObject(Class<T> clazz) {
    T dataObject;
    try {
      dataObject = clazz.newInstance();
      dataObject.init(ctx, new SimpleDataMapper(dataObject));
    } catch (InstantiationException | IllegalAccessException | UserException e) {
      throw new IllegalStateException(e);
    }
    return dataObject;
  }
}
