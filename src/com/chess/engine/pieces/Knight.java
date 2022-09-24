package com.chess.engine.pieces;

import com.chess.engine.Team;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    private static ArrayList<Integer> candidateMovePossibilities = new ArrayList<Integer>() {
        {
            add(-17);
            add(-15);
            add(-10);
            add(-6);
            add(6);
            add(10);
            add(15);
            add(17);
        }
    }; // Possible knight moves from current square

    public Knight(int piecePosition, Team pieceTeam) {
        super(piecePosition, pieceTeam);
    }

    private static boolean isFirstColException(int currentPosition, int candidatePosition)
    {
       return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -17 || candidatePosition == -10 || candidatePosition == 6 || candidatePosition == 15);
    }

    private static boolean isSecondColException(int currentPosition, int candidatePosition)
    {
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidatePosition == -10 || candidatePosition == 6);
    }

    private static boolean isSeventhColException(int currentPosition, int candidatePosition)
    {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidatePosition == 10 || candidatePosition == -6);
    }

    private static boolean isEightColumnException(int currentPosition, int candidatePosition)
    {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidatePosition == 17 || candidatePosition == 10 || candidatePosition == -6 || candidatePosition == -15);
    }

    @Override
    public Collection<Move> getLegalMoves(final Board board) {

        final ArrayList<Move> candidateLegalMoves = new ArrayList<Move>();

        for (int i : candidateMovePossibilities)
        {
            final int candidateDest = this.getPosition() + i;
            if (BoardUtils.isValidTile(candidateDest))
            {
                if (isFirstColException(this.position, i) || isSecondColException(this.position, i) || isSeventhColException(this.position, i) || isEightColumnException(this.position, i))
                {
                    continue;
                }

                final Tile candidateDestTile = board.getTile(candidateDest);
                if (!candidateDestTile.isOccupied())
                {
                    Move newLegalMove = new Move.MajorPieceMove(board, this, candidateDest);
                    candidateLegalMoves.add(newLegalMove);
                }
                else
                {
                    final Piece occupiedPiece = candidateDestTile.getPiece();
                    final Team occupiedPieceTeam = occupiedPiece.getTeam();

                    if (this.getTeam() != occupiedPieceTeam)
                    {
                        // capture
                        Move newCaptureLegalMove = new Move.MoveAttack(board, this, occupiedPiece, candidateDest);
                        candidateLegalMoves.add(newCaptureLegalMove);
                    }
                }
            }
        }
        return ImmutableList.copyOf(candidateLegalMoves);
    }
}
