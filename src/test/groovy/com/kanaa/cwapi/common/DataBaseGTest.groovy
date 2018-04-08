package com.kanaa.cwapi.common

import org.junit.Test

import java.sql.Connection
import java.sql.ResultSet

/**
 *
 * @author Alexander Kanunnikov
 */
class DataBaseGTest {

  private Context ctx = new Context()

  @Test
  void testConnection() throws Exception {
    Connection conn = ctx.dbConnection
    def statement = conn.createStatement()
    ResultSet rs = statement.executeQuery('SELECT version()')
    assert rs.next()
    conn.close()
  }

  @Test
  void testFindSite() throws Exception {
    def site = new Site(ctx)
    site.find(1)
    assert site.getShortName() == "OWM"
  }
}
