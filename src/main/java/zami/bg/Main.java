package zami.bg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://zani.bg/").get();
        Elements categoriesNames = doc.select("#menu-classic-shop-main-menu >li a .menu-text");
        Elements lis = doc.getElementById("menu-classic-shop-main-menu").getElementsByTag("li");
        List<String> names = new ArrayList<>();
        for (Element li : lis) {
            Element span = li.getElementsByClass("menu-text").first();
            if (span != null) {
                names.add(span.text().trim());
            }
        }
        List<String> catUrls = new ArrayList<>();
        int index = 0;
        doc.select("#menu-classic-shop-main-menu >li").get(1).select("a").attr("href");

        for (int i = 0; i < categoriesNames.size(); i++) {
            catUrls.add(doc.select("#menu-classic-shop-main-menu >li").get(i).select("a").attr("href"));
            Category category = new Category(names.get(i), catUrls.get(i));
            System.out.println(category.toString());
        }
        System.out.println(names);
    }
    private static void log(String title) {
        System.out.println(title);
    }

    private static void log(String s, String title, String href) {
        System.out.println(title);
    }
}
