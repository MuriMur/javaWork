package b2bbebolino;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomParserDemo {

    public static void main(String[] args) {

        try {
            File inputFile = new File("bebolino-ltd.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("product");
            System.out.println("----------------------------");
            List<Product> products = new ArrayList<>();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Product product = new Product();
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                   // String subCategory = eElement.getElementsByTagName("sub_category").item(0).getTextContent();
                    product.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
                    product.setProductCode(eElement.getElementsByTagName("product_code").item(0).getTextContent());
                    product.setBarCode(eElement.getElementsByTagName("barcode").item(0).getTextContent());
                    product.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    product.setShortDescription(eElement.getElementsByTagName("short_description").item(0).getTextContent());
                    product.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    product.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
                    Category category = new Category(eElement.getElementsByTagName("category").item(0).getTextContent());
                    if (eElement.getElementsByTagName("sub_category").item(0) != null) {
                        Category subCategory = new Category(eElement.getElementsByTagName("sub_category").item(0).getTextContent(),category);
                        product.setCategory(subCategory);
                    }
                    product.setManufacturer(eElement.getElementsByTagName("manufacturer").item(0).getTextContent());
                    product.setPrice(BigDecimal.valueOf(Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent())));
                    product.setOriginalPrice(BigDecimal.valueOf(Double.parseDouble(eElement.getElementsByTagName("original_price").item(0).getTextContent())));
                    product.setStatus(eElement.getElementsByTagName("stock_status").item(0).getTextContent());

                    NodeList imageNodeList = eElement.getElementsByTagName("image");
                    List<String> images = new ArrayList<>();
                    for (int count = 0; count < imageNodeList.getLength(); count++) {
                        Node imageNode = imageNodeList.item(count);
                        if (imageNode.getNodeType() == imageNode.ELEMENT_NODE) {
                            Element image = (Element) imageNode;
                            if (image.getTextContent() != null && !image.getTextContent().equalsIgnoreCase("")) {
                                System.out.println("image : " + image.getTextContent());
                                images.add(image.getTextContent());
                            }
                        }
                    }
                    product.setImages(images);
                    product.setMetaTitle(eElement.getElementsByTagName("meta_title").item(0).getTextContent());
                    product.setMetaDescription(eElement.getElementsByTagName("meta_description").item(0).getTextContent());
                    NodeList variantNodeList = eElement.getElementsByTagName("variant");
                    List<Variant> variants = new ArrayList<>();
                    for (int i = 0; i < variantNodeList.getLength(); i++){
                        Node variantNode = variantNodeList.item(i);
                        Variant variantObj = new Variant();
                        if (variantNode.getNodeType() == variantNode.ELEMENT_NODE){
                            Element variant = (Element) variantNode;
                            if (variant.getElementsByTagName("id").item(0) != null) {
                                variantObj.setId(variant.getElementsByTagName("id").item(0).getTextContent());
                            }
                            if (variant.getElementsByTagName("key").item(0) != null) {
                                variantObj.setKey(variant.getElementsByTagName("key").item(0).getTextContent());
                            }
                            if (variant.getElementsByTagName("stock_status").item(0) != null) {
                                variantObj.setStockStatus(variant.getElementsByTagName("stock_status").item(0).getTextContent());
                            }
                            if (variant.getElementsByTagName("value").item(0) != null) {
                                variantObj.setValue(variant.getElementsByTagName("value").item(0).getTextContent());
                            }
                            if (variant.getElementsByTagName("price").item(0) != null) {
                                variantObj.setPrice(BigDecimal.valueOf(Double.parseDouble(variant.getElementsByTagName("id").item(0).getTextContent())));
                            }
                            variants.add(variantObj);
                        }
                    }
                    product.setVariants(variants);
                    products.add(product);
                }
                for (int j = 0; j < products.size(); j++){
                    System.out.println(products.get(j));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}