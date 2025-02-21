package chess;

public class King extends Piece{

    public King(Chess.Player player) {
        super(player);
    }
    
    @Override
    public boolean isValidMove(Board board, Square from, Square to) {
        // Calculate differences
        int rankDiff = Math.abs(to.rank - from.rank);
        int fileDiff = Math.abs(to.file - from.file);
        
        // King can move one square in any direction
        return (rankDiff <= 1 && fileDiff <= 1);
    }
    
    @Override
    public ReturnPiece toReturnPiece() {
        ReturnPiece rp = new ReturnPiece();
        rp.pieceType = (player == Chess.Player.white) ? 
                       ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK;
        rp.pieceFile = ReturnPiece.PieceFile.values()[file];
        rp.pieceRank = rank + 1;
        return rp;
    }

    
    
}
