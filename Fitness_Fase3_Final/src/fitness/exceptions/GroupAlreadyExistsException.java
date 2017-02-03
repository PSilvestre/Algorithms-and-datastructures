/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class GroupAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public GroupAlreadyExistsException(){
		super();
	}
	
	public GroupAlreadyExistsException(String errMsg){
		super(errMsg);
	}
}
