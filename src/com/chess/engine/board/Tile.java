package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    private int tileNum; // Each tile (64 in total), represented by an identifying number
    private static final Map<Integer, emptyTile> emptyTileMap = createEmptyTiles(); // Create map that tracks all empty tiles

    /* Tile Constructor */
    public Tile(int tileNum)
    {
        this.tileNum = tileNum;
    }

    /* Getter for tileNum */
    public int getTileNum() { return tileNum; }

    /* Setter for tileNum */
    public void setTileNum(int newTileNum) { tileNum = newTileNum; }

    /* Create Map between each tile number and the tile itself for tracking purposes */
    private static Map<Integer, emptyTile> createEmptyTiles() {
        final Map<Integer, emptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < BoardUtils.NUM_TILES; i++)
        {
            emptyTileMap.put(i, new emptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap); // Immutable map from Guava library
    }

    /* Create Tile Method */
    public static Tile createTile(final int tileNum, final Piece piece)
    {
        if (piece != null)
        {
            return new occupiedTile(tileNum, piece);
        }
        else
        {
            return emptyTileMap.get(tileNum);
        }
    }

    /* Public abstract method to determine if tile is occupied by a piece */
    public abstract boolean isOccupied();

    /* Public abstract method to determine if tile is occupied by a piece */
    public abstract Piece getPiece();

    /* Empty tile class */
    public static final class emptyTile extends Tile
    {
        /* Empty Tile Constructor */
        public emptyTile(int tileNum) {
            super(tileNum);
        }

        /* isOccupied override method: Tile is unoccupied */
        @Override
        public boolean isOccupied() {
            return false;
        }

        /* getPiece override method: No piece on empty tile */
        @Override
        public Piece getPiece() {
            return null;
        }
    }

    /* Occupied tile class */
    public static final class occupiedTile extends Tile
    {
        private final Piece occupiedPiece; // Create private reference to piece occupying tile. Immutable to ensure same piece is being moved
        /* Occupied Tile Constructor */
        public occupiedTile(int tileNum, final Piece occupiedPiece)
        {
            super(tileNum);
            this.occupiedPiece = occupiedPiece;
        }

        /* isOccupied override method: Tile is occupied */
        @Override
        public boolean isOccupied() {
            return true;
        }

        /* getPiece override method: No piece on empty tile */
        @Override
        public Piece getPiece() {
            return this.occupiedPiece;
        }
    }
}


