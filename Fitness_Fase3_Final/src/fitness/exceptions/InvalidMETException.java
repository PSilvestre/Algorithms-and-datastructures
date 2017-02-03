/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class InvalidMETException extends Exception{
	private static final long serialVersionUID = 1L;
	
	 public InvalidMETException( )   
	    {
	        super();
	    }
	
	public InvalidMETException(String errMsg){
		super(errMsg);
	}
}
