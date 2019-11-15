package com.kanaa.cwapi.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный словарь
 */
public abstract class Dictionary<T> {

  protected Map<Long, T> mapById = new HashMap<>();

  public abstract void load(Context ctx) throws UserException;

  public T getById(Long id) {
    return mapById.get(id);
  }
}