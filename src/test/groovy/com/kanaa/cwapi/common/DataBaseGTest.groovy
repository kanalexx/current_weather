package com.kanaa.cwapi.common

import org.junit.Test

import java.sql.Connection
import java.sql.ResultSet

/**
 *
 * @author Alexander Kanunnikov
 */
class DataBaseGTest {

  @Test
  public void testConnection() throws Exception {
    Context ctx = new Context()
    Connection conn = ctx.dbConnection
    def statement = conn.createStatement()
    ResultSet rs = statement.executeQuery('select version()')
    assert rs.next()
    conn.close()
  }
}
