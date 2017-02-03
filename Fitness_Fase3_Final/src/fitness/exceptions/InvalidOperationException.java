/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class InvalidOperationException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidOperationException(){
		super();
	}
	
	public InvalidOperationException(String errMsg){
		super(errMsg);
	}
}
