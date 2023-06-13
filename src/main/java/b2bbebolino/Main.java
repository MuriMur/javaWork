package b2bbebolino;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        try {
            /*SAXParserFactory is  a factory API that
            enables applications to configure and obtain a
            SAX based parser to parse XML documents. */
            SAXParserFactory factory = SAXParserFactory.newInstance();
            File file = new File("b2bbolinoPart.xml");
            // Creating a new instance of a SAXParser using
            // the currently configured factory parameters.
            SAXParser saxParser = factory.newSAXParser();

            // DefaultHandler is Default base class for SAX2
            // event handlers.
            DefaultHandler handler = new DefaultHandler() {
                boolean id = false;
                boolean productCode = false;
                boolean barCode = false;
                boolean title = false;
                boolean shortDescription = false;
                boolean description = false;
                boolean category = false;
                boolean subCategory = false;
                boolean manufacturer = false;
                boolean price = false;
                boolean originalPrice = false;
                boolean stockStatus = false;


                // Receive notification of the start of an
                // element. parser starts parsing a element
                // inside the document
                public void startElement(String uri, String localName, String qName, Attributes attributes) {

                    if (qName.equalsIgnoreCase("Id")) {
                        id = true;
                    }
                    if (qName.equalsIgnoreCase("product_code")) {
                        productCode = true;
                    }
                    if (qName.equalsIgnoreCase("barcode")) {
                        barCode = true;
                    }
                    if (qName.equalsIgnoreCase("title")) {
                        title = true;
                    }
                    if (qName.equalsIgnoreCase("short_description")) {
                        shortDescription = true;
                    }
                    if (qName.equalsIgnoreCase("meta_description")){
                        description = true;
                    }
                    if (qName.equalsIgnoreCase("category")){
                        category = true;
                    }
                    if (qName.equalsIgnoreCase("sub_category")){
                        subCategory = true;
                    }
                    if (qName.equalsIgnoreCase("manufacturer")){
                        manufacturer = true;
                    }
                }

                // Receive notification of character data
                // inside an element, reads the text value of
                // the currently parsed element
                public void characters(char ch[], int start, int length) {
                    if (id) {
                        System.out.println(
                                "ID : "
                                        + new String(ch, start,
                                        length));
                        id = false;
                    }
                    if (productCode) {
                        System.out.println(
                                "product code: " + new String(ch, start, length));
                        productCode = false;
                    }
                    if (barCode) {
                        System.out.println("barcode: " + new String(ch, start, length));
                        barCode = false;
                    }
                    if (title) {
                        System.out.println("title: " + new String(ch, start, length));
                        title = false;
                    }
                    if (shortDescription) {
                        System.out.println("short description : " + new String(ch, start, length));
                        shortDescription = false;
                    }
                    if (description){
                        System.out.println("description : " + new String(ch, start, length));
                    }
                }
            };

            /*Parse the content described by the giving
             Uniform Resource
             Identifier (URI) as XML using the specified
             DefaultHandler. */
            saxParser.parse(file, handler);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        // TODO Install SAX in Project
        // TODO Read XML File from HDD
        // TODO Create Sax Factory
        // TODO Read XML Fields Using SAX
        // TODO Create a JAVA object representing the XML Content
        // TODO Read XML Fields and save them in JAVA Objects
        // TODO Read XML File from Web
    }
}
