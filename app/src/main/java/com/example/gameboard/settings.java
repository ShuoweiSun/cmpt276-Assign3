package com.example.gameboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class settings extends AppCompatActivity {
    public static Intent makeIntent(Context context){
        return new Intent(context,settings.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        createRadioButtons();
        createMineField();
        int savedValue=getNumPanelsInstalled(this);
        Toast.makeText(this,"save value "+ savedValue,Toast.LENGTH_SHORT)
                .show();
        int savedBoardSize=getNumRows(this);
        Toast.makeText(this,"saved row "+ savedBoardSize,Toast.LENGTH_SHORT)
                .show();
    }
    private void createMineField(){
        RadioGroup group=(RadioGroup)findViewById(R.id.radio_group_field);
        int[] boardSize =getResources().getIntArray(R.array.board_sizes);
        for(int i=0;i<(boardSize.length)/2;i++){
            final int numRow=boardSize[i];
            final int numCol=boardSize[i+3];

            RadioButton button=new RadioButton(this);
            button.setText("Board Size: "+numRow+" rows by "+numCol+" columns");

            //TODO: Set on-click callbacks
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Toast.makeText(settings.this,numRow+" Board Size Selected",Toast.LENGTH_SHORT).show();
                    saveNumRows(numRow);
                    saveNumCols(numCol);
                }
            });
            //Add to radio group:
            group.addView(button);
            // Select default button
            if(numRow==getNumRows(this)){
                button.setChecked(true);
            }
        }
    }
    private void createRadioButtons() {
        RadioGroup group=(RadioGroup) findViewById(R.id.radio_group_mines);

        int[] numMines=getResources().getIntArray(R.array.num_mines);

        for(int i=0;i<numMines.length;i++){
            final int numMine=numMines[i];

            RadioButton button=new RadioButton(this);
            button.setText(getString(R.string.NumofMines,numMine));

            //TODO: Set on-click callbacks
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Toast.makeText(settings.this,numMine+" Mines Selected",Toast.LENGTH_SHORT).show();
                    saveNumPanelsInstalled(numMine);
                }
            });
            //Add to radio group:
            group.addView(button);
            // Select default button
            if(numMine==getNumPanelsInstalled(this)){
                button.setChecked(true);
            }
        }
    }
    private void saveNumPanelsInstalled(int numPanel){
        SharedPreferences prefs=this.getSharedPreferences("AppPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt("Number of mines",numPanel);
        editor.apply();
    }
    static public int getNumPanelsInstalled(Context context){
        SharedPreferences prefs=context.getSharedPreferences("AppPrefs",MODE_PRIVATE);
        return prefs.getInt("Number of mines",0);
    }


    private void saveNumRows(int row){
        SharedPreferences prefs=this.getSharedPreferences("RowPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt("number of rows",row);
        editor.apply();
    }
    static public int getNumRows(Context context){
        SharedPreferences prefs=context.getSharedPreferences("RowPrefs",MODE_PRIVATE);
        return prefs.getInt("number of rows",0);
    }


    private void saveNumCols(int col){
        SharedPreferences prefs=this.getSharedPreferences("ColPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt("number of cols",col);
        editor.apply();
    }
    static public int getNumCols(Context context){
        SharedPreferences prefs=context.getSharedPreferences("ColPrefs",MODE_PRIVATE);
        return prefs.getInt("number of cols",0);
    }
}
