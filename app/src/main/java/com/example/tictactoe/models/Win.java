package com.example.tictactoe.models;

public class Win {
    public WinOrientation orientation;
    public int orientationOrdinal;

    public Win(WinOrientation orientation, int orientationOrdinal) {
        this.orientation = orientation;
        this.orientationOrdinal = orientationOrdinal;
    }
}
