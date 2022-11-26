package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private enum Turn {
        X,
        O;
    }

    private Turn turn = Turn.X;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.assignComponents();
        this.startGame();
    }

    private void assignComponents() {
        Button play_again = findViewById(getIdentifier("play_again"));
        play_again.setOnClickListener(view -> this.startGame());
    }

    private void registerComponents() {
        registerSlots();
    }

    private void registerSlots() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                ImageView slot = findViewById(getIdentifier("slot" + i + "" + j));
                slot.setImageResource(R.drawable.empty);
                slot.setOnClickListener(this::slotOnClick);
            }
        }
    }

    private void slotOnClick(View view) {
        ((ImageView) view).setImageResource(this.getTurnImage());
        this.changeTurn();
    }

    private int getTurnImage() {
        if (this.turn == Turn.X)
            return R.drawable.x;
        return R.drawable.o;
    }

    private void changeTurn() {
        ImageView turnImage = findViewById(getIdentifier("turn"));
        if (this.turn == Turn.X) {
            this.turn = Turn.O;
            turnImage.setImageResource(R.drawable.oplay);
        } else {
            this.turn = Turn.X;
            turnImage.setImageResource(R.drawable.xplay);
        }
    }

    private int getIdentifier(String id) {
        return getResources().getIdentifier(id, "id", "com.example.tictactoe");
    }

    private void startGame() {
        registerComponents();
        if (turn != Turn.X)
            this.changeTurn();
    }
}
