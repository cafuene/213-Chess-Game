package chess;

public class Queen extends Piece {

    public Queen(Chess.Player player) {
        super(player);
    }

    @Override
    public boolean isValidMove(Board board, Square from, Square to) {
        int rankDiff = Math.abs(to.rank - from.rank);
        int fileDiff = Math.abs(to.file - from.file);
        
        // Queen can move like a rook (horizontally or vertically)
        if (from.rank == to.rank || from.file == to.file) {
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
        }
        // Check the destination square - either empty or contains an opponent's piece
        Piece targetPiece = board.getPiece(to.rank, to.file);
        if (targetPiece != null && targetPiece.getPlayer() == player) {
            return false;
        }
                return false;
    }    

    @Override
    public ReturnPiece toReturnPiece() {
        ReturnPiece rp = new ReturnPiece();
        rp.pieceType = (player == Chess.Player.white) ? 
                       ReturnPiece.PieceType.WQ : ReturnPiece.PieceType.BQ;
        rp.pieceFile = ReturnPiece.PieceFile.values()[file];
        rp.pieceRank = rank + 1;
        return rp;
    }

    
}
