/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class ActivityDoesNotExistException extends Exception{
	private static final long serialVersionUID = 1L;

	 public ActivityDoesNotExistException( )   
	    {
	        super();
	    }
	
	public ActivityDoesNotExistException(String errMsg){
		super(errMsg);
	}
}
