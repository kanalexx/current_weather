package com.kanaa.cwapi;

import com.kanaa.cwapi.common.*;
import com.kanaa.cwapi.owm.OWMSiteGateway;
import com.kanaa.cwapi.wu.WUSiteGateway;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App
{
    private static final Logger log = Logger.getLogger(App.class);

    public static void main( String[] args ) throws IOException {
        String nameFile = "log4j.properties";
        PropertyConfigurator.configure(nameFile);
        Connection conn = new Connection();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, SiteGateway> sites = new HashMap<>();
        sites.put("OWM", new OWMSiteGateway(conn));
        sites.put("WU", new WUSiteGateway(conn));
        StationManager stationManager = new StationManager(sites);
        String siteName;
        String cityName;
        do {
            log.info("Введите сайт (OWM, WU):");
            siteName = br.readLine();
            log.info("Введите название города:");
            cityName = br.readLine();
            if (!isExit(cityName)) {
                try {
                    Station station = stationManager.getStationByCityName(cityName, siteName);
                    if (station.update()) {
                        Weather weather = station.getWeather();
                        log.info("Источник: " + station.getSiteName());
                        log.info("Температура в городе " + station.getCityName() + ": " + weather.getTemp() + "\u00BAC");
                    } else {
                        log.info(station.getErrorMessage());
                    }
                } catch (Exception e) {
                    log.error("Ошибка: ",e);
                }
                log.info("Для выхода введите \"Exit\".");
            }
        } while(!isExit(cityName));
    }

    private static boolean isExit(String inString) {
        return inString.equalsIgnoreCase("exit");
    }
}
