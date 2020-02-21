package com.example.gameboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Welcome_screen extends AppCompatActivity {
    private static int SPLASH_TIMING=5000;
    boolean finish_checker=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button btn=findViewById(R.id.skip_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish_checker=true;
                Intent intent=new Intent(Welcome_screen.this,Main_menu.class);
                startActivity(intent);
                finish();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Welcome_screen.this, Main_menu.class);
                startActivity(mainIntent);
                finish();
            }

        },SPLASH_TIMING);
        //TODO stop the handler if the button is pushed.



    }


}
