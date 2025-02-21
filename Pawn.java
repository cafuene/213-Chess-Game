package chess;

public class Pawn extends Piece{

    public Pawn(Chess.Player player) {
        super(player);
    }
    
    @Override
    public boolean isValidMove(Board board, Square from, Square to) {
        // Direction of movement (1 for white, -1 for black)
        int direction = (player == Chess.Player.white) ? 1 : -1;
        
        // Calculate differences
        int rankDiff = to.rank - from.rank;
        int fileDiff = to.file - from.file;
        
        // Forward movement (no capture)
        if (fileDiff == 0) {
            // Single square forward
            if (rankDiff == direction) {
                return board.getPiece(to.rank, to.file) == null;
            }
            
            // Double square forward (from starting position)
            if ((rankDiff == 2 * direction) && 
                ((player == Chess.Player.white && from.rank == 1) || 
                 (player == Chess.Player.black && from.rank == 6))) {
                // Check that both squares are empty
                int middleRank = from.rank + direction;
                return board.getPiece(to.rank, to.file) == null && 
                       board.getPiece(middleRank, from.file) == null;
            }
        }
        
        // Capture diagonally (including en passant)
        if (Math.abs(fileDiff) == 1 && rankDiff == direction) {
            // Regular capture
            Piece targetPiece = board.getPiece(to.rank, to.file);
            if (targetPiece != null && targetPiece.getPlayer() != player) {
                return true;
            }
            
            // En passant - handled in Board.makeMove
            return true;
        }
        
        return false;
    }
    
    @Override
    public ReturnPiece toReturnPiece() {
        ReturnPiece rp = new ReturnPiece();
        rp.pieceType = (player == Chess.Player.white) ? 
                       ReturnPiece.PieceType.WP : ReturnPiece.PieceType.BP;
        rp.pieceFile = ReturnPiece.PieceFile.values()[file];
        rp.pieceRank = rank + 1;
        return rp;
    }

    
        


    
}
