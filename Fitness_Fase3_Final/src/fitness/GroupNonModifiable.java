/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

import dataStructures.Iterator;


public interface GroupNonModifiable {
	/**
	 * Gets the unique Id of this group.
	 * @return the unique Id of this group
	 */
	String getId();
	
	/**
	 * Gets the name of this group.
	 * @return the name of this group
	 */
	String getName();
	
	/**
	 * Gets the member list for this group.
	 * @return an iterator with the members ordered by insertion.
	 */
	Iterator<UserNonModifiable> getMembers();
	
	/**
	 * Tests if this group has a member with an Id equal to userId. 
	 * @param userId is the Id to test
	 * @return true or false
	 */
	boolean hasMember(String userId);
	
	/**
	 * Gets the sum of the calories burned by all the users in this group.
	 * @return the sum of the calories burned by all the users in this group
	 */
	int getCumulativeCalories();
	
	/**
	 * Gets the sum of the steps taken by all the users in this group.
	 * @return the sum of the steps taken by all the users in this group
	 */
	int getCumulativeSteps();
	
	/**
	 * Checks if the Group is empty.
	 * @return true if it is, false otherwise
	 */
	boolean isEmpty();
}
