package com.gas.inverse.gridworld;

import com.gas.framwork.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mao on 11/10/2016.
 */
public class GridEnvironment extends Environment{
    public GridEnvironment(){
        this.agents_state = new LinkedList<State>();
    }
}
