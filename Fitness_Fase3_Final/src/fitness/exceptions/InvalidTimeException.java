/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class InvalidTimeException extends Exception{
	private static final long serialVersionUID = 1L;
	
	  public InvalidTimeException( )   
	    {
	        super();
	    }

	
	public InvalidTimeException(String errMsg){
		super(errMsg);
	}
}
