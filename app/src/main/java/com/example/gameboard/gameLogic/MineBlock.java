package com.example.gameboard.gameLogic;

import android.content.Context;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.StringBufferInputStream;
import java.util.Random;
public class MineBlock extends AppCompatActivity {
    private boolean hasMine;
    private boolean mineDiscovered;
    private boolean isClicked;
    private String text;

    private String numOfMinesRowCol;
    private int numOfMineRowCol_int;

    public void setDefault(){
        mineDiscovered=false;
        hasMine=false;
        isClicked=false;
    }
    //public void setMineIsFound(){
      //  hasMine=false;
   // }
    public void setBlockClicked(){
        isClicked=true;
    }

    public void NumberOfMinesInRowCol(int num){
        // TODO fix this
        numOfMinesRowCol=setNum(num);
        numOfMineRowCol_int=num;
    }
    //TODO delete this
    public String setNum(int t){

        text=Integer.toString(t);
        return text;
    }

    public boolean hasMine(){return hasMine;}
    public void plantMine(){hasMine=true;}
    public void setMineDiscovered(){
        mineDiscovered=true;
    }
    public String getNumOfMinesRowCol() {
        return numOfMinesRowCol;
    }
    public boolean getMineStatus() {
        return mineDiscovered;
    }

    public int getNumOfMineRowCol_int(){
        return numOfMineRowCol_int;
    }
    public boolean getBlockStatus(){
        return isClicked;
    }

}
