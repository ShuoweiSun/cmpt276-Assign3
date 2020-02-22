package com.example.gameboard.gameLogic;

import android.content.Context;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.StringBufferInputStream;
import java.util.Random;
public class MineBlock extends AppCompatActivity {
    private boolean hasMine;
    private boolean mineDiscovered;
    private String text;


    private String numOfMinesRowCol;

    public void setDefault(){
        mineDiscovered=false;
        hasMine=false;
    }
    public void setMineIsFound(){
        hasMine=false;
    }
    public void NumberOfMinesInRowCol(int num){
        // TODO fix this
        numOfMinesRowCol=setNum(num);
    }
    public String setNum(int t){
        if(t!=0)
        {
            text=Integer.toString(t);
        }
        return text;
    }

    public boolean hasMine(){return hasMine;}
    public void plantMine(){hasMine=true;}
    public String getNumOfMinesRowCol() {
        return numOfMinesRowCol;
    }
    public boolean getMineStatus() {
        return mineDiscovered;
    }
    public void setMineDiscovered(){
        mineDiscovered=true;
    }

}
