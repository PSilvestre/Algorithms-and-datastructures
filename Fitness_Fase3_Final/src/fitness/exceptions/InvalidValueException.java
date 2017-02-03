/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class InvalidValueException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidValueException(){
		super();
	}
	
	public InvalidValueException(String errMsg){
		super(errMsg);
	}
}
