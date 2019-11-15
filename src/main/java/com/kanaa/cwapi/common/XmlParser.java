package com.kanaa.cwapi.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParser {

  public Element createDocument(File file) throws IOException, SAXException {
    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
      return createDocument(in);
    }
  }

  public Element createDocument(InputStream in) throws IOException, SAXException {
    DOMParser parser = new DOMParser();
    parser.parse(new InputSource(in));
    return parser.getDocument().getDocumentElement();
  }

}