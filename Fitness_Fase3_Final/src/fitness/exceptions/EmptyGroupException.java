/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class EmptyGroupException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyGroupException(){
		super();
	}
	
	public EmptyGroupException(String errMsg){
		super(errMsg);
	}
}
