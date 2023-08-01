package logFileParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
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

                System.out.println(strLine);
            }
            fstream.close();

            System.out.println("count of infos: " + infoCount);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
