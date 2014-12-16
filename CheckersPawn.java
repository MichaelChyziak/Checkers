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
	private MoveList moveList;
	
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
		moveList.createMoveList();
	}
	
	
	@Override
	public pieceColor getColor() {
		return color;
	}

	
	@Override
	public boolean move(int rowPosition, int columnPosition, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {
		return false;
		
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


}
