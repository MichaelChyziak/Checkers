import java.util.ArrayList;


public class CheckersKing implements Piece {

	private int rowPosition;
	private int columnPosition;
	private pieceColor color;
	private CheckersKingMoveList moveList;
	
	/**
	 * Creates a new king in a certain location on the board with a certain color (team)
	 * @param rowPosition the row position of the king on the board
	 * @param columnPosition the column position of the king on the board
	 * @param Color the color (team) that the king is
	 */
	public CheckersKing(int rowPosition, int columnPosition, pieceColor color) {
		this.rowPosition = rowPosition;
		this.columnPosition = columnPosition;
		this.color = color;
		moveList = new CheckersKingMoveList(color);
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
			return "W";
		}
		return "B";
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

}
