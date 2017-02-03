/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class GroupDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public GroupDoesNotExistException(){
		super();
	}
	
	public GroupDoesNotExistException(String errMsg){
		super(errMsg);
	}
}
