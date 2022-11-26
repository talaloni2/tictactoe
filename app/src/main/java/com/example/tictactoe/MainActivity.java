package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tictactoe.models.Win;
import com.example.tictactoe.utils.CompareUtil;
import com.example.tictactoe.utils.WinCheckUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private enum Turn {
        X,
        O
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
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                ImageView slot = findViewById(getIdentifier("slot" + i + "" + j));
                slot.setOnClickListener(this::slotOnClick);
            }
        }
    }

    private void slotOnClick(View view) {
        ImageView viewAsImage = (ImageView) view;
        Drawable emptyImage = ResourcesCompat.getDrawable(getResources(), R.drawable.empty, getTheme());
        if (!CompareUtil.drawablesEqual(viewAsImage.getDrawable(), emptyImage)){
            return;
        }
        viewAsImage.setImageResource(this.getTurnImage());
        Win w = WinCheckUtil.checkWin(getBoard(), emptyImage);
        if (w != null) {
            Toast.makeText(this, turn.name() + " WINS!!!", Toast.LENGTH_SHORT).show();
        }
        this.changeTurn();
    }

    private List<List<Drawable>> getBoard() {
        List<List<Drawable>> board = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            board.add(new ArrayList<>());
            for (int j = 1; j <= 3; j++) {
                ImageView slot = findViewById(getIdentifier("slot" + i + "" + j));
                board.get(i - 1).add(slot.getDrawable());
            }
        }
        return board;
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
        turn = Turn.X;
    }
}