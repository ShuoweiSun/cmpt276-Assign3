package com.example.gameboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gameboard.UI.Main_menu;

public class Welcome_screen extends AppCompatActivity {
    private static int SPLASH_TIMING=5000;
    boolean finish_checker=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        ImageView imageview=(ImageView)findViewById(R.id.torpedo);
        RotateAnimation rotateAnimation=new RotateAnimation(0,720, Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5F);
        rotateAnimation.setDuration(2000);
        imageview.startAnimation(rotateAnimation);
        Button btn=findViewById(R.id.skip_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish_checker=true;
                Intent intent=new Intent(Welcome_screen.this, Main_menu.class);
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
    //https://www.bing.com/images/search?view=detailV2&ccid=fwvLjaNm&id=92C837CDC11ABF2229DD3BBB9726E7949B342C4C&thid=OIP.fwvLjaNm9J8Fea8Y77h-7QHaFj&mediaurl=https%3a%2f%2fcdn.dribbble.com%2fusers%2f758950%2fscreenshots%2f4948819%2ftorpedo_dribbble.jpg&exph=600&expw=800&q=torpedo+image+cartoon&simid=607992559173698103&selectedIndex=2&ajaxhist=0
    public void rotateAnim(){


    }


}
