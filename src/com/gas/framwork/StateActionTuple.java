package com.gas.framwork;

/**
 * Created by Mao on 17/10/2016.
 */
public class StateActionTuple {
    private State state;
    private Action action;
    public StateActionTuple(State state, Action action){
        this.state = state;
        this.action = action;
    }

    public Action getAction() {
        return action;
    }
    public State getState(){
        return state;    }
}