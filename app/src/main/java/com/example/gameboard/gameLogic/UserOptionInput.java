package com.example.gameboard.gameLogic;

import com.example.gameboard.UI.settings;

public class UserOptionInput {
    private int numRows;
    private int numCols;
    private int numMines;

    //Singleton
    private static UserOptionInput instance;
    private  UserOptionInput(){}
    public static UserOptionInput getInstance(){
        if(instance==null){
            instance=new UserOptionInput();
        }
        return instance;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getMumCols() {
        return numCols;
    }

    public void setMumCols(int mumCols) {
        this.numCols = mumCols;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }
}
