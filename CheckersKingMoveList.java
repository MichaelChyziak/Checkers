import java.util.ArrayList;


public class CheckersKingMoveList implements MoveList {

	
	//lists of int arrays where the 1st element is the row it can move to, and the 2nd element is the column it can move to, 
		//1rd is its priority (0 meaning no jump, 1 meaning jump), 2nd and onward being the elements it jumps over (none if jump priority is 0)
		private ArrayList<int[]> moveList;
		private ArrayList<ArrayList<Integer>> captureList;
		private int rowMoveType[] = {1, -1};
		private final int columnMoveType[] = {1, -1};
		private Piece.pieceColor color; //not used now, maybe debugging or later
		
		/**
		 * Updates the moveList and stores it in an array list
		 * @param color the team that our piece is aligned to
		 */
		public CheckersKingMoveList(Piece.pieceColor color) {
			moveList = new ArrayList<int[]>(0);
			captureList = new ArrayList<ArrayList<Integer>>(0);
			this.color = color;
		}
		
		@Override
		public boolean isValidMove(int rowPosition, int columnPosition) {
			for (int i = 0; i < moveList.size(); i++) {
				if (moveList.get(i)[0] == rowPosition && moveList.get(i)[1] == columnPosition) {
					return true;
				}
			}
			return false;
		}

		@Override
		public int getNumberOfValidMoves() {
			return moveList.size();
		}

		@Override
		public void updateMoveList(int currentRow, int currentColumn, ArrayList<Piece> myPieces, ArrayList<Piece> enemyPieces) {
			deletePastLists();
			createAdjacentMoveList(currentRow, currentColumn);
			checkForAllyPieces(currentRow, currentColumn, myPieces);
			checkForEnemyJump(currentRow, currentColumn, myPieces, enemyPieces, 0);
		}
		
		/**
		 * Resets our move list and capture list to empty lists
		 */
		private void deletePastLists() {
			moveList.clear();
			captureList.clear();
		}
		
		
		/**
		 * Checks if we can jump over an enemy piece, if so update the move list, if not do nothing
		 * @param currentRow the current row that the piece is in
		 * @param currentColumn the current column that the piece is in
		 * @param myPieces the players pieces
		 * @param enemyPieces the enemies pieces
		 * @param amtOfPrevJumps the amount of jumps that have happened prior to this function being called
		 */
		private void checkForEnemyJump(int currentRow, int currentColumn, ArrayList<Piece> myPieces, ArrayList<Piece> enemyPieces, int amtOfPrevJumps) {
			
			int pieceLocationInArrayList = -1;
			
			for (int i = 0; i < enemyPieces.size(); i++) {

				
				if (currentRow + rowMoveType[0] == enemyPieces.get(i).getRowPosition() &&
						currentColumn + columnMoveType[0] == enemyPieces.get(i).getColumnPosition()) {

					
					//ENEMY TO THE RIGHT, remove the move to the right, but take into account jumping the piece
					if (amtOfPrevJumps == 0) {
						pieceLocationInArrayList = findMove(currentRow + rowMoveType[0], currentColumn + columnMoveType[0]);
						moveList.remove(pieceLocationInArrayList);
						captureList.remove(pieceLocationInArrayList);
					}
						
					int tempMove[] = {enemyPieces.get(i).getRowPosition() + rowMoveType[0], 
							enemyPieces.get(i).getColumnPosition() + columnMoveType[0]};
					moveList.add(tempMove);
					
					//Capture priority
					if (amtOfPrevJumps == 0) {
						ArrayList<Integer> temp = new ArrayList<Integer>(2);
						temp.add(1);
						temp.add(i);
						captureList.add(temp);
					}
					else {
						captureList.get(captureList.size() - 1).add(i);
					}
					
					boolean checkForExtraJumps = true;
					
					for (int j = 0; j < myPieces.size(); j++) {
						if (enemyPieces.get(i).getRowPosition() + rowMoveType[0] == myPieces.get(j).getRowPosition() &&
								enemyPieces.get(i).getColumnPosition() + columnMoveType[0] == myPieces.get(j).getColumnPosition()) {
								
							pieceLocationInArrayList = findMove(enemyPieces.get(i).getRowPosition() + rowMoveType[0], 
									enemyPieces.get(i).getColumnPosition() + columnMoveType[0]);
							moveList.remove(pieceLocationInArrayList);
							captureList.remove(pieceLocationInArrayList);

							checkForExtraJumps = false;
						}
					}
					
					
					for (int j = 0; j < enemyPieces.size(); j++) {
						if (enemyPieces.get(i).getRowPosition() + rowMoveType[0] == enemyPieces.get(j).getRowPosition() &&
								enemyPieces.get(i).getColumnPosition() + columnMoveType[0] == enemyPieces.get(j).getColumnPosition()) {
						
							pieceLocationInArrayList = findMove(enemyPieces.get(i).getRowPosition() + rowMoveType[0], 
									enemyPieces.get(i).getColumnPosition() + columnMoveType[0]);
							moveList.remove(pieceLocationInArrayList);
							captureList.remove(pieceLocationInArrayList);
							checkForExtraJumps = false;

						}
							
					}
					
					if (checkForExtraJumps) {
						if (amtOfPrevJumps > 0) {
							moveList.remove(moveList.size() - 2);
						}
						checkForEnemyJump(enemyPieces.get(i).getRowPosition() + rowMoveType[0],
								enemyPieces.get(i).getColumnPosition() + columnMoveType[0], myPieces, enemyPieces, amtOfPrevJumps + 1);
					}
						
					
					
					
				}
				
				
				
				
				
				if (currentRow + rowMoveType[0] == enemyPieces.get(i).getRowPosition() &&
						currentColumn + columnMoveType[1] == enemyPieces.get(i).getColumnPosition()) {
					
					
					pieceLocationInArrayList = findMove(currentRow + rowMoveType[0], currentColumn + columnMoveType[1]);
					//ENEMY TO THE LEFT, remove the move to the left, but take into account jumping the piece
					if (amtOfPrevJumps == 0) {
						moveList.remove(pieceLocationInArrayList);
						captureList.remove(pieceLocationInArrayList);
					}
						
					int tempMove[] = {enemyPieces.get(i).getRowPosition() + rowMoveType[0], 
							enemyPieces.get(i).getColumnPosition() + columnMoveType[1]};
					moveList.add(tempMove);
					
					//Capture priority
					if (amtOfPrevJumps == 0) {
						ArrayList<Integer> temp = new ArrayList<Integer>(2);
						temp.add(1);
						temp.add(i);
						captureList.add(temp);
					}
					else {
						captureList.get(captureList.size() - 1).add(i);
					}
					
					
					
					boolean checkForExtraJumps = true;
					
					
					for (int j = 0; j < myPieces.size(); j++) {
						if (enemyPieces.get(i).getRowPosition() + rowMoveType[0] == myPieces.get(j).getRowPosition() &&
								enemyPieces.get(i).getColumnPosition() + columnMoveType[1] == myPieces.get(j).getColumnPosition()) {
							
							
							pieceLocationInArrayList = findMove(enemyPieces.get(i).getRowPosition() + rowMoveType[0], 
									enemyPieces.get(i).getColumnPosition() + columnMoveType[1]);
							moveList.remove(pieceLocationInArrayList);
							captureList.remove(pieceLocationInArrayList);
							checkForExtraJumps = false;
						}
					}
					
					
					for (int j = 0; j < enemyPieces.size(); j++) {
						if (enemyPieces.get(i).getRowPosition() + rowMoveType[0] == enemyPieces.get(j).getRowPosition() &&
								enemyPieces.get(i).getColumnPosition() + columnMoveType[1] == enemyPieces.get(j).getColumnPosition()) {
							
							pieceLocationInArrayList = findMove(enemyPieces.get(i).getRowPosition() + rowMoveType[0], 
									enemyPieces.get(i).getColumnPosition() + columnMoveType[1]);
							moveList.remove(pieceLocationInArrayList);
							captureList.remove(pieceLocationInArrayList);
							checkForExtraJumps = false;
						}
							
					}
					
					if (checkForExtraJumps) {
						if (amtOfPrevJumps > 0) {
							moveList.remove(moveList.size() - 2);
						}
						checkForEnemyJump(enemyPieces.get(i).getRowPosition() + rowMoveType[0],
								enemyPieces.get(i).getColumnPosition() + columnMoveType[1], myPieces, enemyPieces, amtOfPrevJumps + 1);
					}
				}
				
				
				
				
				
				if (currentRow + rowMoveType[1] == enemyPieces.get(i).getRowPosition() &&
						currentColumn + columnMoveType[1] == enemyPieces.get(i).getColumnPosition()) {
					
					
					pieceLocationInArrayList = findMove(currentRow + rowMoveType[1], currentColumn + columnMoveType[1]);
					//ENEMY TO THE Lower LEFT, remove the move to the left, but take into account jumping the piece
					if (amtOfPrevJumps == 0) {
						moveList.remove(pieceLocationInArrayList);
						captureList.remove(pieceLocationInArrayList);
					}
						
					int tempMove[] = {enemyPieces.get(i).getRowPosition() + rowMoveType[1], 
							enemyPieces.get(i).getColumnPosition() + columnMoveType[1]};
					moveList.add(tempMove);
					
					//Capture priority
					if (amtOfPrevJumps == 0) {
						ArrayList<Integer> temp = new ArrayList<Integer>(2);
						temp.add(1);
						temp.add(i);
						captureList.add(temp);
					}
					else {
						captureList.get(captureList.size() - 1).add(i);
					}
					
					
					
					boolean checkForExtraJumps = true;
					
					
					for (int j = 0; j < myPieces.size(); j++) {
						if (enemyPieces.get(i).getRowPosition() + rowMoveType[1] == myPieces.get(j).getRowPosition() &&
								enemyPieces.get(i).getColumnPosition() + columnMoveType[1] == myPieces.get(j).getColumnPosition()) {
							
							
							pieceLocationInArrayList = findMove(enemyPieces.get(i).getRowPosition() + rowMoveType[1], 
									enemyPieces.get(i).getColumnPosition() + columnMoveType[1]);
							moveList.remove(pieceLocationInArrayList);
							captureList.remove(pieceLocationInArrayList);
							checkForExtraJumps = false;
						}
					}
					
					
					for (int j = 0; j < enemyPieces.size(); j++) {
						if (enemyPieces.get(i).getRowPosition() + rowMoveType[1] == enemyPieces.get(j).getRowPosition() &&
								enemyPieces.get(i).getColumnPosition() + columnMoveType[1] == enemyPieces.get(j).getColumnPosition()) {
							
							pieceLocationInArrayList = findMove(enemyPieces.get(i).getRowPosition() + rowMoveType[1], 
									enemyPieces.get(i).getColumnPosition() + columnMoveType[1]);
							moveList.remove(pieceLocationInArrayList);
							captureList.remove(pieceLocationInArrayList);
							checkForExtraJumps = false;
						}
							
					}
					
					if (checkForExtraJumps) {
						if (amtOfPrevJumps > 0) {
							moveList.remove(moveList.size() - 2);
						}
						checkForEnemyJump(enemyPieces.get(i).getRowPosition() + rowMoveType[1],
								enemyPieces.get(i).getColumnPosition() + columnMoveType[1], myPieces, enemyPieces, amtOfPrevJumps + 1);
					}
				}
				
				
				
				
				if (currentRow + rowMoveType[1] == enemyPieces.get(i).getRowPosition() &&
						currentColumn + columnMoveType[0] == enemyPieces.get(i).getColumnPosition()) {

					
					//ENEMY TO THE lower RIGHT, remove the move to the right, but take into account jumping the piece
					if (amtOfPrevJumps == 0) {
						pieceLocationInArrayList = findMove(currentRow + rowMoveType[1], currentColumn + columnMoveType[0]);
						moveList.remove(pieceLocationInArrayList);
						captureList.remove(pieceLocationInArrayList);
					}
						
					int tempMove[] = {enemyPieces.get(i).getRowPosition() + rowMoveType[1], 
							enemyPieces.get(i).getColumnPosition() + columnMoveType[0]};
					moveList.add(tempMove);
					
					//Capture priority
					if (amtOfPrevJumps == 0) {
						ArrayList<Integer> temp = new ArrayList<Integer>(2);
						temp.add(1);
						temp.add(i);
						captureList.add(temp);
					}
					else {
						captureList.get(captureList.size() - 1).add(i);
					}
					
					boolean checkForExtraJumps = true;
					
					for (int j = 0; j < myPieces.size(); j++) {
						if (enemyPieces.get(i).getRowPosition() + rowMoveType[1] == myPieces.get(j).getRowPosition() &&
								enemyPieces.get(i).getColumnPosition() + columnMoveType[0] == myPieces.get(j).getColumnPosition()) {
								
							pieceLocationInArrayList = findMove(enemyPieces.get(i).getRowPosition() + rowMoveType[1], 
									enemyPieces.get(i).getColumnPosition() + columnMoveType[0]);
							moveList.remove(pieceLocationInArrayList);
							captureList.remove(pieceLocationInArrayList);

							checkForExtraJumps = false;
						}
					}
					
					
					for (int j = 0; j < enemyPieces.size(); j++) {
						if (enemyPieces.get(i).getRowPosition() + rowMoveType[1] == enemyPieces.get(j).getRowPosition() &&
								enemyPieces.get(i).getColumnPosition() + columnMoveType[0] == enemyPieces.get(j).getColumnPosition()) {
						
							pieceLocationInArrayList = findMove(enemyPieces.get(i).getRowPosition() + rowMoveType[1], 
									enemyPieces.get(i).getColumnPosition() + columnMoveType[0]);
							moveList.remove(pieceLocationInArrayList);
							captureList.remove(pieceLocationInArrayList);
							checkForExtraJumps = false;

						}
							
					}
					
					if (checkForExtraJumps) {
						if (amtOfPrevJumps > 0) {
							moveList.remove(moveList.size() - 2);
						}
						checkForEnemyJump(enemyPieces.get(i).getRowPosition() + rowMoveType[1],
								enemyPieces.get(i).getColumnPosition() + columnMoveType[0], myPieces, enemyPieces, amtOfPrevJumps + 1);
					}
						
					
					
					
				}
			}
			
		}



		
		
		
		/**
		 * Removes all possibilities for our piece to move to a location that another piece of ours is located in the move list
		 * @param currentRow the current row that the piece is in
		 * @param currentColumn the current column that the piece is in
		 * @param myPieces the players pieces
		 */
		private void checkForAllyPieces(int currentRow, int currentColumn, ArrayList<Piece> myPieces) {
			for (int i = 0; i < myPieces.size(); i++) {
				if (currentRow + rowMoveType[0] == myPieces.get(i).getRowPosition() &&
						currentColumn + columnMoveType[0] == myPieces.get(i).getColumnPosition()) {
					
					int pieceLocationInArrayList = findMove(currentRow + rowMoveType[0], currentColumn + columnMoveType[0]);
					moveList.remove(pieceLocationInArrayList);
					captureList.remove(pieceLocationInArrayList);
				}
				if (currentRow + rowMoveType[0] == myPieces.get(i).getRowPosition() &&
						currentColumn + columnMoveType[1] == myPieces.get(i).getColumnPosition()) {
					
					int pieceLocationInArrayList = findMove(currentRow + rowMoveType[0], currentColumn + columnMoveType[1]);
					moveList.remove(pieceLocationInArrayList);
					captureList.remove(pieceLocationInArrayList);
				}
				
				
				if (currentRow + rowMoveType[1] == myPieces.get(i).getRowPosition() &&
						currentColumn + columnMoveType[1] == myPieces.get(i).getColumnPosition()) {
					
					int pieceLocationInArrayList = findMove(currentRow + rowMoveType[1], currentColumn + columnMoveType[1]);
					moveList.remove(pieceLocationInArrayList);
					captureList.remove(pieceLocationInArrayList);
				}
				
				
				if (currentRow + rowMoveType[1] == myPieces.get(i).getRowPosition() &&
						currentColumn + columnMoveType[0] == myPieces.get(i).getColumnPosition()) {
					
					int pieceLocationInArrayList = findMove(currentRow + rowMoveType[1], currentColumn + columnMoveType[0]);
					moveList.remove(pieceLocationInArrayList);
					captureList.remove(pieceLocationInArrayList);
				}
			}
		}
		

		
		/**
		 * Sets all forward adjacent moves to the move list array list
		 * @param currentRow the current row our piece is in
		 */
		private void createAdjacentMoveList(int currentRow, int currentColumn) {
			if (currentColumn == 7) {
				int left[] = {currentRow + rowMoveType[0], currentColumn + columnMoveType[1]};
				moveList.add(left);
				
				//No capture priority
				ArrayList<Integer> temp = new ArrayList<Integer>(1);
				temp.add(0);
				captureList.add(temp);
			}
			else if (currentColumn == 0) {
				int right[] = {currentRow + rowMoveType[0], currentColumn + columnMoveType[0]};
				moveList.add(right);
			
				//No capture priority
				ArrayList<Integer> temp = new ArrayList<Integer>(1);
				temp.add(0);
				captureList.add(temp);
			}
			else {
				int left[] = {currentRow + rowMoveType[0], currentColumn + columnMoveType[1]};
				int right[] = {currentRow + rowMoveType[0], currentColumn + columnMoveType[0]};
				moveList.add(left);
				moveList.add(right);
				
				//No capture priority
				ArrayList<Integer> temp = new ArrayList<Integer>(1);
				temp.add(0);
				captureList.add(temp);
				captureList.add(temp); 
			}
		}
		
		
		
		/**
		 * Gives the location of the move in the move list array list
		 * @param rowPosition the position of the row we want to find in the move list
		 * @param columnPosition the position of the column we want to find in the move list
		 * @return the position of the move in the move list, -1 if there is no such move
		 */
		public int findMove(int rowPosition, int columnPosition) {
			for (int i = 0; i < moveList.size(); i++) {
				if (rowPosition == moveList.get(i)[0] && columnPosition == moveList.get(i)[1]) {
					return i;
				}
			}
			return -1;
		}
		
		
		/**
		 * Returns the move list
		 * @return the move list
		 */
		public ArrayList<int[]> getMoveList() {
			return moveList;
		}
		
		
		
		/**
		 * Returns the capture list
		 * @return the capture list
		 */
		public ArrayList<ArrayList<Integer>> getCaptureList() {
			return captureList;
		}

		
		/**
		 * Finds the location of the parameter value in the capture list and returns it
		 * @param arrayListLocation the location in the array list we are searching for
		 * @return the array list of integers of the given element in the larger array list
		 */
		public ArrayList<Integer> getCapture(int arrayListLocation) {
			return captureList.get(arrayListLocation);
		}
		
}
