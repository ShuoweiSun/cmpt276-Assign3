package com.example.gameboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Random;
import java.util.RandomAccess;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 2;
    int mines=10;

    private boolean areMinesSet;
    // number of mines undiscovered
    private int minesToFind;
    Button buttons[][]=new Button[NUM_ROWS][NUM_COLS];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateButtons();
    }
    private void populateButtons(){
        TableLayout table=(TableLayout)findViewById((R.id.tableForButtons));
        for (int row = 0; row< NUM_ROWS; row++){
            TableRow tableRow=new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for (int col=0; col<NUM_COLS;col++){
                final int FINAL_COL=col;
                final int FINAL_ROW=row;
                Button button=new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                        ));
                button.setText(""+col+","+row);

                //make text not clip on small buttons
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL,FINAL_ROW);
                    }
                });
                tableRow.addView(button);
                buttons[row][col]=button;
            }
        }

    }
    private void setMines(){
        int x;
        int y;
        for(int i=0;i<this.mines;i++){
            do{
                Random r= new Random();
                x= r.nextInt(this.NUM_COLS-1);
                y=r.nextInt(this.NUM_ROWS-1);
            }while(this.buttons[x][y]._state=);
        }
    }

    private void gridButtonClicked(int col,int row) {
        Toast.makeText(this,"button"+col+","+row,Toast.LENGTH_SHORT).show();
        Button button=buttons[row][col];
        // lock button sizes;
        lockButtonSizes();
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.mines);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }
    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
}
