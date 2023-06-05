package zami.bg;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SecondMain {
    public static void main(String[] args) throws IOException {
        List<String> listOfRows = new ArrayList<String>();
        // load data from file
        BufferedReader bf = new BufferedReader(new FileReader("export-of-categories.txt"));
        // read entire line as string
        String line = bf.readLine();
        // checking for end of file
        PrintStream fileWriter = new PrintStream("export-of-links.txt");
        while (line != null) {
            listOfRows.add(line);
            line = bf.readLine();
        }
        int ROWS = listOfRows.size();
        int COLS = listOfRows.get(0).split(",").length;
        String[][] arr = new String[ROWS][COLS];
        for (int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++) {
                String currentLine = listOfRows.get(row);
                String[] parts =  currentLine.split(",");
                arr[row][col] = parts[col].trim();
            }
        }
        //printArray(arr);
        bf.close();
        System.out.println(arr[0][1]);
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
    public static void printArray(String[][] arr){
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                System.out.printf("%s ", arr[row][col]);
            }
            System.out.println();
        }
    }
}
