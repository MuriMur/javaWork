package zami.bg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://zani.bg/").get();
        Elements mainMenuLis = doc.getElementById("menu-classic-shop-main-menu").getElementsByTag("li");
        List<String> names = new ArrayList<>();
        PrintStream fileWriter = new PrintStream("export-of-categories.txt");
        List<String> podCategories = null;
        List<Category> categoryList = new ArrayList<>();
        List<Category> parentCategoryList = new ArrayList<>();
        for (Element li : mainMenuLis) {
            Element span = li.getElementsByClass("menu-text").first();
            Element url = li.getElementsByAttribute("href").first();
            if (span != null) {
                names.add(span.text().trim());
                Category category = new Category(span.text().trim(), url.attr("href"));
                categoryList.add(category);
                parentCategoryList.add(category);
            }
            Element subMenu = li.getElementsByTag("ul").first();
            if (subMenu == null) {
                //fileWriter.println(parentCategoryList.get(parentCategoryList.size()-1));
                //fileWriter.println(categoryList.get(categoryList.size() -1));
                continue;
            }
            Elements subMenuList = subMenu.getElementsByTag("li");
            for (Element li1 : subMenuList) {
                Element spanSub = li1.getElementsByTag("span").first();
                Element subUrl = li1.getElementsByAttribute("href").first();
                if (spanSub == null) {
                    continue;
                }
                podCategories = new ArrayList<>();
                podCategories.add(spanSub.text().trim());
                Category category = new Category(podCategories.get(0), subUrl.attr("href"), parentCategoryList.get(parentCategoryList.size() - 1));
                categoryList.add(category);
                //fileWriter.println(category);
            }

        }
        for (int i = 0; i < categoryList.size(); i++) {
            fileWriter.println(categoryList.get(i));
        }
//        List<String> catUrls = new ArrayList<>();
//        List<String> childCatUrls = new ArrayList<>();
//        doc.select("#menu-classic-shop-main-menu >li").get(1).select("a").attr("href");
//        for (int i = 0; i < parentCategoriesNames.size(); i++) {
//            catUrls.add(doc.select("#menu-classic-shop-main-menu >li").get(i).select("a").attr("href"));
//            Category category = new Category(names.get(i), catUrls.get(i));
//            fileWriter.println(category.toString());
//        }
//
//        while (index < parentCategoriesNames.size()) {
//            while(index < doc.getElementsByClass("sub-menu").get(index).childrenSize()) {
//          Element span = doc.getElementsByClass("sub-menu").get(index).getElementsByTag("span").first();
//                childCatUrls.add(doc.select("#menu-classic-shop-main-menu >li .sub-menu >li").get(index).select("a").attr("href"));
//                podCategories = doc.getElementsByClass("sub-menu").get(index).getElementsByTag("span").eachText();
//               Category category1 = new Category(podCategories.get(index), childCatUrls.get(index), names.get(index + 1));
//                fileWriter.println(category1);
//                index++;
//            }
//            index++;
//        }
//        System.out.println(names);
//    }
    }
}
