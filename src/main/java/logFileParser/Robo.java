package logFileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Robo {
    private String name;
    private int warnCounter;
    private int errorCounter;
    private int fatalCounter;
    private List<String> warnings = new ArrayList<>();
    private List<String> fatalMsgs = new ArrayList<>();
    private List<String> errors = new ArrayList<>();
    private HashMap<String, List<String>> log;

    @Override
    public String toString() {
        return "Robo{" +
                "name='" + name + '\'' +
                ", warnCounter=" + warnCounter +
                ", errorCounter=" + errorCounter +
                ", fatalCounter=" + fatalCounter +
                ", warnings=" + warnings +
                ", fatalMsg=" + fatalMsgs +
                ", errors=" + errors +
                ", log=" + log +
                '}';
    }

    public Robo(String name) {
        log = new HashMap<String, List<String>>();
        log.put("WARN", warnings);
        log.put("ERROR", errors);
        log.put("FATAL", fatalMsgs);
        this.name = name;

    }

    public HashMap<String, List<String>> getLog() {
        return log;
    }

    public void setLog(HashMap<String, List<String>> log) {
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWarnCounter() {
        return warnCounter;
    }

    public void setWarnCounter(int warnCounter) {
        this.warnCounter = warnCounter;
    }

    public int getErrorCounter() {
        return errorCounter;
    }

    public void setErrorCounter(int errorCounter) {
        this.errorCounter = errorCounter;
    }

    public int getFatalCounter() {
        return fatalCounter;
    }

    public void setFatalCounter(int fatalCounter) {
        this.fatalCounter = fatalCounter;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public List<String> getFatalMsgs() {
        return fatalMsgs;
    }

    public void setFatalMsgs(List<String> fatalMsgs) {
        this.fatalMsgs = fatalMsgs;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
