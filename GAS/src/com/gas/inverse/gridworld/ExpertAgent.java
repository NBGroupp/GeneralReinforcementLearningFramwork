package com.gas.inverse.gridworld;

import com.gas.framwork.*;

import java.util.Map;
import java.util.Random;

/**
 * Created by Mao on 11/10/2016.
 */
public class ExpertAgent extends Agent{
    public ExpertAgent(Environment env, RewardFunc reward, Logger log,Map<String, Action> actions){
        super(env,reward,log,actions);
    }
    @Override
    public State initialize() {
        Random random = new Random();
        int init_x = random.nextInt(4);
        int init_y = random.nextInt(4);
        State init_state = new GridState(init_x,init_y);
        return init_state;
    }

    @Override
    protected Action policy() {
        Random rand = new Random();
        int r = rand.nextInt(2);
        State s = this.observe();
        int x = (int)s.getFeature("x");
        int y = (int)s.getFeature("y");
        if (x == 3 && y == 0) {
            return null;
        } else if (x == 3 && y > 0) {
            return this.getAction("up");
        } else if (y == 0 && x < 3) {
            return this.getAction("right");
        } else if (x < 3 && y > 0) {
            if (r == 0) {
                return this.getAction("right");
            } else {
                return this.getAction("up");
            }
        } else {
            System.out.println("ERROR in action of Agent");
        }
        return null;
    }

    @Override
    protected boolean isEndState(State state) {
        if((int)state.getFeature("x")==-1 && (int)state.getFeature("y")==-1){
            return true;
        }else if ( (int)state.getFeature("x")==3 && (int)state.getFeature("y")==0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void update(State state_before_action, Action action) {
        // do nothing
    }
    protected void update() {
        // do nothing
    }
}
