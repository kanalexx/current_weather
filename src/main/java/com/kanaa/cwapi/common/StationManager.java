package com.kanaa.cwapi.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StationManager {

  private class ResourceKey {
    private String city;
    private SiteGateway siteGateway;

    public ResourceKey(String city, SiteGateway siteGateway) {
      this.city = city;
      this.siteGateway = siteGateway;
    }

    @Override
    public int hashCode() {
      return 12 * city.hashCode() + siteGateway.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null)
        return false;
      if (this == obj)
        return true;
      if (getClass() == obj.getClass()) {
        ResourceKey rk = (ResourceKey) obj;
        return this.siteGateway.equals(rk.siteGateway)
            && this.city.equals(rk.city);
      }
      return false;
    }
  }

  /**
   * Статичный кэш станций. Общий для всех экземпляров класса.
   */
  private static Map<ResourceKey, Station> stationCache = new HashMap<>();
  private Map<String, SiteGateway> sites;

  public StationManager(Map<String, SiteGateway> sites) {
    this.sites = sites;
  }

  public Station getStationByCityName(String cityName) throws UserException {
    return getStationByCityName(cityName, "");
  }

  public Station getStationByCityName(String cityName, String siteName) throws UserException {
    SiteGateway siteGateway = getSite(siteName);
    ResourceKey resourceKey = new ResourceKey(cityName, siteGateway);
    Station station = stationCache.get(resourceKey);
    if (station == null) {
      station = new Station(cityName, siteGateway);
      stationCache.put(resourceKey, station);
    }
    return station;
  }

  private SiteGateway getSite(String siteName) throws UserException {
    if (sites.isEmpty()) {
      throw new UserException("Список сайтов пуст.");
    }
    SiteGateway siteGateway;
    if (siteName.isEmpty()) {
      siteGateway = new ArrayList<>(sites.values()).get(0);
      return siteGateway;
    }
    siteGateway = sites.get(siteName);
    if (siteGateway == null) {
      throw new UserException("Неизвестный сайт.");
    }
    return siteGateway;
  }

}
