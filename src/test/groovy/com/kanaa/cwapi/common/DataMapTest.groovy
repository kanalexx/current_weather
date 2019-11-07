package com.kanaa.cwapi.common

import org.junit.Before
import org.junit.Test

class DataMapTest extends MyTest {

  DataMap dataMap;

  @Before
  void setUp() {
    dataMap = new DataMap(Class.forName("com.kanaa.cwapi.common.DataObject"))
  }

  @Test
  void testAddField() {
    dataMap.addField("ID", "id");

  }
}
