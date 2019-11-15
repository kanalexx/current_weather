package com.kanaa.cwapi.common

import com.kanaa.cwapi.common.dofactory.DataObjectFactoryImpl
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertNotNull
import static org.mockito.Mockito.mock

/**
 * Тест фабрики объектов
 */
class DataObjectFactoryTest {

  private ctx

  @Before
  void setUp() {
    ctx = mock(Context)
  }

  @Test
  void test1() {
    DataObject obj = new DataObjectFactoryImpl(ctx).newDataObject(DataObject.class)
    assertNotNull(obj)
  }
}
