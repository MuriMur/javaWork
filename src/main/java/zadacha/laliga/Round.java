package zadacha.laliga;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Round {
    private String number;
    private List<Game> games = new ArrayList<>();

    public List<Game> getGames() {
        return games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return (number.equals(round.number));
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, games);
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Round{" +
                "number='" + number + '\'' +
                ", games=" + games.size() +
                '}';
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
