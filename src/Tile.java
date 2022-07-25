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
        private Piece occupiedPiece; // Create private reference to piece occupying tile
        /* Occupied Tile Constructor */
        public occupiedTile(int tileNum, Piece occupiedPiece)
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


