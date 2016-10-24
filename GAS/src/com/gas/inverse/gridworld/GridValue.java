package com.gas.inverse.gridworld;

import com.gas.framwork.Action;
import com.gas.framwork.State;
import com.gas.framwork.Value;

/**
 * Created by Mao on 17/10/2016.
 */
public class GridValue implements Value {
    private double[][][] valueMatrix;     //[y][x][action]
    public GridValue(){
        valueMatrix = new double[4][4][4];
    }
    @Override
    public void updateValue(State state, Action action , double value) {
        int x = (int)state.getFeature("x");
        int y = (int)state.getFeature("y");
        int a_id = action.getId();
        valueMatrix[y][x][a_id] = value;
    }

    @Override
    public double getValue(State state, Action action) {
        int x = (int)state.getFeature("x");
        int y = (int)state.getFeature("y");
        if(x==-1 && y==-1){
            return 0;
        }
        int a_id = action.getId();
        //System.out.println("@@@     y="+y+"  x="+x+"  a="+a_id+"  $V="+valueMatrix[y][x][a_id]);
        return valueMatrix[y][x][a_id];
    }

    public void printQV(){
        for(int i =0;i<4;i++){
            System.out.print("\n============action: "+i+" ===============\n");
            for(int j=0;j<4;j++ ){
                for (int k=0;k<4;k++){
                    System.out.print(valueMatrix[j][k][i] +" ");
                }
                System.out.print("\n");
            }
        }

    }
}
