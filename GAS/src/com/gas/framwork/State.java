package com.gas.framwork;

/**
 * Created by Mao on 10/10/2016.
 */
import java.util.Map;
import java.util.HashMap;
public abstract class State {
    protected Map<String,Double> features;
    public State(){
        this.features = new HashMap<String,Double>();
    }

    abstract public double getFeature(String fName);
    abstract public void setFeature(String fName, Double fValue);
    abstract public State clone();
}
