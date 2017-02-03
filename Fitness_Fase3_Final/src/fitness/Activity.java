/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

public interface Activity {
	
	/**
	 * Gets the Id of the Activity.
	 * @return the Id of the Activity
	 */
	String getId();
	
	/**
	 * Gets the Name of the Activity.
	 * @return the Name of the Activity
	 */
	String getName();
	
	/**
	 * Gets the MET of the Activity.
	 * @return the MET of the Activity
	 */
	int getMET();
	
}
