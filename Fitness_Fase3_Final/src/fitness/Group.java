/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

interface Group extends GroupNonModifiable{
	
	
	/**
	 * Adds a member to the group.
	 * @param u is the User to be added
	 * @pre User is not in group already
	 */
	void addMember(User u);
	
	/**
	 * Removes a member from the group.
	 * @param userId the Id of the member to remove.
	 * @pre User is in group
	 */
	void removeMember(String userId);
	
	
	
	/**
	 * Adds to the caloric count of the group.
	 * @param calories the number of calories to add
	 */
	void addCalories(int calories);
	
	
	/**
	 * Adds to the step count of the group.
	 * @param steps the number of steps to add
	 */
	void addSteps(int steps);
	
}
