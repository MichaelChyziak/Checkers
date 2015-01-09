import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Creates a human player that is able to play the game of checkers by making moves
 * @author Michael Chyziak (mchyziak@sfu.ca)
 * @version 1
 * @since 15-12-2014 (DD-MM-YYYY)
 */
public class Player {

	protected Piece.pieceColor color;
	protected Board board;
	
	/**
	 * Assigns the player to a certain color (team) and gets the current board
	 */
	public Player(Piece.pieceColor color, Board board) {
		this.color = color;
		this.board = board;
	}
	
	/**
	 * Allows a player to make a turn
	 */
	public void turn(Board board) {
		
		this.board = board;
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		ArrayList<Piece> teamPieces = this.board.getTeamPieces(color);
		ArrayList<Piece> enemyPieces = this.board.getEnemyPieces(color);
		
		Piece pieceChosen = null;
		
		System.out.println("It is player " + color + "'s turn!");
		
		boolean validFrom = false;
		
		while (!validFrom) {
			System.out.println("What row would you like to select?");
			int rowFrom = in.nextInt() - 1;
			System.out.println("What column would you like to select?");
			int columnFrom = in.nextInt() - 1;
			for (int i = 0; i < teamPieces.size(); i++) {
				if (rowFrom == teamPieces.get(i).getRowPosition() && columnFrom == teamPieces.get(i).getColumnPosition()) {
					pieceChosen = teamPieces.get(i);
					validFrom = true;
					break;
				}
			}
		}
		pieceChosen.updateMoveList(teamPieces, enemyPieces);
		MoveList pieceMoveList;
		if (pieceChosen.getClass() == CheckersPawn.class) {
			pieceMoveList = (CheckersPawnMoveList) pieceChosen.getMoveList();
		}
		else {
			pieceMoveList = (CheckersKingMoveList) pieceChosen.getMoveList();
		}
		
		
		ArrayList<int[]> pieceMoves = pieceMoveList.getMoveList();
		ArrayList<ArrayList<Integer>> pieceCaptures = pieceMoveList.getCaptureList();
		
		System.out.println("Possible Moves: ");
		
		for (int i = 0; i < pieceMoves.size(); i++) {
			System.out.println((pieceMoves.get(i)[0] + 1) + ", " + (pieceMoves.get(i)[1] + 1)); 
		}
		System.out.println("Possible Captures: ");
		for (int i = 0; i < pieceCaptures.size(); i++) {
			for (int j = 0; j < pieceCaptures.get(i).size(); j++) {
				System.out.print(pieceCaptures.get(i).get(j).intValue() + " ") ;
			}
			System.out.println();
		}
		
		System.out.println("What row would you like to move to?");
		int rowTo = in.nextInt() - 1;
		System.out.println("What column would you like to move to?");
		int columnTo = in.nextInt() - 1;
		
		if (pieceChosen.getMoveList().isValidMove(rowTo, columnTo)) {
			ArrayList<Integer> capturedPieces = pieceChosen.move(rowTo, columnTo);
			
			//means that there are captures (since array is only {0} if no captures, which size is 1) (not sure what size 0 means), could be error
			if (capturedPieces.size() > 1) {
				
				
				//deletes the first element (since it only lets us know that we are jumping) and then sorts the rest of the list
				capturedPieces.remove(0);
				Collections.sort(capturedPieces);
				
				
				//delete each captured piece (***Remember, pieces get shifted when deletion happens below them)
				for (int removed = 0; capturedPieces.size() > 0; removed++) {
		
					this.board.removePiece(enemyPieces.get(capturedPieces.get(0) - removed).getRowPosition(), 
							enemyPieces.get(capturedPieces.get(0) - removed).getColumnPosition(), Piece.getOppositeColor(color));
					capturedPieces.remove(0);	
					
				}
			}
		}
		
		checkIfKinged(pieceChosen);
		
	}
	
	private void checkIfKinged(Piece pieceChosen) {
		if (pieceChosen.getClass() == CheckersPawn.class) {
			CheckersPawn pawnPiece = (CheckersPawn) pieceChosen;
			if (pawnPiece.atEndRow()) {
				board.kingPiece(pawnPiece);
			}
		}
		
	}


	/**
	 * Gets the color (or team) that the player is on
	 * @return the color White or Black of enum type Piece.pieceColor 
	 */
	public Piece.pieceColor getColor() {
		return color;
	}
	
	
	/**
	 * Returns the game board
	 * @return the game board
	 */
	public Board getBoard() {
		return board;
	}
	
	
	
	/**
	 * Sets the board to the board given
	 * @param board the new board to change to
	 */
	protected void setBoard(Board board) {
		this.board = board;
	}

}
