package com.kanaa.cwapi.common

import org.junit.Before
import org.junit.Test

import static org.mockito.Mockito.mock

/**
 *
 * @author Alexander Kanunnikov
 */
class StationManagerGTest {
  private StationManager manager
  private Map sites

  @Before
  void setUp() throws Exception {
    sites = [OWM: mock(SiteGateway.class),
             WU : mock(SiteGateway.class)]
    manager = new StationManager(sites)
  }

  /**
   * Проверяет получение станции из кэша по имени города.
   * При одинаковом названии города получам один и тотже экземпляр.
   * При разных названиях города - разные станции.
   */
  @Test
  void testGetStationByCityName() {
    Station stationM1 = manager.getStationByCityName("Moscow")
    Station stationM2 = manager.getStationByCityName("Moscow")
    Station stationS = manager.getStationByCityName("Saratov")
    assert stationM1.is(stationM2)
    assert !stationM1.is(stationS)
  }

  /**
   * Проверяет, что для разных сайтов с одним городом получаются разные станции.
   */
  @Test
  void testGetStationWithSite() {
    Station stationDef = manager.getStationByCityName("Moscow")
    Station stationAnotherSite = manager.getStationByCityName("Moscow", "WU")
    assert !stationDef.is(stationAnotherSite)
  }

  /**
   * Проверяет общий статичный кэш для всех экземпляров StationManager.
   */
  @Test
  void testGetStationFromCache() {
    StationManager manager2 = new StationManager(sites)
    Station owmStation1 = manager.getStationByCityName("Moscow")
    Station owmStation2 = manager2.getStationByCityName("Moscow")
    Station wuStation1 = manager.getStationByCityName("Moscow", "WU")
    Station wuStation2 = manager2.getStationByCityName("Moscow", "WU")

    assert owmStation1.is(owmStation2)
    assert wuStation1.is(wuStation2)
    assert !owmStation1.is(wuStation1)
  }

  /**
   * Ожидается исключение: Неизвестный сайт.
   */
  @Test(expected = UserException.class)
  void testGetStationWithInvalidSite() {
    manager.getStationByCityName("Moscow", "ERROR")
  }

  /**
   * Ожидается исключение: Список сайтов пуст.
   */
  @Test(expected = UserException.class)
  void testGetStationEmptySiteList() {
    Map sites = [:]
    StationManager manager = new StationManager(sites)
    manager.getStationByCityName("Moscow")
  }

}
