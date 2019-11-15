package com.kanaa.cwapi.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.kanaa.cwapi.common.Context;
import com.kanaa.cwapi.common.Dictionary;
import com.kanaa.cwapi.common.UserException;
import com.kanaa.cwapi.common.XmlParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Справочник сайтов
 */
public class SiteDictionary extends Dictionary<Site> {

  private Map<String, Site> mapByShortName = new HashMap<>();

  @Override
  public void load(Context ctx) throws UserException {
    loadFromXml(ctx);
  }

  private void loadFromXml(Context ctx) throws UserException {
    XmlParser xml = new XmlParser();
    try {
      Element rootEl = xml.createDocument(new File("Resources\\Dictionaries\\Site.xml"));
      for (Node node = rootEl.getFirstChild(); node != null; node = node.getNextSibling()) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          //
          Element siteEl = (Element) node;
          Site site = ctx.getDataObjectFactory().newDataObject(Site.class);
          //
          site.setId(Long.valueOf(siteEl.getAttribute("ID")));
          site.setName(siteEl.getAttribute("NAME"));
          site.setShortName(siteEl.getAttribute("SHORT_NAME"));
          site.setUrl(siteEl.getAttribute("URL"));
          site.setAppId(siteEl.getAttribute("APPID"));
          site.setWeatherRequest(siteEl.getAttribute("WEATHER_REQUEST"));
          site.setProcessorClassName(siteEl.getAttribute("PROCESSOR"));
          //
          mapById.put(site.getId(), site);
          mapByShortName.put(site.getShortName(), site);
        }
      }
    } catch (IOException | SAXException e) {
      throw new UserException("Ошибка загрузки справочника сайтов", e);
    }
  }

  public Site getByShortName(String shortName) {
    return mapByShortName.get(shortName);
  }
}