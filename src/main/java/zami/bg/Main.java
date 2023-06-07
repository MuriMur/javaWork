package zami.bg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://zani.bg/").get();
        Elements mainMenuLis = doc.getElementById("menu-classic-shop-main-menu").getElementsByTag("li");
        List<String> names = new ArrayList<>();
        PrintStream fileWriter = new PrintStream("export-of-categories.txt");
        List<String> podCategoriesNames = null;
        List<Category> categoryList = new ArrayList<>();
        List<Category> parentCategoryList = new ArrayList<>();
        PrintStream fileWriter1 = new PrintStream("export-of-products.txt");
        for (Element li : mainMenuLis) {
            Element span = li.getElementsByClass("menu-text").first();
            Element url = li.getElementsByAttribute("href").first();
            Element subMenu = li.getElementsByTag("ul").first();
            if (span != null) {
                names.add(span.text().trim());
                Category category = new Category(span.text().trim(), url.attr("href"));
                categoryList.add(category);
                parentCategoryList.add(category);
                if (subMenu == null) {
                    //fileWriter.println(categoryList.get(categoryList.size() - 1));
                    System.out.println(categoryList.get(categoryList.size() - 1));
                    readProducts(category.getUrlAddress(), category , fileWriter1);

                }
            }
            if (subMenu == null) {
                //fileWriter.println(parentCategoryList.get(parentCategoryList.size()-1));
                continue;
            }
            Elements subMenuList = subMenu.getElementsByTag("li");
            for (Element li1 : subMenuList) {
                Element spanSub = li1.getElementsByTag("span").first();
                Element subUrl = li1.getElementsByAttribute("href").first();
                if (spanSub == null) {
                    continue;
                }
                podCategoriesNames = new ArrayList<>();
                podCategoriesNames.add(spanSub.text().trim());
                Category category = new Category(podCategoriesNames.get(0), subUrl.attr("href"), parentCategoryList.get(parentCategoryList.size() - 1));
                categoryList.add(category);
                //fileWriter.println(category);
                System.out.println(category);
                readProducts(category.getUrlAddress(), category, fileWriter1);
            }

        }
    }
    public static void readProducts(String url, Category category, PrintStream fileWriter) throws IOException{
        Document podDoc = Jsoup.connect(url).get();
        Elements tableLis = podDoc.getElementsByClass("products clearfix products-4");
        Elements lis = tableLis.select("li");
        int index = 1;
        //String stringIndex = "1";
        List<Product> products = new ArrayList<>();
        if (podDoc.getElementsByClass("next page-numbers").first() == null) {
            readProduct(url + "page/" + index + "/", fileWriter, category);
        }
        while(podDoc.getElementsByClass("next page-numbers").first() != null) {
            System.out.println("Now going to " + url + "/page/" + index + "/");
            podDoc = Jsoup.connect(url + "page/" + index + "/").get();
            tableLis = podDoc.getElementsByClass("products clearfix products-4");
            lis = tableLis.select("li");
            index++;
            //stringIndex = String.valueOf(index);
            fileWriter.println(category.getName());
            for (Element li : lis) {
                Element podUrl = li.getElementsByAttribute("href").first();
                Document document = Jsoup.connect(podUrl.attr("href")).get();
                Element summaryContainer = document.getElementsByClass("summary-container").first();
                String name = summaryContainer.getElementsByAttributeValue("itemprop", "name").first().text();
                Element priceEl = summaryContainer.getElementsByClass("price").first();
                String price = "";
                String promoPrice = "";
                String finalPrice = "";
                BigDecimal promoPriceBigDecimal = null;

                if (priceEl.getElementsByTag("bdi").first() != null){
                    price = priceEl.getElementsByTag("bdi").first().text();
                    finalPrice = price;
                }else if (priceEl.getElementsByTag("del").first() != null){
                    price = priceEl.getElementsByTag("del").first().text();
                    promoPrice = priceEl.getElementsByTag("ins").first().text();
                    finalPrice = promoPrice;
                    promoPriceBigDecimal = BigDecimal.valueOf(Double.parseDouble(promoPrice));

                }else{
                    finalPrice = summaryContainer.getElementsByClass("price").first().text();
                }
                BigDecimal finalPriceBigDecimal = BigDecimal.valueOf(Double.parseDouble(finalPrice));
//                Elements spans = priceEl.getElementsByClass("woocommerce-Price-amount amount");
//                Stack<String> prices = new Stack<>();
//                for (Element span : spans) {
//                    prices.push(span.text());
//                }
                Element galleryElement = document.getElementsByClass("avada-single-product-gallery-wrapper avada-product-images-global avada-product-images-thumbnails-bottom").first();
                Elements as = galleryElement.getElementsByTag("img");
                List<String> images = new ArrayList<>();
                for (Element a : as) {
                    images.add(a.attr("data-src"));
                }
                Element productMeta = document.getElementsByClass("product_meta").first();
                String catalogNumber = productMeta.getElementsByClass("sku").text();
                Element moreInformationTable = document.getElementsByClass("woocommerce-product-attributes shop_attributes").first();
                Elements trs = moreInformationTable.getElementsByTag("tr");
                HashMap<String, String> features = new HashMap<>();
                for (Element tr : trs) {
                    features.put(tr.getElementsByClass("woocommerce-product-attributes-item__label").first().text(),
                            tr.getElementsByClass("woocommerce-product-attributes-item__value").first().text());
                }
                Element descEl = document.getElementsByClass("woocommerce-Tabs-panel woocommerce-Tabs-panel--description panel entry-content wc-tab").first()
                        .getElementsByClass("post-content").first();
                String description = descEl.html();
                Product product = new Product(name, finalPriceBigDecimal, category, images, catalogNumber, features, description, promoPriceBigDecimal);
                System.out.println(product);
                fileWriter.println(product);
                products.add(product);
                //sleep(800);
            }
            //fileWriter.println("Now going to " + url + "/page/" + index + "/");
        }
        System.out.println(products.size());
    }
    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static List<Product> readProduct(String url, PrintStream fileWriter, Category category) throws IOException {
        List<Product> products = new ArrayList<>();
        Document podDoc = Jsoup.connect(url).get();
        Elements tableLis = podDoc.getElementsByClass("products clearfix products-4");
        Elements lis = tableLis.select("li");
        //stringIndex = String.valueOf(index);
        fileWriter.println(category.getName());
        for (Element li : lis) {
            Element podUrl = li.getElementsByAttribute("href").first();
            Document document = Jsoup.connect(podUrl.attr("href")).get();
            Element summaryContainer = document.getElementsByClass("summary-container").first();
            String name = summaryContainer.getElementsByAttributeValue("itemprop", "name").first().text();
            Element priceEl = summaryContainer.getElementsByClass("price").first();
            String price = "";
            String promoPrice = "";
            String finalPrice = "";
            BigDecimal promoPriceBigDecimal = null;
            if (priceEl.getElementsByTag("bdi").first() != null){
                price = priceEl.getElementsByTag("bdi").first().text();
                finalPrice = price;
            }else if (priceEl.getElementsByTag("del").first() != null){
                price = priceEl.getElementsByTag("del").first().text();
                promoPrice = priceEl.getElementsByTag("ins").first().text();
                finalPrice = promoPrice;
                promoPriceBigDecimal = BigDecimal.valueOf(Double.parseDouble(promoPrice));

            }else{
                finalPrice = summaryContainer.getElementsByClass("price").first().text();
            }
            BigDecimal finalPriceBigDecimal = BigDecimal.valueOf(Double.parseDouble(finalPrice));
            Element galleryElement = document.getElementsByClass("avada-single-product-gallery-wrapper avada-product-images-global avada-product-images-thumbnails-bottom").first();
            Elements as = galleryElement.getElementsByTag("img");
            List<String> images = new ArrayList<>();
            for (Element a : as) {
                images.add(a.attr("data-src"));
            }
            Element productMeta = document.getElementsByClass("product_meta").first();
            String catalogNumber = productMeta.getElementsByClass("sku").text();
            Element moreInformationTable = document.getElementsByClass("woocommerce-product-attributes shop_attributes").first();
            Elements trs = moreInformationTable.getElementsByTag("tr");
            HashMap<String, String> features = new HashMap<>();
            for (Element tr : trs) {
                features.put(tr.getElementsByClass("woocommerce-product-attributes-item__label").first().text(),
                        tr.getElementsByClass("woocommerce-product-attributes-item__value").first().text());
            }
            Element descEl = document.getElementsByClass("woocommerce-Tabs-panel woocommerce-Tabs-panel--description panel entry-content wc-tab").first()
                    .getElementsByClass("post-content").first();
            String description = descEl.html();
            Product product = new Product(name, finalPriceBigDecimal, category, images, catalogNumber, features, description, promoPriceBigDecimal);
            System.out.println(product);
            fileWriter.println(product);
            products.add(product);
            //sleep(800);
        }
        return products;
    }
    public static void writeProducts(List<Product> products) throws FileNotFoundException {
        PrintStream fileWriter = new PrintStream("export-of-products.txt");
        for (int i = 0; i < products.size(); i++){
            fileWriter.println(products.get(i));
        }
    }
}

