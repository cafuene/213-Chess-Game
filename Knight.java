package chess;

public class Knight extends Piece{

    public Knight(Chess.Player player) {
        super(player);
    }

    @Override
    public boolean isValidMove(Board board, Square from, Square to) {
        int rankDiff = Math.abs(to.rank - from.rank);
        int fileDiff = Math.abs(to.file - from.file);
        
        // Knight can move in an L-shape
        return (rankDiff == 2 && fileDiff == 1) || (rankDiff == 1 && fileDiff == 2);
    }

    @Override
    public ReturnPiece toReturnPiece() {
        ReturnPiece rp = new ReturnPiece();
        rp.pieceType = (player == Chess.Player.white) ? 
                       ReturnPiece.PieceType.WN : ReturnPiece.PieceType.BN;
        rp.pieceFile = ReturnPiece.PieceFile.values()[file];
        rp.pieceRank = rank + 1;
        return rp;
    }

    
    
}
