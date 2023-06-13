package b2bbebolino;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXxmlParser {
    public static void main(String[] args) {
        try {
            SAXParserFactory  factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler(){
                boolean id = false, productCode = false, barCode = false, title = false;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
                    System.out.println("Start element: " + qName);
                    if (qName.equalsIgnoreCase("id")) id = true;
                    if (qName.equalsIgnoreCase("product_code")) productCode = true;
                    if (qName.equalsIgnoreCase("barcode")) barCode = true;

                }

                public void endElement(String uri, String localName, String qName) throws SAXException{
                    System.out.println("end element: " + qName);
                }

                public void characters(char[] chars, int start, int length) throws SAXException{
                    if (id){
                        System.out.println("id: " + new String(chars, start, length));
                        id = false;
                    }
                    if (productCode){
                        System.out.println("product code: " + new String(chars, start, length));
                        productCode = false;
                    }
                    if (barCode){
                        System.out.println("barcode: " + new String(chars, start, length));
                        barCode = false;
                    }
                }
            };
            saxParser.parse("b2bbolinoPart.xml", handler);
        }
        catch (Exception ex){

        }
    }
}
