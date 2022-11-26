package com.example.tictactoe.utils;

import android.graphics.drawable.Drawable;

public class CompareUtil {
    public static boolean drawablesEqual(Drawable a, Drawable b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        return a.getConstantState().equals(b.getConstantState());
    }
}
