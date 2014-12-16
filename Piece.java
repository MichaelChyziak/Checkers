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
	 * Moves the piece to the given location if it is allowed
	 * @param rowPosition the row to which the piece is moved to
	 * @param columnPosition the column to which the piece is moved to 
	 * @param whitePieces an array list of all of the pieces belonging to the white team
	 * @param blackPieces an array list of all of the pieces belonging to the black team
	 * @return false if the move is not allowed and the piece won't move, true if it is allowed and the piece will move to that position
	 */
	boolean move(int rowPosition, int columnPosition, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces);
	
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
	
}
