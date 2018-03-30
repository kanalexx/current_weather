package com.kanaa.cwapi.common;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StationManagerTest {

  private StationManager manager;
  private Map<String, SiteGateway> sites;

  @Before
  public void setUp() throws Exception {
    sites = new HashMap<>();
    sites.put("OWM", mock(SiteGateway.class));
    sites.put("WU", mock(SiteGateway.class));
    manager = new StationManager(sites);
  }

  /**
   * Проверяет получение станции из кэша по имени города.
   * При одинаковом названии города получам один и тотже экземпляр.
   * При разных названиях города - разные станции.
   */
  @Test
  public void testGetStationByCityName() throws Exception {
    Station stationM1 = manager.getStationByCityName("Moscow");
    Station stationM2 = manager.getStationByCityName("Moscow");
    Station stationS = manager.getStationByCityName("Saratov");
    assertSame(stationM1, stationM2);
    assertNotSame(stationM1, stationS);
  }

  /**
   * Проверяет, что для разных сайтов с одним городом получаются разные станции.
   */
  @Test
  public void testGetStationWithSite() throws Exception {
    Station stationDef = manager.getStationByCityName("Moscow");
    Station stationAnotherSite = manager.getStationByCityName("Moscow", "WU");
    assertNotSame(stationDef, stationAnotherSite);
  }

  /**
   * Проверяет общий статичный кэш для всех экземпляров StationManager.
   */
  @Test
  public void testGetStationFromCache() throws Exception {
    StationManager manager2 = new StationManager(sites);
    Station owmStation1 = manager.getStationByCityName("Moscow");
    Station owmStation2 = manager2.getStationByCityName("Moscow");
    Station wuStation1 = manager.getStationByCityName("Moscow", "WU");
    Station wuStation2 = manager2.getStationByCityName("Moscow", "WU");

    assertSame(owmStation1, owmStation2);
    assertSame(wuStation1, wuStation2);
    assertNotSame(owmStation1, wuStation1);
  }

  /**
   * Ожидается исключение: Неизвестный сайт.
   */
  @Test(expected = UserException.class)
  public void testGetStationWithInvalidSite() throws Exception {
    manager.getStationByCityName("Moscow", "ERROR");
  }

  /**
   * Ожидается исключение: Список сайтов пуст.
   */
  @Test(expected = UserException.class)
  public void testGetStationEmptySiteList() throws Exception {
    Map<String, SiteGateway> sites = new HashMap<>();
    StationManager manager = new StationManager(sites);
    manager.getStationByCityName("Moscow");
  }


}
