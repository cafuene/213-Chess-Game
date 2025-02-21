package chess;

public class Rook extends Piece{

	public Rook(Chess.Player player) {
        super(player);
    }
    
    @Override
    public boolean isValidMove(Board board, Square from, Square to) {
        // Rooks can only move horizontally or vertically
        if (from.rank != to.rank && from.file != to.file) {
            return false;
        }
        
        int startRank = from.rank;
        int startFile = from.file;
        int endRank = to.rank;
        int endFile = to.file;
        
        // Check for obstacles in the path
        if (from.rank == to.rank) {
            // Horizontal movement
            int dir = (to.file > from.file) ? 1 : -1;
            for (int f = from.file + dir; f != to.file; f += dir) {
                if (board.getPiece(from.rank, f) != null) {
                    return false;
                }
            }
        } else {
            // Vertical movement
            int dir = (to.rank > from.rank) ? 1 : -1;
            for (int r = from.rank + dir; r != to.rank; r += dir) {
                if (board.getPiece(r, from.file) != null) {
                    return false;
                }
            }
        }
        
        // Check the destination square - either empty or contains an opponent's piece
        Piece targetPiece = board.getPiece(to.rank, to.file);
        return targetPiece == null || targetPiece.getPlayer() != player;
    }
    
    @Override
    public ReturnPiece toReturnPiece() {
        ReturnPiece rp = new ReturnPiece();
        rp.pieceType = (player == Chess.Player.white) ? 
                       ReturnPiece.PieceType.WR : ReturnPiece.PieceType.BR;
        rp.pieceFile = ReturnPiece.PieceFile.values()[file];
        rp.pieceRank = rank + 1;
        return rp;
    }

}
