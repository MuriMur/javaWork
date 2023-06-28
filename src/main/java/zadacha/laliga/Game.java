package zadacha.laliga;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String result;
    private List<String> odds = new ArrayList<>();
    private String date;
    private Team host;
    private Team guest;
    private char winner;

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Game{" +
                "result='" + result + '\'' +
                ", odds=" + odds +
                ", date='" + date + '\'' +
                ", host=" + host +
                ", guest=" + guest +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getOdds() {
        return odds;
    }

    public void setOdds(List<String> odds) {
        this.odds = odds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Team getHost() {
        return host;
    }

    public void setHost(Team host) {
        this.host = host;
    }

    public Team getGuest() {
        return guest;
    }

    public void setGuest(Team guest) {
        this.guest = guest;
    }
}
