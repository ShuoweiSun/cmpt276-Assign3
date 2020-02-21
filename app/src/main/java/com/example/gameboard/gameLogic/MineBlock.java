package com.example.gameboard.gameLogic;

import android.content.Context;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.StringBufferInputStream;
import java.util.Random;
public class MineBlock extends AppCompatActivity {
    private boolean hasMine;
    private String text;




    private String numOfMinesRowCol;
    private boolean isFound;

    public void setDefault(){
        hasMine=false;
    }
    public String getNumOfMinesRowCol() {
        return numOfMinesRowCol;
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
}
