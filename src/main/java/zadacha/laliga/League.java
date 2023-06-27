package zadacha.laliga;

import java.util.ArrayList;
import java.util.List;

public class League {
    private String name;
    private List<Team> teams = new ArrayList<>();
    private List<Round> rounds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "League{" +
                "name='" + name + '\'' +
                ", teams=" + teams +
                ", rounds=" + rounds +
                '}';
    }
}
