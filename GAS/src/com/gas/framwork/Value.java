package com.gas.framwork;

/**
 * Created by Mao on 11/10/2016.
 */
public interface Value {
    void updateValue(State state, Action action,double value);
    double getValue(State state, Action action);
}
