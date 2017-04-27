package asgn1SportsUtils;

/**
 * A Enum that represents if a match has been won, lost of draw.
 * @author Alan
 *
 */
public enum WLD{

	WIN('W'),
	LOSS('L'),
	DRAW('D');

	char c;

	/**
	 * Constructs the Enum element with the specified character.
	 * @param c The character representation of the Enum element. 
	 */
	WLD(char c){
		this.c = c;
	}
	
	/**
	 * Returns 'W' for a win, 'L' for a loss and 'D' for a draw.
	 * 
	 * @return A character representing the result.
	 */
	public char getChar(){
		return c;
	}
	
}
