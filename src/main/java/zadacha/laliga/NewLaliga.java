package zadacha.laliga;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewLaliga {
    public static void main() throws IOException {
        HashMap<String, List<String>> laLigaTable = new HashMap<>();
        // TODO Exception if connection is not successful
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.betexplorer.com/football/spain/laliga-2021-2022/results/").get();
        } catch (Exception e) {
            System.out.println("page not found");
        }
        Elements teamElements = doc.select("#js-leagueresults-all table tr .h-text-left span");
        Elements results = doc.select("#js-leagueresults-all table tr .h-text-center a ");
//        for (Element team : teams) {
//            log(team.text());
//        }
        Element teamHostElement;

        League league = new League();
        Game game;
        List<Round> rounds = new ArrayList<>();
        Team team;
        List<Team> teams = new ArrayList<>();
        Round roundObj = null;
        Element teamGuest = null;
        List<String> teamHostResults = null;
        List<String> teamGuestResults = null;
        int index = 0;
        Element trRound = null;
        Elements siblings = null;
        String roundName;
        Element table = doc.getElementsByClass("table-main js-tablebanner-t js-tablebanner-ntb").first();
        Elements trs = table.getElementsByTag("tr");
        Round currentRound = null;

        for (Element tr : trs) {
            if (tr.childrenSize() == 5){
                // TODO Round
//                currentRound = new Round();
//                currentRound.setNumber(tr.getElementsByAttribute("colspan").text());
//                if (rounds.contains(currentRound)) {
//                    rounds.get()
//                    System.out.println("zapochva rund " + currentRound.getNumber());
//                    rounds.add(currentRound);
//                }
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
            } else if (tr.childrenSize() == 6) {
                // TODO Game
                // ima6 6 kletki i vurvi6 po tqx, suzdavaiki edin Game obekt
                // i nakraq go dobavq6 kum sega6niq rund
                game = new Game();
                Elements spans = tr.getElementsByTag("td").first().getElementsByTag("span");
                List<String> teamsNames = new ArrayList<>();
                for (Element span : spans) {
                    String teamName = span.text();
                    teamsNames.add(teamName);
                }
                Team host = new Team();
                host.setName(teamsNames.get(0));
                Team guest = new Team();
                guest.setName(teamsNames.get(1));
                game.setHost(host);
                game.setGuest(guest);
                if (!teams.contains(host) && !teams.contains(guest)) {
                    teams.add(host);
                    teams.add(guest);
                }
                String result = tr.getElementsByClass("h-text-center").first().text();

                if (teamHostResults == null){
                    teamHostResults = new ArrayList<>();
                    host.setResults(teamHostResults);
                }
                if (teamGuestResults == null){
                    teamGuestResults = new ArrayList<>();
                    guest.setResults(teamGuestResults);
                }
                int hostRes = Integer.parseInt(result.substring(0, result.indexOf(":")));
                int guestRes = Integer.parseInt(result.substring(result.indexOf(":") + 1, result.length()));
                if (hostRes < guestRes){
                    teamHostResults.add("2");
                    teamGuestResults.add("1");
                }
                if (hostRes == guestRes){
                    teamHostResults.add("x");
                    teamGuestResults.add("x");
                }
                if (hostRes > guestRes){
                    teamHostResults.add("1");
                    teamGuestResults.add("2");
                }
                game.setResult(result);
                String date = tr.getElementsByClass("h-text-right h-text-no-wrap").first().text();
                game.setDate(date);
                currentRound.getGames().add(game);
            }
        }
        rounds.add(currentRound);
        // TODO na izlizane ot for(), vij current round kakvo ima v nego, i go dobavi i nego, ako e nov

        league.setTeams(teams);
        league.setRounds(rounds);
        league.setName(doc.select(".wrap-section__header__title .tablet-desktop-only").text());
    }
}
