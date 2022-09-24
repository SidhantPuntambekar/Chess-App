package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece piece;
    final int tileMove;

    public Move(final Board board, final Piece piece, final int tileMove)
    {
        this.board = board;
        this.piece = piece;
        this.tileMove = tileMove;
    }

    public static final class MajorPieceMove extends Move
    {
        public MajorPieceMove(Board board, Piece piece, int tileMove) {
            super(board, piece, tileMove);
        }
    }

    public static final class MoveAttack extends Move
    {
        final Piece attackedPiece;

        public MoveAttack(final Board board, final Piece piece, final Piece attackedPiece, final int tileMove)
        {
            super(board, piece, tileMove);
            this.attackedPiece = attackedPiece;
        }
    }

}
