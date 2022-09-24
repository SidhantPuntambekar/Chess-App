package com.chess.engine.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = initCol(0);
    public static final boolean[] SECOND_COLUMN = initCol(1);
    public static final boolean[] SEVENTH_COLUMN = initCol(6);
    public static final boolean[] EIGHTH_COLUMN = initCol(7);

    public static final int NUM_TILES = 64;
    public static final int NUM_PER_ROW = 8;


    public BoardUtils()
    {
        throw new RuntimeException("BoardUtils is unable to be instantiated since it is a utility class.");
    }

    // Checks if tile the piece wants to move to is in the range 0-64 (actually on the board)
    public static boolean isValidTile(final int tileLocation)
    {
        if (tileLocation >= 0 && tileLocation <= NUM_TILES)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean[] initCol(int columnNumber)
    {
        final boolean[] column = new boolean[64];

        do {
            column[columnNumber] = true;
            columnNumber += 8; // Sets each consecutive square in column equal to true by skipping 8 squares
        } while(columnNumber < 64);
        return column;
    }
}
