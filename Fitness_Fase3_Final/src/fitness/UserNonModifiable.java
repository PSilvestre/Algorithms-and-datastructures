/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

import dataStructures.Iterator;

public interface UserNonModifiable {
	/**
	 * Returns the unique identifier of the <code>User</code>.
	 * 
	 * @return the unique identifier of the <code>User</code>
	 */
	String getId();
	
	/**
	 * Returns the name of the <code>User</code>.
	 * 
	 * @return the name of the <code>User</code>
	 */
	String getName();
	
	/**
	 * Returns the height of the <code>User</code>.
	 * 
	 * @return the height of the <code>User</code>
	 */
	int getHeight();

	/**
	 * Returns the weight of the <code>User</code>.
	 * 
	 * @return the weight of the <code>User</code>
	 */
	int getWeight();
	
	/**
	 * Returns the age of the <code>User</code>.
	 * 
	 * @return the age of the <code>User</code>
	 */
	int getAge();
	
	/**
	 * Returns the gender of the User.
	 * @return "Masculino" or "Feminino"
	 */
	String getGender();

	/**
	 * Returns the number of steps taken by the <code>User</code>.
	 * 
	 * @return the number of steps taken by the <code>User</code>
	 */
	int getSteps();
	
	/**
	 * Returns all registered workouts the <code>User</code> has done in order
	 * of registration.
	 * 
	 * @return an Iterator of <code>Workout</code> objects
	 */
	Iterator<PerformedActivity> getWorkouts();
	
	/**
	 * Returns all registered workouts the <code>User</code> has done in order
	 * of caloric loss.
	 * 
	 * @return an Iterator of <code>Workout</code> objects
	 */
	Iterator<PerformedActivity> getWorkoutsByCalories();

	
	/**
	 * Checks if the User has performed at least one Activity.
	 * 
	 * @return true if he has performed at least one Activity, false otherwise.
	 */
	boolean hasWorkedOut();

	/**
	 * Checks if the User is in a Group.
	 * @return true or false
	 */
	boolean isInGroup();
	
	/**
	 * Returns the group the user belongs to.
	 * 
	 * @return the group if the User belongs to one, returns null otherwise.
	 */
	GroupNonModifiable getGroup();
	
	
	/**
	 * Gets the sum of all burned calories of this user.
	 * @return the sum of all burned calories of this user
	 */
	int getCalorieCount();
}
