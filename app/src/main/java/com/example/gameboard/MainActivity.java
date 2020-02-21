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

import com.example.gameboard.gameLogic.MineBlock;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


        private static final int NUM_ROWS = 8;
        private static final int NUM_COLS = 5;
        Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
        MineBlock blocks[][]=new MineBlock[NUM_ROWS][NUM_COLS];
        int mines = 10;



        private boolean areMinesSet;
        // number of mines undiscovered
        private int minesToFind;
        boolean hasMine;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setupMineField();
            setMines();
            populateButtons();
        }

        private void populateButtons() {
            TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

            for (int row = 0; row < NUM_ROWS; row++) {
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT,
                        1.0f));
                table.addView(tableRow);

                for (int col = 0; col < NUM_COLS; col++){
                    final int FINAL_COL = col;
                    final int FINAL_ROW = row;

                    Button button = new Button(this);
                    button.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT,
                            1.0f));

                    //button.setText("" + col + "," + row);

                    // Make text not clip on small buttons
                    button.setPadding(0, 0, 0, 0);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(blocks[FINAL_ROW][FINAL_COL].hasMine()){
                            gridButtonClicked(FINAL_COL, FINAL_ROW);}
                        }
                    });

                    tableRow.addView(button);
                    buttons[row][col] = button;
                }
            }
        }

        //setting up a mine field that overlays onto the TableRow
        private void setupMineField() {

            for (int row = 0; row < NUM_ROWS; row++) {
                for (int col = 0; col < NUM_COLS; col++) {
                   blocks[row][col]=new MineBlock();
                   blocks[row][col].setDefault();

                }
            }
        }
        private void setDefault(){
            hasMine=false;
        }
        // setup random mines
        // Function Reference: https://www.codeproject.com/Articles/113892/Minesweeper-Minesweeper-game-for-Android
        private void setMines() {
        Random rand = new Random();
        int mineRow, mineColumn;
        for (int row = 0; row < this.mines; row++) {
            // generate random position
            mineRow = rand.nextInt(NUM_ROWS);
            mineColumn = rand.nextInt(NUM_COLS);

            if (blocks[mineRow][mineColumn].hasMine()) {
                    row--;
                }
            blocks[mineRow][mineColumn].plantMine();


        }
        int rowColMineCount;
        // count number of mines in surrounding blocks
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                // for each block find row and col mine count
                rowColMineCount = 0;

                // check in all nearby blocks
                for (int thisrow = row; thisrow < NUM_ROWS; thisrow++) {
                    if (blocks[thisrow][col].hasMine()) {
                        rowColMineCount++;
                    }
                }
                for (int thiscol = col; thiscol < NUM_COLS; thiscol++) {
                    if (blocks[row][thiscol].hasMine()) {
                        rowColMineCount++;
                    }
                }


                blocks[row][col].NumberOfMinesInRowCol(rowColMineCount);

            }
        }
    }

        private void gridButtonClicked ( int col, int row){
            Toast.makeText(this, "button" + col + "," + row, Toast.LENGTH_SHORT).show();
            Button button = buttons[row][col];
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
        private void lockButtonSizes () {
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
