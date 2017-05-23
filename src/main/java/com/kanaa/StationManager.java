package com.kanaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StationManager {

    private class ResourceKey {
        private String city;
        private Site site;

        public ResourceKey(String city, Site site) {
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

    /** Статичный кэш станций. Общий для всех экземпляров класса. */
    private static Map<ResourceKey, Station> stationCache = new HashMap<>();
    private Map<String, Site> sites;

    public StationManager(Map<String, Site> sites) {
        this.sites = sites;
    }

    public Station getStationByCityName(String cityName) throws UserException {
		return getStationByCityName(cityName, "");
	}

	public Station getStationByCityName(String cityName, String siteName) throws UserException {
        Site site = getSite(siteName);
        ResourceKey resourceKey = new ResourceKey(cityName, site);
        Station station = stationCache.get(resourceKey);
		if (station == null) {
			station = new Station(cityName, site);
            stationCache.put(resourceKey, station);
		}
		return station;
	}

    private Site getSite(String siteName) throws UserException {
        if (sites.isEmpty()) {
            throw new UserException("Список сайтов пуст.");
        }
        Site site;
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
