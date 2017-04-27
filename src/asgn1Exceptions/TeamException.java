/**
 * 
 */
package asgn1Exceptions;

/** 
 * A  class to handle exceptions related to sport teams.
 * */
public class TeamException extends Exception{

	/**
	 * A class to handle exceptions related to sport teams and displays a message.
	 */
	public TeamException() {
	}

	/** 
     * A class to handle exceptions related to sport teams and displays a message.
	 *  
	 * @param message A message detailing why the exception was thrown.
	 */
	public TeamException(String message) {
		super(message);
	}

	/** 
      * A  class to handle exceptions related to sport teams with a specified cause.
	 *  
	 * @param cause The cause of the  exception.
	 */
	public TeamException(Throwable cause) {
		super(cause);
	}

	/** 
      * A  class to handle exceptions related to sport teams with a specified cause.
 	 * with a specified message and cause.
	 *  
	 * @param message A message detailing why the exception was thrown.
	 * @param cause The cause of the  exception.
	 */
	public TeamException(String message, Throwable cause) {
		super(message, cause);
	}

	/** 
      * A  class to handle exceptions related to sport teams with a specified cause.
 	 * with a specified message, cause, suppression enabled or disabled, and writable stack trace 
	 * enabled or disabled.
	 *  
	 * @param message A message detailing why the exception was thrown.
	 * @param cause The cause of the  exception.
     * @param enableSuppression Whether or not suppression is enabled or disabled.
     * @param writableStackTrace Whether or not the stack trace should be writable.
     */	
	public TeamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
