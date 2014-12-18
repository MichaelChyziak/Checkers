import java.util.ArrayList;

/**
 * Stores any possible moves that a piece can make
 * @author Michael Chyziak (mchyziak@sfu.ca)
 * @version 1
 * @since 15-12-2014 (DD-MM-YYYY)
 */
public interface MoveList {
	
	/**
	 * Checks if a move to be made is valid or not
	 * @param rowPosition the position we want to move to
	 * @param columnPosition the position we are at
	 * @return true if the move is valid, false otherwise
	 */
	boolean isValidMove(int rowPosition, int columnPosition);
	
	/**
	 * Returns the total number of valid moves a player can make
	 * @return the integer value of the total number of moves that are possible
	 */
	int getNumberOfValidMoves();
	
	
	/**
	 * Updates the move list based on the units current location
	 * @param currentRow the current row that the piece is in
	 * @param currentColumn the current column that the piece is in
	 * @param myPieces the players pieces
	 * @param enemyPieces the enemies pieces
	 */
	void updateMoveList(int currentRow, int currentColumn, ArrayList<Piece> myPieces, ArrayList<Piece> enemyPieces);
	
	/**
	 * Returns the move list
	 * @return the move list
	 */
	ArrayList<int[]> getMoveList();
	
	
	/**
	 * Returns the capture list
	 * @return the capture list
	 */
	public ArrayList<ArrayList<Integer>> getCaptureList();

}
