package com.example.gameboard.gameLogic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.gameboard.R;

public class WinMessageFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create the view to show
        View v= LayoutInflater.from(getActivity())
                .inflate(R.layout.game_win_message,null);

        // Create a buttpn Listener
        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TAG","you clicked the dialog");
            }
        };
        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Game message")
                .setView(v)
                .setPositiveButton(android.R.string.ok,listener)
                .create();
    }
}
