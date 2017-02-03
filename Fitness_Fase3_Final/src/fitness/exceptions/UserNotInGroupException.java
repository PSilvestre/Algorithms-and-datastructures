/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class UserNotInGroupException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNotInGroupException(){
		super();
	}
	
	public UserNotInGroupException(String errMsg){
		super(errMsg);
	}
}
