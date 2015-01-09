import java.util.ArrayList;
import java.util.Collections;

/**
 * The tree that stores each variant of each move made on the board to a certain max depth
 * @author Michael Chyziak (mchyziak@sfu.ca)
 * @version 1
 * @since 24-12-2014 (DD-MM-YYYY)
 */
public class MoveTree {

	private Board board;
	private ArrayList<MoveTree> subMoves;
	private int currentDepth;
	private Piece.pieceColor color;
	private int boardScore;
	
	public MoveTree(Board board, Piece.pieceColor color, int currentDepth) {
		this.board = board;
		this.color = color;
		boardScore = -1;
		this.currentDepth = currentDepth;
		if (this.currentDepth < AIPlayer.maxDepth) {
			subMoves = new ArrayList<MoveTree>(getAmountOfBranches());
			createBranches();
		}
		else {
			boardScore = getBoardScore();
		}
	}
	
	
	

	/**
	 * Calculates the amount of branches a certain part of a tree should have, depending on what team we are
	 * @param moveTree the current branch (could be even root) of our tree of which we find the branches of 
	 * @param color the team we are looking for moves for
	 * @return the total amount of valid moves that can be made for the teams color
	 */
	public int getAmountOfBranches() {
		int validTeamMoves = 0;
		for (int i = 0; i < board.getTeamPieces(color).size(); i++) {
			//update the pieces move list and then add the total amount of moves the piece can make to the teams total number of moves it can make
			board.getTeamPieces(color).get(i).getMoveList().updateMoveList(board.getTeamPieces(color).get(i).getRowPosition(), board.getTeamPieces(color).get(i).getColumnPosition(), board.getTeamPieces(color), board.getEnemyPieces(color));
			validTeamMoves += board.getTeamPieces(color).get(i).getMoveList().getNumberOfValidMoves();
		}
		return validTeamMoves;
	}
	
	
	public void createBranches() {
		for (int j = 0; j < board.getTeamPieces(color).size(); j++) {
//			//FOR DEBUGGING PURPOSES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//			board.showBoard();
//			System.out.println();
			for (int k = 0; k < board.getTeamPieces(color).get(j).getMoveList().getMoveList().size(); k++) {
				
				ArrayList<Piece> whitePiecesBoard = board.getWhitePieces();
				ArrayList<Piece> blackPiecesBoard = board.getBlackPieces();
				Board nextBoard = new Board(whitePiecesBoard, blackPiecesBoard);
			
				
				nextBoard.getTeamPieces(color).get(j).getMoveList().updateMoveList(nextBoard.getTeamPieces(color).get(j).getRowPosition(), 
						nextBoard.getTeamPieces(color).get(j).getColumnPosition(), nextBoard.getTeamPieces(color), nextBoard.getEnemyPieces(color));
			
				
				int[] moveTo = nextBoard.getTeamPieces(color).get(j).getMoveList().getMoveList().get(k);

				//make move on board and get the capture list of the move
				ArrayList<Integer> capturedPieces = nextBoard.getTeamPieces(color).get(j).move(moveTo[0], moveTo[1]);
				
				//remove any pieces that are captured
				//means that there are captures (since array is only {0} if no captures, which size is 1) (not sure what size 0 means), could be error
				if (capturedPieces.size() > 1) {
					
					
					//deletes the first element (since it only lets us know that we are jumping) and then sorts the rest of the list
					capturedPieces.remove(0);
					Collections.sort(capturedPieces);
					
					
					//delete each captured piece (***Remember, pieces get shifted when deletion happens below them)
					for (int removed = 0; capturedPieces.size() > 0; removed++) {
			
						nextBoard.removePiece(nextBoard.getEnemyPieces(color).get(capturedPieces.get(0) - removed).getRowPosition(), 
								nextBoard.getEnemyPieces(color).get(capturedPieces.get(0) - removed).getColumnPosition(), Piece.getOppositeColor(color));
						capturedPieces.remove(0);	
						
					}
				}
				
				checkIfKinged(nextBoard.getTeamPieces(color).get(j), nextBoard);

//				//FOR DEBUGGING PURPOSES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//				nextBoard.showBoard();
//				System.out.println();
				
//				//FOR TESTING THE BOARD
//				for (int i = 0; i < subMoves.size(); i++) {
//					System.out.println("test board: " + i);
//					board.showBoard();
//				}
				
				//assign the new board to the submoves
				MoveTree tempTree = new MoveTree(nextBoard, Piece.getOppositeColor(color), currentDepth + 1);
				subMoves.add(tempTree);
				
			}
		}

		
//		//FOR TESTING THE BOARD
//		for (int i = 0; i < subMoves.size(); i++) {
//			System.out.println("test board");
//			subMoves.get(i).getBoard().showBoard();
//		}
		
	}
	
	private void checkIfKinged(Piece pieceChosen, Board board) {
		if (pieceChosen.getClass() == CheckersPawn.class) {
			CheckersPawn pawnPiece = (CheckersPawn) pieceChosen;
			if (pawnPiece.atEndRow()) {
				board.kingPiece(pawnPiece);
			}
		}
		
	}
	
	
	/**
	 * Gets the score of the current board
	 * @param color the color (team) that we are calculating the score for
	 * @return the score of the current board
	 */
	private int getBoardScore() {
		
		//Calculate the score for black, if color is white then reverse the score
		int boardScore = 0;
		
		//Scoring stats
		final int pawnScore = 100;
		final int kingScore = 400;
		
		
		
		//Score for each piece on the board (kings and pawns) for white (negative toward total score)
		for (int i = 0; i < board.getWhitePieces().size(); i++) {
			if (board.getWhitePieces().get(i).getClass() == CheckersPawn.class) {
				boardScore -= pawnScore;
			}
			if (board.getWhitePieces().get(i).getClass() == CheckersKing.class) {
				boardScore -= kingScore;
			}
		}
		
		//Score for each piece on the board (kings and pawns) for black (positive toward total score)
		for (int i = 0; i < board.getBlackPieces().size(); i++) {
			if (board.getBlackPieces().get(i).getClass() == CheckersPawn.class) {
				boardScore += pawnScore;
			}
			if (board.getBlackPieces().get(i).getClass() == CheckersKing.class) {
				boardScore += kingScore;
			}
		}
		
		
		//if color is white then reverse the score (since was originally calculated for favoring black)
		if (Piece.pieceColor.White == color) {
			boardScore *= -1;
		}
		
		return boardScore;
	}
	
	
	
	public ArrayList<MoveTree> getSubMoves() {
		return subMoves;
	}
	
	public MoveTree getSubMove(int index) {
		
		if (index < subMoves.size()) {
			return subMoves.get(index);
		}
		
		return null;
	}
	
	public int getSize() {
		return subMoves.size();
	}
	
	
	public Board getBoard() {
		return board;
	}
	
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public int getScore() {
		return boardScore;
	}
	
	
	public void setScore(int score) {
		boardScore = score;
	}
}
