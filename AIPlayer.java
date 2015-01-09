

/**
 * The AI for chess game which uses minimax with alpha-beta pruning
 * @author Michael Chyziak (mchyziak@sfu.ca)
 * @version 1
 * @since 18-12-2014 (DD-MM-YYYY)
 */
public class AIPlayer extends Player{

	public static final int maxDepth = 3;
	private MoveTree moveTree;
	
	
	public AIPlayer(Piece.pieceColor color, Board board) {
		super(color, board);
		
	//	moveTree = new MoveTree(board, color, 0);
	}
	

	public int getScores(MoveTree moveTree, boolean myTurn) {
		if (moveTree.getScore() == -1) {
			int score = 0;
			int amountScored = 0;
			for (int i = 0; i < moveTree.getSubMoves().size(); i++) {
				
				if (myTurn) {
					if (amountScored == 0 || moveTree.getSubMove(i).getScore() > score) {
						score = getScores(moveTree.getSubMove(i), myTurn);
						amountScored++;
					}
				}
				
				
				else {
					if (amountScored == 0 || moveTree.getSubMove(i).getScore() < score) {
						score = getScores(moveTree.getSubMove(i), !myTurn);
						amountScored++;
					}
				}
				
			}
			
			moveTree.setScore(score);
			
		}
		
		
		return moveTree.getScore();
	}
	
	
	
	/**
	 * Allows a player to make a turn
	 */
	public void turn(Board board) {
		super.board = board;
		
		moveTree = new MoveTree(super.board, color, 0);
		getScores(moveTree, true);

		
		
		
		int score = -1;
		int index= -1;
		for (int i = 0; i < moveTree.getSubMoves().size(); i++) {
			
			if (super.getColor() == Piece.pieceColor.White) {
				if (moveTree.getSubMove(i).getScore() > score) {
					score = moveTree.getSubMove(i).getScore();
					index = i;
				}
			}
			else {
				if (moveTree.getSubMove(i).getScore() < score) {
					score = moveTree.getSubMove(i).getScore();
					index = i;
				}
			}
		}
		
		
//		//FOR DEBUGGING
//		System.out.println("Testing...");
//		System.out.println(super.getColor());
//		for (int i = 0; i < moveTree.getSubMoves().size(); i++) {
//			moveTree.getSubMove(i).getBoard().showBoard();
//			System.out.println("Score: " + moveTree.getSubMove(i).getScore());
//			System.out.println();
//		}
//		System.out.println("Testing finished.");
		
		
		
		
		super.board = moveTree.getSubMove(index).getBoard();
		moveTree = moveTree.getSubMove(index);

	}
	

	public Board getBoard() {
		return board;
	}
}
