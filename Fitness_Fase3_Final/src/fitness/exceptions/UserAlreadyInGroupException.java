/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class UserAlreadyInGroupException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserAlreadyInGroupException(){
		super();
	}
	
	public UserAlreadyInGroupException(String errMsg){
		super(errMsg);
	}
}
