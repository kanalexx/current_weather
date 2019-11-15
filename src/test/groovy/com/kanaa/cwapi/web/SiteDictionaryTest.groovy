package com.kanaa.cwapi.web

import com.kanaa.cwapi.common.Context
import com.kanaa.cwapi.common.DataObjectFactory
import com.kanaa.cwapi.common.MyTest
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertNotNull
import static org.mockito.Matchers.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

/**
 * Тест словаря сайтов
 */
class SiteDictionaryTest extends MyTest {

  private SiteDictionary dict
  private Context ctx
  private DataObjectFactory dataObjectFactory

  @Before
  void setUp() throws Exception {
    dict = new SiteDictionary()
    ctx = mock(Context)
    dataObjectFactory = mock(DataObjectFactory)
    //
    when(ctx.getDataObjectFactory()).thenReturn(dataObjectFactory)
    when(dataObjectFactory.newDataObject(any())).thenReturn(new Site())
  }

  @Test
  void loadTest() {
    dict.load(ctx)
    Site site = dict.getById(1)
    assertNotNull(site)
  }
}