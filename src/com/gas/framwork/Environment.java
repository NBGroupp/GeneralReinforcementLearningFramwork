package com.gas.framwork;

import java.util.Map;
import java.util.List;
/**
 * Created by Mao on 10/10/2016.
 */
public abstract class Environment {

    protected List<State> agents_state;

    protected Environment(){
    }

    public State getState(int agent_id) {
        return agents_state.get(agent_id);
    }
    public int addAgent(State agent_init_state){
        agents_state.add(agent_init_state);
        return agents_state.indexOf(agent_init_state);
    }
    public void setAgentsState(int agent_id, State agentState){
        agents_state.set(agent_id,agentState);
    }
}
