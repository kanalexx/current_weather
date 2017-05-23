package com.kanaa;

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
        Map<String, Site> sites = new HashMap<>();
        sites.put("OWM", new OWMSite(conn));
        sites.put("WU", new WUSite(conn));
        StationManager stationManager = new StationManager(sites);
        String siteName;
        String cityName;
        do {
            System.out.println("Введите сайт (OWM, WU):");
            siteName = br.readLine();
            System.out.println("Введите название города:");
            cityName = br.readLine();
            if (!isExit(cityName)) {
                try {
                    Station station = stationManager.getStationByCityName(cityName, siteName);
                    if (station.update()) {
                        System.out.println("Источник: " + station.getSiteName());
                        System.out.println("Температура в городе " + station.getCityName() + ": " + station.getTemp() + "\u00BAC");
                    } else {
                        System.out.println(station.getErrorMessage());
                    }
                } catch (Exception e) {
                    log.error("Ошибка: ",e);
                }
                System.out.println("Для выхода введите \"Exit\".");
            }
        } while(!isExit(cityName));
    }

    private static boolean isExit(String inString) {
        return inString.equalsIgnoreCase("exit");
    }
}
