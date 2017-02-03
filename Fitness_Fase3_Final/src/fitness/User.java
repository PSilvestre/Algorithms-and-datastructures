/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */

package fitness;

interface User extends UserNonModifiable{
	

	/**
	 * Sets the unique identifier of the <code>User</code> to
	 * <code>newId</code>.
	 * 
	 * @param newId the new identifier
	 */
	void setId(String newId);

	/**
	 * Sets the name of the <code>User</code> to <code>newName</code>.
	 * 
	 * @param newName is the new name
	 */
	void setName(String newName);


	/**
	 * Sets the height of the <code>User</code> to <code>newHeight</code>.
	 * 
	 * @param newHeight
	 *            is the new height
	 */
	void setHeight(int newHeight);
	
	

	/**
	 * Sets the weight of the <code>User</code> to <code>newWeight</code>.
	 * 
	 * @param newWeight is the new Weight of the User
	 */
	void setWeight(int newWeight);

	

	/**
	 * Sets the age of the <code>User</code> to <code>newAge</code>.
	 * 
	 * @param newAge is the new Age of the User
	 */
	void setAge(int newAge);
	
	

	/**
	 * Adds to the number of steps taken by the <code>User</code> to
	 * <code>newSteps</code>.
	 * 
	 * @param newSteps is the amount of new steps taken
	 */
	void addSteps(int newSteps);


	/**
	 * Adds a new <code>PerformedActivity</code> to the User.
	 * 
	 * @param a is the Activity to add
	 * @param duration is the duration of the activity
	 */
	void addActivity(PerformedActivity a);
	
	
	
	/**
	 * Set this users group to Group g.
	 * @param g the group the user belongs to.
	 */
	void setGroup(Group g);
	
	
}
