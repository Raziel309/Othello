package game;

public interface GameGrid {
	/**
	 * Places a white piece at Location 'location.
	 * @precondition location is a valid location
	 */
	public GameGrid placeWhitePiece(Location location);
	
	/**
	 * Places a black piece at Location 'location.
	 * @precondition location is a valid location
	 */
	public GameGrid placeBlackPiece(Location location);
	
	/**
	 * @return White's score
	 */
	public int whiteScore();
	
	/**
	 * @return Black's score
	 */
	public int blackScore();
	
	/**
	 * @return true if the game is over, false otherwise
	 */
	public boolean gameOver();
}
