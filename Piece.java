import java.util.ArrayList;

/**
 * Interface for a piece which can be used for both chess and checkers pieces
 * @author Michael Chyziak (mchyziak@sfu.ca)
 * @version 1
 * @since 15-12-2014 (DD-MM-YYYY)
 */
public interface Piece {

	//Lets you know which color piece you are
	enum pieceColor {
		White, Black;
	}
	
	/**
	 * Get's the color that the piece is (what team it is aligned to)
	 * @return the enum of the pieceColor, either "White" or "Black"
	 */
	pieceColor getColor();
	
	/**
	 * Get's the opposite teams color that the piece is (what team the other piece it is aligned to)
	 * @return the enum of the pieceColor, either "White" or "Black"
	 */
	static pieceColor getOppositeColor(Piece.pieceColor color) {
		if (color == Piece.pieceColor.White) {
			return Piece.pieceColor.Black;
		}
		return Piece.pieceColor.White;
	}
	
	
	/**
	 * Moves the piece to the given location if it is allowed
	 * @param rowPosition the row to which the piece is moved to
	 * @param columnPosition the column to which the piece is moved to 
	 * @return the piece(s) to be destroyed (was jumped) in an arraylist of integers where the integer corresponds to the pieces location
	 * 		   null if no pieces jumped 
	 */
	ArrayList<Integer> move(int rowPosition, int columnPosition);
	
	/**
	 * Gets the row position of the piece
	 * @return the row position of the piece
	 */
	int getRowPosition();
	
	/**
	 * Gets the column position of the piece
	 * @return the column position of the piece
	 */
	int getColumnPosition();
	
	
	/**
	 * The pieces representation to be shown on the console version of the board
	 * @return the string of the piece
	 */
	String getPieceRepresentation();
	
	/**
	 * Get's the move list by the particular piece
	 * @return the move list for this piece, null if the move list is not updated yet
	 */
	MoveList getMoveList();
	
	
	/**
	 * Updates the move list
	 * @param myPieces an array list of all of the pieces belonging to the players team
	 * @param enemyPieces an array list of all of the pieces belonging to the enemies team
	 */
	void updateMoveList(ArrayList<Piece> myPieces, ArrayList<Piece> enemyPieces);
	
	
//	/**
//	 * Returns the last row that the piece is going towards
//	 * @return the integer of the last row that a piece is moving towards (to be kinged)
//	 */
//	public int getEndRow();
}
