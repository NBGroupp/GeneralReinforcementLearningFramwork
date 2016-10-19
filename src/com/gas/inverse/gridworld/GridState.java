package com.gas.inverse.gridworld;

import com.gas.framwork.State;

/**
 * Created by Mao on 11/10/2016.
 */
public class GridState extends State {

    public GridState(double x, double y){
        super();
        this.features.put("x",x);
        this.features.put("y",y);
    }

    @Override
    public double getFeature(String fName) {
      return this.features.get(fName);
    }

    @Override
    public void setFeature(String fName, Double fValue) {
        features.put(fName,fValue);
    }
    @Override
    public State clone() {
        return new GridState(getFeature("x"),getFeature("y"));
    }
}
