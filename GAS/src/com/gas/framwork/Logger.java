package com.gas.framwork;

import java.util.List;

/**
 * Created by Mao on 10/10/2016.
 */
public abstract class Logger {
    private String name;

    protected Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void logTraj(State state);
    public abstract void newTraj();
    public abstract List<List<State>> endLog();
    public abstract void echo(State state, Action action);
}
