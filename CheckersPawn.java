import java.util.ArrayList;

/**
 * Creates the Pawn for the game of checkers and all of its characteristics
 * @author Michael Chyziak (mchyziak@sfu.ca)
 * @version 1
 * @since 15-12-2014 (DD-MM-YYYY)
 */
public class CheckersPawn implements Piece {

	private int rowPosition;
	private int columnPosition;
	private pieceColor color;
	private CheckersPawnMoveList moveList;
	private int endRow = 0; //not used now, maybe later
	
	/**
	 * Creates a new pawn in a certain location on the board with a certain color (team)
	 * @param rowPosition the row position of the pawn on the board
	 * @param columnPosition the column position of the pawn on the board
	 * @param Color the color (team) that the pawn is
	 */
	public CheckersPawn(int rowPosition, int columnPosition, pieceColor color) {
		this.rowPosition = rowPosition;
		this.columnPosition = columnPosition;
		this.color = color;
		moveList = new CheckersPawnMoveList(color);
		if (color == Piece.pieceColor.Black) {
			endRow = 7;
		}
		else {
			endRow = 0;
		}
	}
	
	
	@Override
	public pieceColor getColor() {
		return color;
	}

	
	@Override
	public ArrayList<Integer> move(int rowPosition, int columnPosition) {
		if (moveList.isValidMove(rowPosition, columnPosition)) {
			int arrayListLocation = moveList.findMove(rowPosition, columnPosition);
			ArrayList<Integer> captureList = moveList.getCapture(arrayListLocation);
			this.rowPosition = rowPosition;
			this.columnPosition = columnPosition;
			return captureList;
		}
		return null;
		
	}

	@Override
	public int getRowPosition() {
		return rowPosition;
	}

	@Override
	public int getColumnPosition() {
		return columnPosition;
	}


	@Override
	public String getPieceRepresentation() {
		if (color == Piece.pieceColor.White) {
			return "w";
		}
		return "b";
	}


	@Override
	public MoveList getMoveList() {
		try {
			return moveList;
		} 
		catch (NullPointerException e) {
			System.out.println("MoveList not updated yet!");
		}
		return null;
	}
	
	@Override
	public void updateMoveList(ArrayList<Piece> myPieces, ArrayList<Piece> enemyPieces) {
		moveList.updateMoveList(rowPosition, columnPosition, myPieces, enemyPieces);
	}
	
	/**
	 * Checks if the piece is at the end of the row (to be kinged)
	 * @return true if it is at the end of the row, false otherwise
	 */
	public boolean atEndRow() {
		if (rowPosition == endRow) {
			return true;
		}
		return false;
	}


}
