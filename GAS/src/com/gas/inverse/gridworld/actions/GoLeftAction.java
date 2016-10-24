package com.gas.inverse.gridworld.actions;

import com.gas.framwork.Action;
import com.gas.framwork.Agent;
import com.gas.framwork.State;

/**
 * Created by Mao on 11/10/2016.
 */
public class GoLeftAction extends Action {
    public GoLeftAction(String name, int id) {
        super(name, id);
    }

    @Override
    public void act(Agent agent) {
        State agent_state = agent.observe();
        double x = agent_state.getFeature("x");
        double y = agent_state.getFeature("y");

        if (x > 0) {
            agent_state.setFeature("x", x - 1);
        } else {
            agent_state.setFeature("x", -1.0);
            agent_state.setFeature("y", -1.0);
        }
    }
}
