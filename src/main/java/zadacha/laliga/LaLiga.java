package zadacha.laliga;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
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

// TODO Kato cqlo
//  da se pomisli za vyzmojnite abstrakcii, kato obekti, clasove i t.n.
//  za6toto sega ti e v nqkakvi masivi i se obikalq po tqh, i trqbva da znae6 vseki masiv, kak to4no e podredena informaciqta v nego
//  ako se popadne na drug sait, v koito tablicite sa po drug na4in, aide Jsoup logikata 6te se pi6e nanovo (za6toto e drug sait), no pone
//  tiq masivi trqbva da sa su6tite i t.n.
//  po-dobre, vseki masiv ot rezultati da e nqkakuv League obekt, s List<Round> obekti,
//  vseki Round obekt da ima List<Game> obekti
//  i vseki Game obekt vutre da ima List<Teams>, da ima SCORE, da ima ODDS, i da ima data
//  otdelno vuv vseki Team moje da se poddurja nqkakva statistika?? ili moje bi da e vutre v League obekta??

public class LaLiga {
    // TODO Divide this main into several methods
    // TODO Basic connection method, which only connects to a page and returns a Document
    // TODO Jsoup method, which extracts data
    // TODO Print method, which prints data to a file or outputstream
    public static void main() throws IOException {
        HashMap<String, List<String>> laLigaTable = new HashMap<>();
        // TODO Exception if connection is not successful
        Document doc = null;
        try {
             doc = Jsoup.connect("https://www.betexplorer.com/football/spain/laliga-2021-2022/results/").get();
        }
        catch (Exception e){
            System.out.println("page not found");
        }
        log(doc.title());
        Elements teamElements = doc.select("#js-leagueresults-all table tr .h-text-left span");
        Elements results = doc.select("#js-leagueresults-all table tr .h-text-center a ");
//        for (Element team : teams) {
//            log(team.text());
//        }
        Element teamHostElement;

        League league = new League();
        Game game;
        List<Game> games = null;
        List<Round> rounds = new ArrayList<>();
        Team team;
        List<Team> teams = new ArrayList<>();
        Round roundObj = null;
        Element teamGuest = null;
        List<String> teamHostResults;
        List<String> teamGuestResults = null;
        int index = 0;
        Element trRound = null;
        Elements siblings = null;
        String roundName;
        Element table = doc.getElementsByClass("table-main js-tablebanner-t js-tablebanner-ntb").first();
        Elements roundsEls = table.getElementsByAttribute("colspan");
        for (Element roundEl : roundsEls
             ) {
            games = new ArrayList<>();
            Round round = new Round();
            round.setNumber(roundEl.text());
            if (!rounds.contains(round)) {
                rounds.add(round);
            }
        }
        while(index < teamElements.size()){
            teamHostElement = teamElements.get(index);
            Element tr = teamHostElement.parent().parent().parent();
            siblings = tr.previousElementSibling().select(".h-text-left");
            while (siblings == null || siblings.isEmpty()){
                tr = tr.previousElementSibling();
                siblings = tr.select(".h-text-left");

            }
            roundName = siblings.get(0).text();
            log(roundName);
            games = new ArrayList<>();
            teamHostResults = laLigaTable.get(teamHostElement.text());
            roundObj = new Round();
            roundObj.setNumber(siblings.get(0).text());
            roundObj.setGames(games);
            game = new Game();
            if (teamHostResults == null){
                teamHostResults = new ArrayList<>();
                laLigaTable.put(teamHostElement.text(), teamHostResults);
                team = new Team();
                team.setName(teamHostElement.text());
                team.setResults(teamHostResults);
                teams.add(team);
                game.setHost(team);
            }
            index++;
            if (index < teamElements.size()){
                teamGuest = teamElements.get(index);
                teamGuestResults = laLigaTable.get(teamGuest.text());
                if (teamGuestResults == null){
                    teamGuestResults = new ArrayList<>();
                    laLigaTable.put(teamGuest.text(), teamGuestResults);
                    team = new Team();
                    team.setResults(teamGuestResults);
                    team.setName(teamGuest.text());
                    teams.add(team);
                    game.setGuest(team);
                }
            }
            else {
                System.err.println("invalid html format");
                System.exit(1); // TODO Exception and continue here
            }
            log("host: " + teamHostElement.text() + "; guest: " + teamGuest.text());
            Element result = results.get(index / 2);
            log("result: " + result.text());
            String strResult = result.text().trim();
            game.setResult(strResult);
            games.add(game);
            if (roundObj != null) {
                rounds.add(roundObj);
            }
            int host = Integer.parseInt(strResult.substring(0, strResult.indexOf(":")));
            int guest = Integer.parseInt(strResult.substring(strResult.indexOf(":") + 1, strResult.length()));
            // TODO Replace hardcoded symbol with ENUM, which means the same thing anywhere
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
        league.setRounds(rounds);
        league.setTeams(teams);
        league.setName("la liga");
        ArrayList<String> teamNames = new ArrayList<>();
        teamNames.addAll(laLigaTable.keySet());
        Collections.sort(teamNames);

        // TODO Move "print to file" logic to another method/class
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
