package com.example.gameboard.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.gameboard.R;

public class helpPage extends AppCompatActivity {
    public static Intent makeIntent(Context context){
        return new Intent(context,helpPage.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);
    }
}
