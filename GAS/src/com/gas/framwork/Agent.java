package com.gas.framwork;

/**
 * Created by Mao on 10/10/2016.
 */

import java.util.List;
import java.util.Map;

public abstract class Agent {
    Environment env;
    RewardFunc reward; //agent 的主观认识，反应agent的愿望
    int id;
    protected Map<String, Action> actions;
    Logger log;

    public Agent(Environment env, RewardFunc reward, Logger log,Map<String, Action> actions) {
        this.env = env;
        this.reward = reward;
        this.log = log;
        this.actions = actions;
        State agent_init_state = initialize();
        id = env.addAgent(agent_init_state);
    }

    public State observe() {
        return env.getState(id);
    }

    abstract protected State initialize();
    abstract protected Action policy();   //action_id
    abstract protected boolean isEndState(State state);
    abstract protected void update(State state_before_action,Action action);
    public void addAction(String name, Action action){
        actions.put(name,action);
    }

    public void delAction(String name){
        actions.remove(name);
    }

    public Action getAction(String name){
        return actions.get(name);
    }

    public List<List<State>> runTrajectory(int times){
        for(int i=0; i<times;i++){
            env.setAgentsState(id,initialize());
            Action action = null;
            while (!isEndState(observe())){
                action = policy();
                log.logTraj(observe().clone());
                State state_before_act = observe().clone();
                action.act(this);
                update(state_before_act , action);
            }
            log.logTraj(observe());
            log.newTraj();
        }

        return log.endLog();
    }

    public  Environment getEnvironment(){
        return this.env;
    }

}
