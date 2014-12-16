import java.util.ArrayList;

/**
 * Creates a chess/checkers board on the console
 * @author Michael Chyziak (mchyziak@sfu.ca)
 * @version 1
 * @since 15-12-2014 (DD-MM-YYYY)
 */
public class Board {

	public final static int boardWidth = 8;
	public final static int boardHeight = 8;
	public final static int piecesCheckers = 12;
	private String[][] board;
	private ArrayList<Piece> whitePieces;
	private ArrayList<Piece> blackPieces;
	
	
	/**
	 * Returns the black team's pieces
	 * @return the array list of pieces on the black team
	 */
	public ArrayList<Piece> getBlackPieces() {
		return blackPieces;
	}
	
	/**
	 * Returns the white team's pieces
	 * @return the array list of pieces on the white team
	 */
	public ArrayList<Piece> getWhitePieces() {
		return whitePieces;
	}
	
	
	/**
	 * Creates the default 8x8 chess/checkers board on the console with no pieces
	 */
	public Board() {
		board = new String[boardHeight][boardWidth];
		whitePieces = new ArrayList<Piece>();
		blackPieces = new ArrayList<Piece>();
		setDefaultCheckersBoard();
		resetBoard();
	}
	
	/**
	 * Resets the board to be empty
	 */
	private void resetBoard() {
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeight; j++) {
				board[i][j] = " ";
			}
		}
	}
	
	
	
	/**
	 * Sets the default checkers board with all pieces horizontal from each other on the first 3 rows on their side. 
	 * White on bottom, black on top. 12 pieces each. All start with pawns
	 */
	public void setDefaultCheckersBoard() {
		
		//White pieces
		whitePieces.add(new CheckersPawn(7, 6, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(7, 4, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(7, 2, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(7, 0, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(6, 7, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(6, 5, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(6, 3, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(6, 1, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(5, 6, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(5, 4, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(5, 2, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(5, 0, Piece.pieceColor.White));
		
		//Black pieces
		blackPieces.add(new CheckersPawn(2, 7, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(2, 5, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(2, 3, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(2, 1, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(1, 6, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(1, 4, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(1, 2, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(1, 0, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(0, 7, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(0, 5, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(0, 3, Piece.pieceColor.Black));
		blackPieces.add(new CheckersPawn(0, 1, Piece.pieceColor.Black));	
		
	}
	
	
	
	/**
	 * Get's the current board of the game
	 * @return a two dimensional String array of the current board of the game
	 */
	public void showBoard() {
		resetBoard();
		setPiecesOnBoard();
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeight; j++) {
				System.out.print("|" + board[i][j]);
			}
			System.out.println("|");
		}
	}
	
	
	/**
	 * Puts all of the players pieces on the game board
	 */
	private void setPiecesOnBoard() {
		for (int i = 0; i < whitePieces.size(); i++) {
			board[whitePieces.get(i).getRowPosition()][whitePieces.get(i).getColumnPosition()] = whitePieces.get(i).getPieceRepresentation();
		}
		for (int i = 0; i < blackPieces.size(); i++) {
			board[blackPieces.get(i).getRowPosition()][blackPieces.get(i).getColumnPosition()] = blackPieces.get(i).getPieceRepresentation();	
		}
	}
}
