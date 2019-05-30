package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 29.05.2019
 */
public class SAXPars extends DefaultHandler {

    int result = 0;

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("entry")) {
            String field = attributes.getValue("field");
            result = result + Integer.parseInt(field);
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Result: " + result);
    }
}
