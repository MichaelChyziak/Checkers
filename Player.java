import java.util.Scanner;

/**
 * Creates a human player that is able to play the game of checkers by making moves
 * @author Michael Chyziak (mchyziak@sfu.ca)
 * @version 1
 * @since 15-12-2014 (DD-MM-YYYY)
 */
public class Player {

	private Piece.pieceColor color;
	
	/**
	 * Assigns the player to a certain color (team)
	 */
	public Player(Piece.pieceColor color) {
		this.color = color;
	}
	
	/**
	 * Allows a player to make a turn
	 */
	public void turn() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("What row would you like to select?");
		int rowFrom = in.nextInt();
		System.out.println("What column would you like to select?");
		int columnFrom = in.nextInt();
		System.out.println("What row would you like to move to?");
		int rowTo = in.nextInt();
		System.out.println("What row would you like to move to?");
		int columnTo = in.nextInt();
		
		//TODO
	}
	
	/**
	 * Gets the color (or team) that the player is on
	 * @return the color White or Black of enum type Piece.pieceColor 
	 */
	public Piece.pieceColor getColor() {
		return color;
	}

}
