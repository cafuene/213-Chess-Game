package chess;

public abstract class Piece {

	protected Chess.Player player;
    protected int rank;
    protected int file;
    
    public Piece(Chess.Player player) {
        this.player = player;
    }
    
    public Chess.Player getPlayer() {
        return player;
    }
    
    public void setPosition(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public abstract ReturnPiece toReturnPiece();

    public abstract boolean isValidMove(Board board, Square from, Square to);
    
}
