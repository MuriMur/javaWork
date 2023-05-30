import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LaLiga {
    public static void main() throws IOException {
        HashMap<String, List<String>> laLigaTable = new HashMap<>();
        Document doc = Jsoup.connect("https://www.betexplorer.com/football/spain/laliga-2021-2022/results/").get();
        log(doc.title());
        Elements teams = doc.select("#js-leagueresults-all table tr .h-text-left span");
        Elements results = doc.select("#js-leagueresults-all table tr .h-text-center a ");
//        for (Element team : teams) {
//            log(team.text());
//        }
        Element teamHost;
        Element teamGuest = null;
        List<String> teamHostResults;
        List<String> teamGuestResults = null;
        int index = 0;
        Element trRound = null;
        Elements siblings = null;
        String roundName;
        while(index < teams.size()){
            teamHost = teams.get(index);
            Element tr = teamHost.parent().parent().parent();
            siblings = tr.previousElementSibling().select(".h-text-left");
            while (siblings == null || siblings.isEmpty()){
                tr = tr.previousElementSibling();
                siblings = tr.select(".h-text-left");
            }
            roundName = siblings.get(0).text();
            log(roundName);
            teamHostResults = laLigaTable.get(teamHost.text());
            if (teamHostResults == null){
                teamHostResults = new ArrayList<>();
                laLigaTable.put(teamHost.text(), teamHostResults);
            }
            index++;
            if (index < teams.size()){
                teamGuest = teams.get(index);
                teamGuestResults = laLigaTable.get(teamGuest.text());
                if (teamGuestResults == null){
                    teamGuestResults = new ArrayList<>();
                    laLigaTable.put(teamGuest.text(), teamGuestResults);
                }
            }else {
                System.err.println("invalid html format");
                System.exit(1);
            }
            log("host: " + teamHost.text() + "; guest: " + teamGuest.text());
            Element result = results.get(index / 2);
            log("result: " + result.text());
            String strResult = result.text().trim();
            int host = Integer.parseInt(strResult.substring(0, strResult.indexOf(":")));
            int guest = Integer.parseInt(strResult.substring(strResult.indexOf(":") + 1, strResult.length()));
            if (host < guest){
                teamHostResults.add("2");
                teamGuestResults.add("1");
            }else if (host > guest){
                teamHostResults.add("1");
                teamGuestResults.add("2");
            }else{
                teamHostResults.add("x");
                teamGuestResults.add("x");
            }
            index++;
        }
        ArrayList<String> teamNames = new ArrayList<>();
        teamNames.addAll(laLigaTable.keySet());
        Collections.sort(teamNames);
        PrintStream fileWriter = new PrintStream("export.txt");
        for (String teamName : teamNames) {
            List<String> teamResult = laLigaTable.get(teamName);
            fileWriter.print(teamName + ", ");
            for (int i = teamResult.size() - 1; i > 0;  i--) {
                fileWriter.print(teamResult.get(i) + ", ");
             }
            if (teamResult.size() > 0){
                fileWriter.print(teamResult.get(0));
            }
            fileWriter.println();
        }
    }

    private static void log(String title) {
        System.out.println(title);
    }

    private static void log(String s, String title, String href) {
        System.out.println(title);
    }
}
