package com.example.gameboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.gameboard.gameLogic.MineBlock;
import com.example.gameboard.gameLogic.WinMessageFragment;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


        private  int NUM_ROWS = 3;
        private  int NUM_COLS = 3;
        Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
        MineBlock blocks[][]=new MineBlock[NUM_ROWS][NUM_COLS];

        int mines = 0;
        int rowColMineCount;
        int numOfClicks=0;

    public static Intent makeIntent(Context context) {
        return new Intent(context,MainActivity.class);
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getUserSetting();
            setupMineField();
            setMines();
            populateButtons();
        }
        private void getUserSetting() {
            mines=settings.getNumPanelsInstalled(this);
            NUM_COLS=settings.getNumCols(this);
            NUM_ROWS=settings.getNumRows(this);
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
                                // perform a scan for empty block
                                if (!blocks[FINAL_ROW][FINAL_COL].hasMine()) {
                                    calculateMinesRowCol(FINAL_ROW,FINAL_COL);
                                    gridEmptyClicked(FINAL_COL, FINAL_ROW);
                                    blocks[FINAL_ROW][FINAL_COL].setBlockClicked();
                                }
                                // perform a scan for found mines
                                if(blocks[FINAL_ROW][FINAL_COL].hasMine()&&blocks[FINAL_ROW][FINAL_COL].getMineStatus()){
                                    gridEmptyClicked(FINAL_COL, FINAL_ROW);
                                }
                                // if a mine is found
                                if (blocks[FINAL_ROW][FINAL_COL].hasMine()&&!blocks[FINAL_ROW][FINAL_COL].getMineStatus()) {
                                    gridButtonClicked(FINAL_COL, FINAL_ROW);
                                    blocks[FINAL_ROW][FINAL_COL].setMineDiscovered();
                                }
                                // update all the scanned blocks
                                updateScans();


                                numOfClicks++;

                                if(gameWon()){
                                    FragmentManager manager=getSupportFragmentManager();
                                    WinMessageFragment dialog=new WinMessageFragment();
                                    dialog.show(manager,"WinMessage");
                                    Log.i("TAG","just show the win message");
                                }

                        }
                    });

                    tableRow.addView(button);
                    buttons[row][col] = button;
                }
            }
        }

    private void updateScans() {
        for (int row=0;row<NUM_ROWS;row++) {
            for(int col=0;col<NUM_COLS;col++){
                if(blocks[row][col].getBlockStatus()){
                    rowColMineCount = 0;
                    for (int thisRow = 0; thisRow < NUM_ROWS; thisRow++) {
                        if (blocks[thisRow][col].hasMine()&&!blocks[thisRow][col].getMineStatus()) {
                            rowColMineCount++;
                        }
                    }
                    // check total mine in the same row as the block
                    for (int thisCol = 0; thisCol < NUM_COLS; thisCol++) {
                        if (blocks[row][thisCol].hasMine()&&!blocks[row][thisCol].getMineStatus()) {
                            rowColMineCount++;
                        }

                    }
                    blocks[row][col].NumberOfMinesInRowCol(rowColMineCount);
                    gridEmptyClicked(col,row);
                }

            }
        }
    }


    private void gridEmptyClicked(int final_col, int final_row) {
        Button button=buttons[final_row][final_col];
        lockButtonSizes();
        String fileName="num"+blocks[final_row][final_col].getNumOfMinesRowCol();
        int id=getResources().getIdentifier(fileName,"drawable",MainActivity.this.getPackageName());
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),id);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));


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
        private void calculateMinesRowCol(int row,int col){
            rowColMineCount = 0;
            for (int thisRow = 0; thisRow < NUM_ROWS; thisRow++) {
                if (blocks[thisRow][col].hasMine()&&!blocks[thisRow][col].getMineStatus()) {
                    rowColMineCount++;
                }
            }
            // check total mine in the same row as the block
            for (int thisCol = 0; thisCol < NUM_COLS; thisCol++) {
                if (blocks[row][thisCol].hasMine()&&!blocks[row][thisCol].getMineStatus()) {
                    rowColMineCount++;
                }
            }
            blocks[row][col].NumberOfMinesInRowCol(rowColMineCount);
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
    }
        private boolean gameWon(){
            for (int row=0;row<NUM_ROWS;row++) {
                for(int col=0;col<NUM_COLS;col++){
                    if(blocks[row][col].hasMine()&&!blocks[row][col].getMineStatus()){
                        return false;
                    }
                }
            }
            return true;
    }

        private void gridButtonClicked ( int col, int row){
            //Toast.makeText(this, "button" + col + "," + row, Toast.LENGTH_SHORT).show();
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
