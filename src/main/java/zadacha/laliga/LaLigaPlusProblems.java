package zadacha.laliga;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// TODO Rename this class to LaLigaFormulas ili neshto takova
public class LaLigaPlusProblems {
    // TODO Make this return the actual odds, and also explain the logic in a comment
    public static String calcXOdds(String[][] arr, String s) throws FileNotFoundException {
        //PrintStream fileWriter = new PrintStream("export2.txt");
        StringBuilder sb = new StringBuilder();
        char c = '%';
        float counter = 0;
        float xOdd = 0;
        boolean hasX = false;
        for (int row = 0; row < arr.length; row++) {
            for (int col = arr[0].length - 1; col >= 0; col--) {
                if(arr[row][col].equalsIgnoreCase("1") || arr[row][col].equalsIgnoreCase("2")){
                    counter++;
                    if (counter < 5){
                        xOdd = 10;
                    }
                    if (counter == 5 && !hasX) {
                        xOdd = 20;
                    }else if(counter > 5){
                        xOdd = 20 + ((counter - 5) * 10);
                        //System.out.println("xOdd= " + xOdd +" " + "row: " + row);
                        if (xOdd >= 100){
                            xOdd = 99;
                        }
                    }
                } else if (arr[row][col].equalsIgnoreCase(s)){
                    counter = 0;
                    hasX = true;
                    break;
                }
            }
            sb.append(String.format("the team %s has %.0f%c chance to draw in next match\n", arr[row][0], xOdd, c));
            //fileWriter.printf("the team %s has %.0f percentage chance to draw in next match\n", arr[row][0], xOdd);
        }
        //fileWriter.close();
        return sb.toString();
    }

    // TODO Make this return the actual odds, and also explain the logic in a comment
    public static String calcWinOdds(String[][] arr, String s) throws FileNotFoundException {
        //PrintStream fileWriter = new PrintStream("export.txt");
        int rowCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < arr.length; row++) {
            for (int col = arr[0].length - 1; col >= 0; col--) {
                if (arr[row][col].equalsIgnoreCase(s)){
                    rowCount++;
                }else if(arr[row][col].equalsIgnoreCase("x") || arr[row][col].equalsIgnoreCase("2")) {
                    float winOdd = (30 + (10 * rowCount));
                    if (winOdd > 100) {
                        winOdd = 99;

                    }
                    sb.append(String.format("the team %s has win streak of %d and has %.0f percentage chance to win the next game\n", arr[row][0], rowCount, winOdd));
                    //fileWriter.printf("the team %s has win streak of %d and has %.0f percentage chance to win the next game\n", arr[row][0], rowCount, winOdd);
                    break;
                }
            }
            //System.out.printf("the team %s's is %s with: %d %s's\n", arr[rowNumber][0], s , maxCount, s );

            rowCount = 0;

        }
        //fileWriter.close();
        return sb.toString();
    }

    // TODO Make this a generic function which works for any symbol (DONE), and which returns the result
    public static void countSymbolForTeam(String[][] arr, String s){
        int maxCount = Integer.MIN_VALUE;
        int rowCount = 0;
        int rowNumber = 0;
        for (int row = 0; row < arr.length; row++) {
            for (int col = 1; col < arr[0].length; col++) {
                if (arr[row][col].equalsIgnoreCase(s)){
                    rowCount++;
                }
                if (rowCount > maxCount){
                    maxCount = rowCount;
                    rowNumber = row;
                }
            }
            rowCount = 0;
        }
        System.out.printf("the team with most %s's is %s with: %d %s's\n",s ,arr[rowNumber][0], maxCount, s );
    }

    // TODO Make this a generic function which works for any symbol, and which returns the result
    public static void findMaxColX(String[][] arr){
        int maxCount = Integer.MIN_VALUE;
        int colCount = 0;
        int colNumber = 0;
        for (int col = 1; col < arr[0].length; col++) {
            for (int row = 0; row < arr.length; row++) {
                if (arr[row][col].equalsIgnoreCase("x")){
                    colCount++;
                }
                if (colCount > maxCount){
                    maxCount = colCount;
                    colNumber = col;
                }
            }
            colCount = 0;
        }
        System.out.printf("the GameWeek with most X's is %d with: %d X's\n", colNumber, maxCount );
    }

    public static int countTheSymbol(String[][] arr, String s){
        int counter = 0;
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                if (arr[row][col].equalsIgnoreCase(s)){
                    counter++;
                }
            }
        }
        return counter;
    }

    public static void printArray(String[][] arr){
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                System.out.printf("%s ", arr[row][col]);
            }
            System.out.println();
        }
    }

    public static void main()
            throws IOException
    {
        //  WhatIsJava whatIsJava = new WhatIsJava();
        //whatIsJava.primer();

        //if (1 == 1)
        //  return;

        // list that holds strings of a file
        List<String> listOfRows = new ArrayList<String>();
        // load data from file
        BufferedReader bf = new BufferedReader(new FileReader("export.txt"));
        // read entire line as string
        String line = bf.readLine();
        // checking for end of file
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
        printArray(arr);
        //System.out.printf("count of symbol is %d\n", countTheSymbol(arr, "1"));
        // closing bufferreader object
        bf.close();
        findMaxColX(arr);
        countSymbolForTeam(arr, "x");
        PrintStream fileWriter = new PrintStream("export3.txt");
        fileWriter.print(calcWinOdds(arr, "1"));
        fileWriter.printf("\n");
        fileWriter.print( calcXOdds(arr, "x"));
        fileWriter.close();
        // storing the data in arraylist to array
//        String[] array = listOfRows.toArray(new String[0]);
        // printing each line of file
        // which is stored in array
//        for (String str : array) {
//            System.out.println(str);
//        }
    }

}

