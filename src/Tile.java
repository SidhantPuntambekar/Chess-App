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
