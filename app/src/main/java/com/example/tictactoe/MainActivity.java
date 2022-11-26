package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerComponents();
    }

    private void registerComponents() {
        ImageView slot11 = findViewById(R.id.slot11);
        slot11.setClickable(true);
        slot11.setOnClickListener((view -> {
            Toast.makeText(MainActivity.this, "Random Toast", Toast.LENGTH_SHORT).show();
        }));
    }
}