package com.kanaa.cwapi.common

import org.junit.Before
import org.junit.Test

import static org.mockito.Mockito.mock

class DataMapTest extends MyTest {

  class MockDataObject extends DataObject {

    BigDecimal amount = new BigDecimal(0);

    MockDataObject(Context ctx) throws UserException {
      super(ctx)
    }

    @Override
    protected DataMapper getDataMapper() throws UserException {
      return mock(DataMapper)
    }

    BigDecimal getAmount() {
      return amount;
    }
  }

  static storage = [
      "ID" : "555",
      "AMOUNT" : "123.45"
  ];

  DataMap dataMap;
  MockDataObject dataObject;

  @Before
  void setUp() {
    dataObject = new MockDataObject(mock(Context.class))
    dataMap = new DataMap(dataObject.getClass())
  }

  @Test
  void testAddField() {
    dataMap.addField("ID", "id")
    dataMap.addField("AMOUNT", "amount")
    storage.each {it ->
      dataMap.setField(dataObject, it.key, it.value)
    }
    assert dataObject.getId()
    assert 555L == dataObject.getId()
    assert 123.45 == dataObject.getAmount()
  }
}
