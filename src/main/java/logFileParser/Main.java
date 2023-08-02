package logFileParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            //TODO Print Date
            FileInputStream fstream = new FileInputStream("robo-vendor.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            HashSet<String> classNamesSet = new HashSet<>();
            HashMap<String, Robo> nameClassMap = new HashMap<>();
            /* read log line by line */
            int infoCount = 0;
            while ((strLine = br.readLine()) != null) {
                String severity = strLine.substring(strLine.indexOf("(") + 1, strLine.indexOf(")")).trim();
                if (severity.equalsIgnoreCase("info")) {
                    infoCount++;
                    continue;
                }
                String noDateString = strLine.substring(strLine.indexOf("]") + 1).trim();
                String noThreadString = noDateString.substring(noDateString.indexOf("]") + 1).trim();
                String javaClassName = noThreadString.substring(noThreadString.indexOf("[") + 1, noThreadString.indexOf("]"));
                String message = noThreadString.substring(noThreadString.indexOf(":") + 1).trim();
                if (classNamesSet.contains(javaClassName)) {
                    Robo robo = nameClassMap.get(javaClassName);
                    if (severity.equalsIgnoreCase("error")) {
                        robo.getLog().get("ERROR").add(message);
                    }
                    if (severity.equalsIgnoreCase("fatal")) {
                        robo.getLog().get("FATAL").add(message);
                    }
                    if (severity.equalsIgnoreCase("warn")) {
                        robo.getLog().get("WARN").add(message);
                    }
                }
                if (!classNamesSet.contains(javaClassName)) {
                    Robo robo = new Robo(javaClassName);
                    if (severity.equalsIgnoreCase("error")) {
                        robo.getLog().get("ERROR").add(message);
                    }
                    if (severity.equalsIgnoreCase("fatal")) {
                        robo.getLog().get("FATAL").add(message);
                    }
                    if (severity.equalsIgnoreCase("warn")) {
                        robo.getLog().get("WARN").add(message);
                    }
                    nameClassMap.put(javaClassName, robo);
                    classNamesSet.add(javaClassName);
                }
            }
            fstream.close();
            for(String className : nameClassMap.keySet()){
                Robo robo = nameClassMap.get(className);
                System.out.println("Class: " + robo.getName());
                System.out.println("Warnings: " + robo.getLog().get("WARN").size());
                for(String warn : robo.getLog().get("WARN")){
                    System.out.println(warn);
                }
                System.out.println("Errors: " + robo.getLog().get("ERROR").size());
                for(String error : robo.getLog().get("ERROR")){
                    System.out.println(error);
                }
                System.out.println("Fatals: " + robo.getLog().get("FATAL").size());
                for(String fatal : robo.getLog().get("FATAL")){
                    System.out.println(fatal);
                }
                System.out.println();
            }
            System.out.println("count of infos: " + infoCount);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
