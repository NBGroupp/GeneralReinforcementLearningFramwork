package com.gas.framwork;

/**
 * Created by Mao on 10/10/2016.
 */
public abstract class Action {
    protected String name;
    protected int id;
    public Action(String name , int id){
        this.name = name;
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }

    abstract public void act(Agent agent);
}
