package zami.bg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class secondAttempt {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://zani.bg/").get();
        doc.getElementsByClass("sub-menu").get(0);//gets the child categories info and now we need to take the names and the urls and put them in the category model
        doc.getElementsByClass("sub-menu").get(0).getElementsByTag("span").first();// we need to extract the text of this span
    }
}
