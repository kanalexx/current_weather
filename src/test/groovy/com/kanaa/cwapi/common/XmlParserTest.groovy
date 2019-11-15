package com.kanaa.cwapi.common;

import org.junit.Before
import org.junit.Test
import org.w3c.dom.Element

import static org.junit.Assert.*;

/**
 * @author Alexander Kanunnikov
 */
class XmlParserTest extends MyTest {

  @Before
  void setUp() throws Exception {
  }

  @Test
  void parseTest() {
    File file = new File("Resources\\Dictionaries\\Site.xml")
    XmlParser parser = new XmlParser()
    Element element = parser.createDocument(file)
    assertNotNull(element)
  }
}