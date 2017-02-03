/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class UserAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
		super();
	}

	UserAlreadyExistsException(String errMsg) {
		super(errMsg);
	}

}
