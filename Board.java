package chess;

import java.util.ArrayList;

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize white pieces
        board[0][0] = new Rook(Chess.Player.white);
        board[0][1] = new Knight(Chess.Player.white);
        board[0][2] = new Bishop(Chess.Player.white);
        board[0][3] = new Queen(Chess.Player.white);
        board[0][4] = new King(Chess.Player.white);
        board[0][5] = new Bishop(Chess.Player.white);
        board[0][6] = new Knight(Chess.Player.white);
        board[0][7] = new Rook(Chess.Player.white);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Chess.Player.white);
        }

        // Initialize black pieces
        board[7][0] = new Rook(Chess.Player.black);
        board[7][1] = new Knight(Chess.Player.black);
        board[7][2] = new Bishop(Chess.Player.black);
        board[7][3] = new Queen(Chess.Player.black);
        board[7][4] = new King(Chess.Player.black);
        board[7][5] = new Bishop(Chess.Player.black);
        board[7][6] = new Knight(Chess.Player.black);
        board[7][7] = new Rook(Chess.Player.black);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Chess.Player.black);
        }
    }

    public Piece getPiece(int rank, int file) {
        return board[rank][file];
    }

    public boolean makeMove(String from, String to, String promotionPiece, Chess.Player currentPlayer) {
        // Convert from and to positions to rank and file
        int fromRank = 8 - Character.getNumericValue(from.charAt(1));
        int fromFile = from.charAt(0) - 'a';
        int toRank = 8 - Character.getNumericValue(to.charAt(1));
        int toFile = to.charAt(0) - 'a';

        Piece piece = board[fromRank][fromFile];
        if (piece == null || piece.getPlayer() != currentPlayer) {
            return false;
        }

        Square fromSquare = new Square(fromRank, fromFile);
        Square toSquare = new Square(toRank, toFile);

        if (!piece.isValidMove(this, fromSquare, toSquare)) {
            return false;
        }

        // Handle promotion
        if (piece instanceof Pawn && (toRank == 0 || toRank == 7)) {
            switch (promotionPiece) {
                case "Q":
                    piece = new Queen(currentPlayer);
                    break;
                case "R":
                    piece = new Rook(currentPlayer);
                    break;
                case "B":
                    piece = new Bishop(currentPlayer);
                    break;
                case "N":
                    piece = new Knight(currentPlayer);
                    break;
                default:
                    return false;
            }
        }

        // Move the piece
        board[toRank][toFile] = piece;
        board[fromRank][fromFile] = null;
        piece.setPosition(toRank, toFile);

        return true;
    }

    public ArrayList<ReturnPiece> getAllPieces() {
        ArrayList<ReturnPiece> pieces = new ArrayList<>();
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece piece = board[rank][file];
                if (piece != null) {
                    pieces.add(piece.toReturnPiece());
                }
            }
        }
        return pieces;
    }

    public boolean isInCheck(Chess.Player player) {
        // Implement check logic
        return false;
    }

    public boolean isInCheckmate(Chess.Player player) {
        // Implement checkmate logic
        return false;
    }
}