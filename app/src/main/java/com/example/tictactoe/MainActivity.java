package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private enum Turn {
        X,
        O;
    }

    private Turn turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerComponents();
        startGame();
    }

    private void registerComponents() {
        registerSlots();
    }

    private void registerSlots() {
        Drawable emptyImage = ResourcesCompat.getDrawable(getResources(), R.drawable.empty, getTheme());
        if (emptyImage == null) {
            return;
        }
        for(int i = 1; i <=3; i++) {
            for(int j = 1; j <=3; j++){
                ImageView slot = findViewById(getIdentifier("slot"+i+""+j));
                slot.setClickable(true);
                slot.setOnClickListener(this::slotOnClick);
            }
        }
    }

    private void slotOnClick(View view) {
        Toast.makeText(MainActivity.this, "Random Toast", Toast.LENGTH_SHORT).show();
    }

    private int getIdentifier(String id) {
        return getResources().getIdentifier(id, "id", "com.example.tictactoe");
    }

    private void startGame() {
        turn = Turn.X;
    }
}