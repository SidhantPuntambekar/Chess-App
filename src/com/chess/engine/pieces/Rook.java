package com.chess.engine.pieces;

import com.chess.engine.Team;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece
{

    private static int[] CANDIDATE_MOVE_VECTORS = {-8, -1, 1, 8};

    public Rook(int piecePosition, Team pieceTeam) {
        super(piecePosition, pieceTeam);
    }

    @Override
    public Collection<Move> getLegalMoves(final Board board) {

        final List<Move> candidateLegalMoves = new ArrayList<>();

        for (final int candidateMoveValues : CANDIDATE_MOVE_VECTORS)
        {
            int candidateDest = this.getPosition();

            while (BoardUtils.isValidTile(candidateDest))
            {
                if (isFirstColException(candidateDest, candidateMoveValues) || isEightColException(candidateDest, candidateMoveValues))
                {
                    break;
                }

                candidateDest += candidateMoveValues;

                if (BoardUtils.isValidTile(candidateDest))
                {
                    final Tile candidateDestTile = board.getTile(candidateDest);
                    if (!candidateDestTile.isOccupied())
                    {
                        Move newLegalMove = new Move.MajorPieceMove(board, this, candidateDest);
                        candidateLegalMoves.add(newLegalMove);
                    }
                    else {
                        final Piece occupiedPiece = candidateDestTile.getPiece();
                        final Team occupiedPieceTeam = occupiedPiece.getTeam();

                        if (this.getTeam() != occupiedPieceTeam) {
                            // capture
                            Move newCaptureLegalMove = new Move.MoveAttack(board, this, occupiedPiece, candidateDest);
                            candidateLegalMoves.add(newCaptureLegalMove);
                        }
                        break; // Break the loop if the rook slides to a new enemy piece and stop
                    }
                }
            }
        }
        return ImmutableList.copyOf(candidateLegalMoves);
    }

    private static boolean isFirstColException(final int currentPos, int candidateOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPos] && (candidateOffset == -1);
    }

    private static boolean isEightColException(final int currentPos, int candidateOffset)
    {
        return BoardUtils.EIGHTH_COLUMN[currentPos] && (candidateOffset == 1);
    }
}
