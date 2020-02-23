package com.example.gameboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setupSettingButton();
        setupPlayButton();
    }




    private void setupSettingButton() {
        Button btn=(Button)findViewById(R.id.setting_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=settings.makeIntent(Main_menu.this);
                startActivity(intent);
            }
        });
    }
    private void setupPlayButton() {
        Button btn=(Button)findViewById(R.id.play_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=MainActivity.makeIntent(Main_menu.this);
                startActivity(intent);
            }
        });
    }



}
