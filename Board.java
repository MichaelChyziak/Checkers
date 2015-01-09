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
	public final static int piecesCheckers = 8;
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
	
	
	public Board(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {
		board = new String[boardHeight][boardWidth];
		this.whitePieces = new ArrayList<Piece>();
		this.blackPieces = new ArrayList<Piece>();
		for (int i = 0; i < whitePieces.size(); i++) {
			int row = whitePieces.get(i).getRowPosition();
			int column = whitePieces.get(i).getColumnPosition();
			this.whitePieces.add(new CheckersPawn(row, column, Piece.pieceColor.White));
		}
		for (int i = 0; i < blackPieces.size(); i++) {
			int row = blackPieces.get(i).getRowPosition();
			int column = blackPieces.get(i).getColumnPosition();
			this.blackPieces.add(new CheckersPawn(row, column, Piece.pieceColor.Black));
		}
		resetBoard();
	}
	
	
	/**
	 * Gives the type of pieces depending on what team you are alligned to
	 * @param color the team color the player is aligned to
	 * @return the array list of Pieces that are aligned to the appropriate team (either white or black pieces)
	 */
	public ArrayList<Piece> getTeamPieces(Piece.pieceColor color) {
		if (color == Piece.pieceColor.White) {
			return whitePieces;
		}
		else if (color == Piece.pieceColor.Black) {
			return blackPieces;
		}
		return null;
	}
	
	/**
	 * Gives the type of pieces of the enemy depending on what team you are alligned to
	 * @param color the team color the player is aligned to
	 * @return the array list of Pieces that are aligned to the enemy team (white if you are black, black if you are white)
	 */
	public ArrayList<Piece> getEnemyPieces(Piece.pieceColor color) {
		if (color == Piece.pieceColor.White) {
			return blackPieces;
		}
		else if (color == Piece.pieceColor.Black) {
			return whitePieces;
		}
		return null;
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
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				board[i][j] = " ";
			}
		}
	}
	
	
	
	/**
	 * Sets the default checkers board with all pieces horizontal from each other on the first 3 rows on their side. 
	 * White on bottom, black on top. 12 pieces each. All start with pawns
	 */
	public void setDefaultCheckersBoard() {
		
		
//		//Pieces put for testing only 1
//		whitePieces.add(new CheckersPawn(5, 0, Piece.pieceColor.White));
//		
//		blackPieces.add(new CheckersPawn(4, 1, Piece.pieceColor.Black));
//		blackPieces.add(new CheckersPawn(2, 3, Piece.pieceColor.Black));
//		blackPieces.add(new CheckersPawn(0, 1, Piece.pieceColor.Black));
//		blackPieces.add(new CheckersPawn(2, 6, Piece.pieceColor.Black));
		
		
		
//		//Pieces put for testing only 2
//		whitePieces.add(new CheckersPawn(5, 0, Piece.pieceColor.White));
//		
//		blackPieces.add(new CheckersPawn(3, 2, Piece.pieceColor.Black));
//		blackPieces.add(new CheckersPawn(0, 1, Piece.pieceColor.Black));
		
		//White pieces
		whitePieces.add(new CheckersPawn(7, 6, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(7, 4, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(7, 2, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(7, 0, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(6, 7, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(6, 5, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(6, 3, Piece.pieceColor.White));
		whitePieces.add(new CheckersPawn(6, 1, Piece.pieceColor.White));

		
		//Black pieces
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
			
//			//Debugging stuff
//			System.out.println("i:" + i);
//			System.out.println("size:" + blackPieces.size());
//			System.out.println("row:" + blackPieces.get(i).getRowPosition());	
//			System.out.println("column:" + blackPieces.get(i).getColumnPosition());
//			System.out.println(blackPieces.get(i).getPieceRepresentation());	
			
			board[blackPieces.get(i).getRowPosition()][blackPieces.get(i).getColumnPosition()] = blackPieces.get(i).getPieceRepresentation();	
		}
	}
	
	/**
	 * Removes a piece from the board
	 * @param pieceRow the row of the piece in the board
	 * @param pieceColumn the column of the piece to be removed
	 * @param color the color (team) the piece is
	 */
	public void removePiece(int pieceRow, int pieceColumn, Piece.pieceColor color) {
	//	System.out.println(pieceRow + ", " + pieceColumn); ~~~What piece should be removed
		if (color == Piece.pieceColor.White) {
			for (int i = 0; i < whitePieces.size(); i++) {
				if (whitePieces.get(i).getRowPosition() == pieceRow && whitePieces.get(i).getColumnPosition() == pieceColumn) {
					whitePieces.remove(i);
					break;
				}
			}
		}
		else {
			for (int i = 0; i < blackPieces.size(); i++) {
				if (blackPieces.get(i).getRowPosition() == pieceRow && blackPieces.get(i).getColumnPosition() == pieceColumn) {
					blackPieces.remove(i);
					break;
				}
			}
		}
	}
	
	
	
	/**
	 * Changes the piece from a pawn to a king
	 * @param pawnPiece the pawn piece to be changed
	 */
	public void kingPiece(CheckersPawn pawnPiece) {
		int row = pawnPiece.getRowPosition();
		int column = pawnPiece.getColumnPosition();
		Piece.pieceColor color = pawnPiece.getColor();
		removePiece(row, column, color);
		addPiece(new CheckersKing(row, column, color));
		
	}
	
	
	/**
	 * Adds a piece to a team and puts it on the board
	 * @param piece the piece to be added to a team and put on the board
	 */
	public void addPiece(Piece piece) {
		if (piece.getColor() == Piece.pieceColor.White) {
			whitePieces.add(piece);
		}
		else {
			blackPieces.add(piece);
		}
	}
	
	/**
	 * Returns the 2d string array of the board
	 * @return the 2d string array of the board
	 */
	public String[][] getBoard() {
		return board;
	}
	
}
