package chess;

import java.util.ArrayList;

public class Chess {

        enum Player { white, black }
    
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		move = move.trim();
        ReturnPlay returnPlay = new ReturnPlay();
        
        // If game is over, return current state
        if (gameOver) {
            returnPlay.piecesOnBoard = board.getAllPieces();
            return returnPlay;
        }
        
        // Check for resignation
        if (move.equals("resign")) {
            gameOver = true;
            returnPlay.piecesOnBoard = board.getAllPieces();
            returnPlay.message = (currentPlayer == Player.white) ? 
                                 ReturnPlay.Message.RESIGN_BLACK_WINS : 
                                 ReturnPlay.Message.RESIGN_WHITE_WINS;
            return returnPlay;
        }
        
        // Check for draw offer
        boolean drawRequested = false;
        if (move.endsWith("draw?")) {
            drawRequested = true;
            move = move.substring(0, move.indexOf("draw?")).trim();
        }
        
        // Parse the move
        String[] parts;
        if (move.split(" ").length > 2) {
            // Likely a promotion move
            parts = move.split(" ");
        } else {
            parts = move.split(" ");
        }
        
        if (parts.length < 2) {
            // Invalid move format
            returnPlay.piecesOnBoard = Board.getAllPieces();
            returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return returnPlay;
        }
        
        String from = parts[0];
        String to = parts[1];
        
        // Check promotion piece
        String promotionPiece = "";
        if (parts.length > 2) {
            promotionPiece = parts[2];
        }
        
        // Check if move is valid
        boolean moveSuccessful = board.makeMove(from, to, promotionPiece, currentPlayer);
        
        if (!moveSuccessful) {
            returnPlay.piecesOnBoard = board.getAllPieces();
            returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
            return returnPlay;
        }
        
        // Move was successful
        lastMove = move;
        
        // Check for draw request
        if (drawRequested) {
            gameOver = true;
            returnPlay.piecesOnBoard = board.getAllPieces();
            returnPlay.message = ReturnPlay.Message.DRAW;
            return returnPlay;
        }
        
        // Check if the opponent is in check or checkmate
        Player opponent = (currentPlayer == Player.white) ? Player.black : Player.white;
        boolean isOpponentInCheck = board.isInCheck(opponent);
        boolean isOpponentInCheckmate = isOpponentInCheck && board.isInCheckmate(opponent);
        
        if (isOpponentInCheckmate) {
            gameOver = true;
            returnPlay.piecesOnBoard = board.getAllPieces();
            returnPlay.message = (currentPlayer == Player.white) ? 
                               ReturnPlay.Message.CHECKMATE_BLACK_WINS : 
                               ReturnPlay.Message.CHECKMATE_WHITE_WINS;
        } else if (isOpponentInCheck) {
            inCheck = true;
            returnPlay.piecesOnBoard = board.getAllPieces();
            returnPlay.message = ReturnPlay.Message.CHECK;
        } else {
            inCheck = false;
            returnPlay.piecesOnBoard = board.getAllPieces();
        }
        
        // Switch player turn
        if (!gameOver) {
            currentPlayer = (currentPlayer == Player.white) ? Player.black : Player.white;
        }
        
        
    }

		
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}

     
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {

		Board board = new Board();
        Player currentPlayer = Player.white;
        boolean gameOver = false;
        String lastMove = "";
        boolean inCheck = false;
        boolean inCheckmate = false;
		
	}
}
