/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class UserDoesNotExistException extends Exception{
	private static final long serialVersionUID = 1L;
	public UserDoesNotExistException(){
		super();
	}
	public UserDoesNotExistException(String errMsg){
		super(errMsg);
	}
}
