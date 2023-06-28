package zadacha.laliga;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class NewLaliga {
    public static void main() throws IOException {
        // TODO Exception if connection is not successful
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.betexplorer.com/football/spain/laliga-2021-2022/results/").get();
        } catch (Exception e) {
            System.out.println("page not found");
        }
        League league = new League();
        Game game;
        List<Round> rounds = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        Element table = doc.getElementsByClass("table-main js-tablebanner-t js-tablebanner-ntb").first();
        Elements trs = table.getElementsByTag("tr");
        Round currentRound = null;

        for (Element tr : trs) {
            if (tr.childrenSize() == 5){
                String roundText = tr.getElementsByAttribute("colspan").text();
                String previousRoundNumber = currentRound == null ? "" : currentRound.getNumber();
                if (currentRound != null && rounds.stream().noneMatch(round -> round.getNumber().equals(previousRoundNumber))) {
                    System.out.println("Adding a new round with number " + currentRound.getNumber());
                    rounds.add(currentRound);
                }
                currentRound = rounds.stream().filter(round -> round.getNumber().equals(roundText)).findFirst().orElse(null);
                if (currentRound == null) {
                    currentRound = new Round();
                    System.out.println("Starting a new round: " + roundText);
                } else {
                    System.out.println("Keep adding games to round " + currentRound.getNumber());
                }
                currentRound.setNumber(roundText);
                // System.out.println("current round " + currentRound.getNumber());
            }
            else if (tr.childrenSize() == 6) {
                game = new Game();
                Elements spans = tr.getElementsByTag("td").first().getElementsByTag("span");
                List<String> teamsNames = new ArrayList<>();
                for (Element span : spans) {
                    String teamName = span.text();
                    teamsNames.add(teamName);
                }

                String hostName = teamsNames.get(0);
                String guestName = teamsNames.get(1);

                // TODO Attempt to find an existing team by name
                Team host = teams.stream()
                        .filter(team -> team.getName().equalsIgnoreCase(hostName))
                        .findFirst().orElse(null);
                if (host == null) {
                    // TODO It's a new team, add it to the list of teams
                    host = new Team();
                    host.setName(hostName);
                    teams.add(host);
                }

                // TODO Same here for the guest team
                Team guest = teams.stream()
                        .filter(team -> team.getName().equalsIgnoreCase(guestName))
                        .findFirst().orElse(null);
                if (guest == null) {
                    guest = new Team();
                    guest.setName(guestName);
                    teams.add(guest);
                }

                game.setHost(host);
                game.setGuest(guest);

                String result = tr.getElementsByClass("h-text-center").first().text();
                int hostRes = Integer.parseInt(result.substring(0, result.indexOf(":")));
                int guestRes = Integer.parseInt(result.substring(result.indexOf(":") + 1, result.length()));
                if (hostRes < guestRes){
                    game.setWinner('2');
                }
                if (hostRes == guestRes){
                    game.setWinner('X');
                }
                if (hostRes > guestRes){
                    game.setWinner('1');
                }
                game.setResult(result);
                String date = tr.getElementsByClass("h-text-right h-text-no-wrap").first().text();
                game.setDate(date);
                currentRound.getGames().add(game);
            }
        }
        rounds.add(currentRound);
        league.setTeams(teams);
        league.setRounds(rounds);
        league.setName(doc.select(".wrap-section__header__title .tablet-desktop-only").text());

        for (Round round1 : league.getRounds()) {
            for (Game game1 : round1.getGames()) {
                if ('1' == game1.getWinner()) {
                    game1.getHost().addResult('1');
                    game1.getGuest().addResult('2');
                }
                else if ('2' == game1.getWinner()){
                    game1.getHost().addResult('2');
                    game1.getGuest().addResult('1');
                }
                else if ('X' == game1.getWinner()){
                    game1.getHost().addResult('X');
                    game1.getGuest().addResult('X');
                }
            }
        }
        for (Team team1 : league.getTeams()) {
            Collections.reverse(team1.getResults());
            System.out.println(team1.getName() + " " + team1.getResults());
        }
        calcXOdds(league, "Elche", 0, 7);
    }
    public static void calcXOdds(League league, String name, int indexStart, int indexStop){
        for (Team team : league.getTeams()) {
            if (team.getName().equalsIgnoreCase(name)){
               char[] arr =  new char[team.getResults().size()];
                for (int i = 0; i < team.getResults().size(); i++) {
                    arr[i] = team.getResults().get(i);
                }
                int counter = 0;
                boolean hasX = false;
                for (int j = indexStart; j < indexStop; j++){
                    if (arr[j] == '1' || arr[j] == '2'){
                        counter++;
                        if (counter == 7){
                            System.out.println("Време за залог!");
                        }
                    }
                    else if (arr[j] == 'X'){
                        counter = 0;
                        hasX = true;
                        System.out.println("Скоро е имало равен така че по добре изчакай");
                        break;
                    }
                }
            }
        }
    }
}
