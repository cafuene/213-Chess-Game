package chess;

public class Bishop extends Piece{

    public Bishop(Chess.Player player) {
        super(player);
    }

    @Override
    public boolean isValidMove(Board board, Square from, Square to) {
        int rankDiff = Math.abs(to.rank - from.rank);
        int fileDiff = Math.abs(to.file - from.file);
        
        // Bishop can move diagonally
        return rankDiff == fileDiff;
    }

    @Override
    public ReturnPiece toReturnPiece() {
        ReturnPiece rp = new ReturnPiece();
        rp.pieceType = (player == Chess.Player.white) ? 
                       ReturnPiece.PieceType.WB : ReturnPiece.PieceType.BB;
        rp.pieceFile = ReturnPiece.PieceFile.values()[file];
        rp.pieceRank = rank + 1;
        return rp;
    }

    
    
}
