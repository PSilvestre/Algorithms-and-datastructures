/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class NoGroupsException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoGroupsException(){
		super();
	}
	
	public NoGroupsException(String errMsg){
		super(errMsg);
	}
}
