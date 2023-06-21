package b2bbebolino;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ShopProducts {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            File file = new File("bebolino-ltd.xml");
            //File file = new File("b2bbolinoPart.xml");
            SAXParser saxParser = factory.newSAXParser();
            ProductHandler handler = new ProductHandler();
            saxParser.parse(file, handler);
            ExcelReadingWriting excelReadingWriting = new ExcelReadingWriting();
            try {
                excelReadingWriting.writeExcel(handler.getProducts());
            } catch (IOException e) {
                e.printStackTrace();
            }
            excelReadingWriting.readExcel();
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
