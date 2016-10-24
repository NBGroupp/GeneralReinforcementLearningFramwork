package com.gas.inverse.gridworld;

import com.gas.framwork.Action;
import com.gas.framwork.Logger;
import com.gas.framwork.State;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mao on 12/10/2016.
 */
public class GridLogger extends Logger{

    List<State> traj;
    List<List<State>> history;
    public GridLogger(String name){
        super(name);
        traj = new LinkedList<State>();
        history = new LinkedList<List<State>>();
    }

    public void logTraj(State state){
        traj.add(state);
    }
    public void newTraj(){
        history.add(traj);
        traj = new LinkedList<State>();
    }
    public List<List<State>> endLog(){
        return history;
    }
    public void echo(State state, Action action) {

         System.out.print("<"+(int)state.getFeature("x")+","+(int)state.getFeature("y")+","+">");
    }
    public void logTranslate(State state, int action_id){

    }
}
