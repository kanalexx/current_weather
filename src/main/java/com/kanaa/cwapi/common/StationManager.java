package com.kanaa.cwapi.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StationManager {

  private class ResourceKey {
    private String city;
    private WebSite site;

    public ResourceKey(String city, WebSite site) {
      this.city = city;
      this.site = site;
    }

    @Override
    public int hashCode() {
      return 12 * city.hashCode() + site.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null)
        return false;
      if (this == obj)
        return true;
      if (getClass() == obj.getClass()) {
        ResourceKey rk = (ResourceKey) obj;
        return this.site.equals(rk.site)
            && this.city.equals(rk.city);
      }
      return false;
    }
  }

  /**
   * Статичный кэш станций. Общий для всех экземпляров класса.
   */
  private static Map<ResourceKey, Station> stationCache = new HashMap<>();
  private Map<String, WebSite> sites;

  public StationManager(Map<String, WebSite> sites) {
    this.sites = sites;
  }

  public Station getStationByCityName(String cityName) throws UserException {
    return getStationByCityName(cityName, "");
  }

  public Station getStationByCityName(String cityName, String siteName) throws UserException {
    WebSite site = getSite(siteName);
    ResourceKey resourceKey = new ResourceKey(cityName, site);
    Station station = stationCache.get(resourceKey);
    if (station == null) {
      station = new Station(cityName, site);
      stationCache.put(resourceKey, station);
    }
    return station;
  }

  private WebSite getSite(String siteName) throws UserException {
    if (sites.isEmpty()) {
      throw new UserException("Список сайтов пуст.");
    }
    WebSite site;
    if (siteName.isEmpty()) {
      site = new ArrayList<>(sites.values()).get(0);
      return site;
    }
    site = sites.get(siteName);
    if (site == null) {
      throw new UserException("Неизвестный сайт.");
    }
    return site;
  }

}
