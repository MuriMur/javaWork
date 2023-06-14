package b2bbebolino;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

public class SAXxmlParser {
    public static void main(String[] args) {
        try {
            SAXParserFactory  factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler(){
                private static final String PRODUCT = "product", ID = "id", BARCODE = "barcode", PRODUCT_CODE = "product_code", TITLE = "title",
                        SHORT_DESCRIPTION = "short_description", DIV = "div", BR = "br",
                        DESCRIPTION = "description", SECTION = "section", STRONG = "strong", P ="p",
                        URL = "url", CATEGORY = "category", SUB_CATEGORY = "sub_category", MANUFACTURER = "manufacturer", PRICE = "price",
                        ORIGINAL_PRICE = "original_price", STOCK_STATUS = "stack_status", IMAGE = "image", META_TITLE = "meta_title", META_DESCRIPTION = "meta_description";
                boolean id = false, productCode = false, barCode = false, title = false, shortDescription = false, div = false, br = false, description = false, url = false,
                category = false, subCategory = false, manufacturer = false, price = false, originalPrice = false,
                Product product;
                List<Product> products;
                List<String> images;
                StringBuilder elementValue;


                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
                    System.out.println("Start element: " + qName);
                    switch (qName){
                        case PRODUCT:
                            product = new Product();
                            products.add(product);
                            break;
                        case ID:
                            id = true;
                            break;
                        case PRODUCT_CODE:
                            productCode = true;
                            break;
                        case BARCODE:
                            barCode = true;
                            break;
                        case TITLE:
                            title = true;
                            break;
                        case ID:
                            id = true;
                            break;
                        case ID:
                            id = true;
                            break;
                        case ID:
                            id = true;
                            break;
                        case ID:
                            id = true;
                            break;
                        case ID:
                            id = true;
                            break;
                        default:
                            break;
                    }


                }

                public void endElement(String uri, String localName, String qName) throws SAXException{
                    switch (qName){
                        case ID:
                            product.setId(elementValue.toString());
                            break;
                        case
                    }
                }

                public void characters(char[] ch, int start, int length) throws SAXException{
                    if (elementValue == null) {
                        elementValue = new StringBuilder();
                    } else {
                        elementValue.append(ch, start, length);
                    }
                }
            };
            saxParser.parse("b2bbolinoPart.xml", handler);
        }
        catch (Exception ex){

        }
    }
}
