public abstract class Tile {
    private int tileNum; // Each tile (64 in total), represented by an identifying number

    /* Tile Constructor */
    public Tile(int tileNum)
    {
        this.tileNum = tileNum;
    }

    /* Public abstract method to determine if tile is occupied by a piece */
    public abstract boolean isOccupied();

    /* Public abstract method to determine if tile is occupied by a piece */
    public abstract Piece getPiece();
}

/* Empty tile static class */
public final class emptyTile extends Tile {
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

