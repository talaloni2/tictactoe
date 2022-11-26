package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Turn turn = Turn.X;
    private static final int FIRST_SLOT_ORDINAL = 1;
    private static final int LAST_SLOT_ORDINAL = 3;

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

    private void registerSlots() {
        for (int i = FIRST_SLOT_ORDINAL; i <= LAST_SLOT_ORDINAL; i++) {
            for (int j = FIRST_SLOT_ORDINAL; j <= LAST_SLOT_ORDINAL; j++) {
                ImageView slot = findViewById(getIdentifier("slot" + i + "" + j));
                slot.setImageResource(R.drawable.empty);
                slot.setOnClickListener(this::slotOnClick);
            }
        }
    }

    private void slotOnClick(View view) {
        if (turn == null) {
            return;
        }

        ImageView viewAsImage = (ImageView) view;
        Drawable emptyImage = ResourcesCompat.getDrawable(getResources(), R.drawable.empty, getTheme());
        if (!CompareUtil.drawablesEqual(viewAsImage.getDrawable(), emptyImage)){
            return;
        }
        viewAsImage.setImageResource(this.getTurnImage());
        Win w = WinCheckUtil.checkWin(getBoard(), emptyImage);
        this.changeTurn(w);
    }

    private List<List<Drawable>> getBoard() {
        List<List<Drawable>> board = new ArrayList<>();
        for (int i = FIRST_SLOT_ORDINAL; i <= LAST_SLOT_ORDINAL; i++) {
            board.add(new ArrayList<>());
            for (int j = FIRST_SLOT_ORDINAL; j <= LAST_SLOT_ORDINAL; j++) {
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

    private void changeTurn(Win win) {
        ImageView turnImage = findViewById(getIdentifier("turn"));

        if (changeTurnForWin(win, turnImage)) return;

        changeTurnForNonWin(turnImage);
    }

    private boolean changeTurnForWin(Win win, ImageView turnImage) {
        if (win != null) {
            if (this.turn == Turn.X)
                turnImage.setImageResource(R.drawable.xwin);
            else {
                turnImage.setImageResource(R.drawable.owin);
            }
            this.turn = null;
            return true;
        }
        return false;
    }

    private void changeTurnForNonWin(ImageView turnImage) {
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
        registerSlots();
        if (turn != Turn.X)
            this.changeTurn(null);
    }
}
