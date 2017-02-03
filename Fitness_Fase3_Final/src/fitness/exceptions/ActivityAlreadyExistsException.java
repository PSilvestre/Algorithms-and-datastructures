/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class ActivityAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	 public ActivityAlreadyExistsException( )   
	    {
	        super();
	    }
	
	
	public ActivityAlreadyExistsException(String errMsg){
		super(errMsg);
	}
}
