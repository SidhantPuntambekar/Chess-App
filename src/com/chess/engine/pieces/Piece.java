package com.chess.engine.pieces;

import com.chess.engine.Team;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;
import java.util.List;

public abstract class Piece {

    protected final int position;
    protected final Team team;

    public Piece(final int piecePosition, final Team pieceTeam)
    {
        position = piecePosition;
        team = pieceTeam;
    }

    public int getPosition()
    {
        return position;
    }

    public Team getTeam()
    {
        return team;
    }

    public abstract Collection<Move> getLegalMoves(final Board board);
}
