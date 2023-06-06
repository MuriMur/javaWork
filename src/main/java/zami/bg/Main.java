package zami.bg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                String price = summaryContainer.getElementsByClass("price").first().text();
                Elements imageTableLis = document.getElementsByClass("ol");
                Elements iLis = imageTableLis.select("li");

                List<String> images = new ArrayList<>();
                for (Element li1 : iLis) {
                    Element element = document.getElementsByClass("avada-single-product-gallery-wrapper avada-product-images-global avada-product-images-thumbnails-bottom").first().getElementsByTag("img").first();
                    images.add(element.text());
                }

                Product product = new Product(name, price, category);
                System.out.println(product);
                fileWriter.println(product);
                products.add(product);
                //sleep(800);
            }
            fileWriter.println("Now going to " + url + "/page/" + index + "/");
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
            String price = summaryContainer.getElementsByClass("price").first().text();
            Product product = new Product(name, price, category);
            System.out.println(product);
            fileWriter.println(product);
            products.add(product);
            //sleep(800);
        }
        return products;
    }
}

