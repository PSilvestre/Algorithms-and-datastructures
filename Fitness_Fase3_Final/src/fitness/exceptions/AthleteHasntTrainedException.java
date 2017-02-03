/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness.exceptions;

public class AthleteHasntTrainedException extends Exception{
	private static final long serialVersionUID = 1L;

	public AthleteHasntTrainedException() {
		super();
	}

	AthleteHasntTrainedException(String errMsg) {
		super(errMsg);
	}
}
