package com.kanaa.cwapi.common

import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*
import static org.mockito.Mockito.mock

/**
 * Тест абстрактного справочника
 */
class DictionaryTest extends MyTest {

  private MyDictionary dict

  class MyDictionary extends Dictionary<DataObject> {
    @Override
    void load(Context con) {
      mapById << [
          1L : mock(DataObject.class),
          2L : mock(DataObject.class)
      ]
    }
  }

  @Before
  void setUp() throws Exception {
    dict = new MyDictionary()
    dict.load()
  }

  @Test
  void getById() {
    DataObject obj1 = dict.getById(1L)
    assertNotNull obj1

    DataObject obj3 = dict.getById(3L)
    assertNull obj3
  }
}