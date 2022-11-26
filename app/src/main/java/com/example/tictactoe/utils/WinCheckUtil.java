package com.example.tictactoe.utils;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tictactoe.models.Win;
import com.example.tictactoe.models.WinOrientation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinCheckUtil {
    public static Win checkWin(List<List<Drawable>> board, Drawable empty) {
        return Stream.of(getRowWin(board, empty), getColumWin(board, empty),
                        getLtrSlashWin(board, empty), getRtlSlashWin(board, empty))
                .filter(Objects::nonNull).findFirst().orElse(null);
    }

    @Nullable
    private static Win getRowWin(List<List<Drawable>> board, Drawable empty) {
        for (int i = 0; i < board.size(); i++) {
            if (areAllEqual(board.get(i), empty)) return new Win(WinOrientation.ROW, i);
        }
        return null;
    }

    @Nullable
    private static Win getColumWin(List<List<Drawable>> board, Drawable empty) {
        AtomicInteger col = new AtomicInteger(0);
        for (int i = 0; i < board.get(0).size(); i++) {
            col.set(i);
            List<Drawable> column = board.stream().map(l -> l.get(col.get())).collect(Collectors.toList());
            if (areAllEqual(column, empty)) return new Win(WinOrientation.COLUMN, i);
        }
        return null;
    }

    @Nullable
    private static Win getLtrSlashWin(List<List<Drawable>> board, Drawable empty) {
        List<Drawable> ltrSlash = new ArrayList<>();
        for (int i = 0; i < board.get(0).size(); i++) {
            ltrSlash.add(board.get(i).get(i));
        }
        if (areAllEqual(ltrSlash, empty)) return new Win(WinOrientation.LTRSLASH, 0);
        return null;
    }

    @Nullable
    private static Win getRtlSlashWin(List<List<Drawable>> board, Drawable empty) {
        List<Drawable> rtlSlash = new ArrayList<>();
        for (int i = 0; i < board.get(0).size(); i++) {
            rtlSlash.add(board.get(i).get(board.get(i).size() - i - 1));
        }
        if (areAllEqual(rtlSlash, empty)) return new Win(WinOrientation.RTLSLASH, 0);
        return null;
    }

    private static boolean areAllEqual(List<Drawable> series, Drawable empty) {
        return !CompareUtil.drawablesEqual(series.get(0), empty) &&
                series.stream().allMatch(d -> CompareUtil.drawablesEqual(series.get(0), d));
    }
}
