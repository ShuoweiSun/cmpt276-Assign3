package com.example.gameboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        createRadioButtons();
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
                }
            });
            //Add to radio group:
            group.addView(button);
        }
    }
}
