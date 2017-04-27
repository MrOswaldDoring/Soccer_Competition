/**
 * 
 */
package asgn1Exceptions;

/** 
 * A  class to handle exceptions related to sport leagues.
 * */
public class LeagueException extends Exception{

	/**
	 * A class to handle exceptions related to sport leagues and displays a message.
	 */
	public LeagueException() {
	}

	/** 
     * A class to handle exceptions related to sport leagues and displays a message.
	 *  
	 * @param message A message detailing why the exception was thrown.
	 */
	public LeagueException(String message) {
		super(message);
	}

	/** 
      * A  class to handle exceptions related to sport leagues with a specified cause.
	 *  
	 * @param cause The cause of the  exception .
	 */
	public LeagueException(Throwable cause) {
		super(cause);
	}

	/** 
      * A  class to handle exceptions related to sport leagues with a specified cause.
 	 * with a specified message and cause.
	 *  
	 * @param message A message detailing why the exception was thrown.
	 * @param cause The cause of the  exception.
	 */
	public LeagueException(String message, Throwable cause) {
		super(message, cause);
	}

	/** 
      * A  class to handle exceptions related to sport leagues with a specified cause.
 	 * with a specified message, cause, suppression enabled or disabled, and writable stack trace 
	 * enabled or disabled.
	 *  
	 * @param message A message detailing why the exception was throw.
	 * @param cause The cause of the  exception. 
     * @param enableSuppression Whether or not suppression is enabled or disabled.
     * @param writableStackTrace Whether or not the stack trace should be writable.
     */	
	public LeagueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
