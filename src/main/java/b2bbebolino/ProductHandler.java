package b2bbebolino;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductHandler extends DefaultHandler {
    public static final String PRODUCT = "product";
    public static final String ID = "id";
    public static final String PRODUCT_CODE = "product_code";
    public static final String BARCODE = "barcode";
    public static final String TITLE = "title";
    public static final String SHORT_DESCRIPTION = "short_description";
    public static final String DESCRIPTION = "description";
    public static final String URL = "url";
    public static final String CATEGORY = "category";
    public static final String SUB_CATEGORY = "sub_category";
    public static final String MANUFACTURER = "manufacturer";
    public static final String PRICE = "price";
    public static final String ORIGINAL_PRICE = "original_price";
    public static final String STOCK_STATUS = "stock_status";
    public static final String IMAGES = "images";
    public static final String IMAGE = "image";
    public static final String META_TITLE = "meta_title";
    public static final String META_DESCRIPTION = "meta_description";
    public static final String VARIANT = "variant";
    public static final String KEY = "key";
    public static final String VALUE = "value";
    public static final String OPTIONS = "options";
    public static final String VARIANTS = "variants";
    public static final String CC_VARIANTS = "cc_variants";
    public static final boolean DEBUG = false;
    boolean id;
    boolean productCode;
    boolean barCode;
    boolean title;
    boolean shortDescription;
    boolean description;
    boolean category;
    boolean subCategory;
    boolean manufacturer;
    boolean price;
    boolean originalPrice;
    boolean stockStatus;
    boolean variantsFlag;
    boolean optionsFlag;
    boolean ccVariantFlag;
    boolean imagesFlag;
    List<Product> products;
    Product currentProduct;
    StringBuilder elementValue;
    Category categoryObj;
    Category subCategoryObj;
    List<String> images;
    List<Variant> variants;
    Variant variant;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (optionsFlag || ccVariantFlag) {
            return;
        }
        elementValue = new StringBuilder();
        switch (qName) {
            case PRODUCT:
                currentProduct = new Product();
                break;
            case VARIANTS:
                variantsFlag = true;
                variants = new ArrayList<>();
                currentProduct.setVariants(variants);
                break;
            case VARIANT:
                variant = new Variant();
                break;
            case IMAGES:
                imagesFlag = true;
                images = new ArrayList<>();
                break;
            case IMAGE:
                //System.out.println("startImage ");
                break;
            case OPTIONS:
                optionsFlag = true;
                break;
            case CC_VARIANTS:
                ccVariantFlag = true;
                break;
            default:
                break;
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        //System.out.println("-> " + new String(ch, start, length));
        if (optionsFlag || ccVariantFlag || elementValue == null) {
            return;
        }
        elementValue.append(ch, start, length);
        clearFlags();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!OPTIONS.equalsIgnoreCase(qName) && optionsFlag) {
            return;
        }
        if (!CC_VARIANTS.equalsIgnoreCase(qName) && ccVariantFlag) {
            return;
        }
        switch (qName) {
            case PRODUCT:
                products.add(currentProduct);
                System.out.println(currentProduct.toString());
                currentProduct = null;
                elementValue = null;
                break;
            case ID:
                if (variantsFlag) {
                    variant.setId(elementValue.toString());
                    if (DEBUG) {
                        System.out.println(variant.getId());
                    }
                    break;

                }
                currentProduct.setId(elementValue.toString());
                if (DEBUG) {
                    System.out.println("ID = " + currentProduct.getId());
                }
                break;
            case SHORT_DESCRIPTION:
                currentProduct.setShortDescription(elementValue.toString());
                if (DEBUG) {
                    System.out.println("short description = " + currentProduct.getShortDescription());
                }
                break;
            case BARCODE:
                if (variantsFlag) {
                    break;
                }
                currentProduct.setBarCode(elementValue.toString());
                if (DEBUG) {
                    System.out.println("BARCODE = " + currentProduct.getBarCode());
                }
                break;
            case PRODUCT_CODE:
                currentProduct.setProductCode(elementValue.toString());
                if (DEBUG) {
                    System.out.println("product code = " + currentProduct.getProductCode());
                }
                break;
            case TITLE:
                currentProduct.setTitle(elementValue.toString());
                if (DEBUG) {
                    System.out.println("title = " + currentProduct.getTitle());
                }
                break;
            case DESCRIPTION:
                currentProduct.setDescription(elementValue.toString());
                if (DEBUG) {
                    System.out.println("description = " + currentProduct.getDescription());
                }
                break;
            case URL:
                currentProduct.setUrl(elementValue.toString());
                if (DEBUG) {
                    System.out.println("url = " + currentProduct.getUrl());
                }
                break;
            case CATEGORY:
                categoryObj = new Category(elementValue.toString());
                break;
            case SUB_CATEGORY:
                subCategoryObj = new Category(elementValue.toString(), categoryObj);
                currentProduct.setCategory(subCategoryObj);
                if (DEBUG) {
                    System.out.println("category = " + currentProduct.getCategory().toString());
                }
                break;
            case MANUFACTURER:
                currentProduct.setManufacturer(elementValue.toString());
                if (DEBUG) {
                    System.out.println("manufacturer = " + currentProduct.getManufacturer());
                }
                break;
            case PRICE:
                if (variantsFlag) {
                    variant.setPrice(BigDecimal.valueOf(Double.parseDouble(elementValue.toString())));
                    if (DEBUG) {
                        System.out.println("variant's price = " + variant.getPrice());
                    }
                    break;
                }
                currentProduct.setPrice(BigDecimal.valueOf(Double.parseDouble(elementValue.toString())));
                if (DEBUG) {
                    System.out.println("price = " + currentProduct.getPrice());
                }
                break;
            case ORIGINAL_PRICE:
                currentProduct.setOriginalPrice(BigDecimal.valueOf(Double.parseDouble(elementValue.toString())));
                if (DEBUG) {
                    System.out.println("original price = " + currentProduct.getPrice());
                }
                break;
            case STOCK_STATUS:
                if (variantsFlag) {
                    variant.setStockStatus(elementValue.toString());
                    if (DEBUG) {
                        System.out.println("variant's stock status = " + variant.getStockStatus());
                    }
                    break;
                }
                currentProduct.setStatus(elementValue.toString());
                if (DEBUG) {
                    System.out.println("stock status = " + currentProduct.getStatus());
                }
                break;
            case IMAGES:
                if (imagesFlag && !variantsFlag) {
                    //System.out.println("add images: " + images);
                    currentProduct.setImages(images);
                    images = null;
                    imagesFlag = false;
                }
                break;
            case IMAGE:
                //System.out.println("end image: " + elementValue.toString());
                if (imagesFlag){
                    images.add(elementValue.toString());
                }
                break;
            case META_TITLE:
                currentProduct.setMetaTitle(elementValue.toString());
                if (DEBUG) {
                    System.out.println("meta title = " + currentProduct.getMetaTitle());
                }
                break;
            case META_DESCRIPTION:
                currentProduct.setMetaDescription(elementValue.toString());
                if (DEBUG) {
                    System.out.println("meta description = " + currentProduct.getMetaDescription());
                }
                break;
            case KEY:
                variant.setKey(elementValue.toString());
                if (DEBUG) {
                    System.out.println("key = " + variant.getKey());
                }
                break;
            case VALUE:
                if (optionsFlag) {
                    break;
                }
                variant.setValue(elementValue.toString());
                if (DEBUG) {
                    System.out.println("variant value = " + variant.getValue());
                }
                break;
            case VARIANTS:
                variantsFlag = false;
                break;
            case VARIANT:
                variants.add(variant);
                variant = null;
                break;
            case OPTIONS:
                optionsFlag = false;
                break;
            case CC_VARIANTS:
                ccVariantFlag = false;
                break;

        }
    }

    @Override
    public void startDocument() throws SAXException {
        products = new ArrayList<>();
        variantsFlag = false;
        optionsFlag = false;
        ccVariantFlag = false;
        imagesFlag = false;
        clearFlags();
    }

    private void clearFlags() {
        id = false;
        productCode = false;
        barCode = false;
        title = false;
        shortDescription = false;
        description = false;
        category = false;
        subCategory = false;
        manufacturer = false;
        price = false;
        originalPrice = false;
        stockStatus = false;
        ccVariantFlag = false;
    }
}
