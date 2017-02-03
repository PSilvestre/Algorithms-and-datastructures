/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;


import dataStructures.Iterator;
import fitness.exceptions.ActivityAlreadyExistsException;
import fitness.exceptions.ActivityDoesNotExistException;
import fitness.exceptions.AthleteHasntTrainedException;
import fitness.exceptions.EmptyGroupException;
import fitness.exceptions.GroupAlreadyExistsException;
import fitness.exceptions.GroupDoesNotExistException;
import fitness.exceptions.InvalidOperationException;
import fitness.exceptions.InvalidTimeException;
import fitness.exceptions.InvalidValueException;
import fitness.exceptions.NoGroupsException;
import fitness.exceptions.UserAlreadyExistsException;
import fitness.exceptions.UserAlreadyInGroupException;
import fitness.exceptions.UserDoesNotExistException;
import fitness.exceptions.UserNotInGroupException;

public interface Fitness {
	/**
	 * Adds a user to the platform.
	 * @param userId the unique Id the User gets from his tracker
	 * @param gender the gender of the User, "M" for male, "F" for female
	 * @param weight the weight of the User in kg
	 * @param height the height of the User in cm
	 * @param age the age of the User in years
	 * @param name the name of the User
	 * @throws UserAlreadyExistsException if the User already exists on the platform
	 * @throws InvalidValueException if gender != "M" or "F" (or lowercase) and if the integer parameters are negative
	 */
	void addUser(String userId, String gender, int weight, int height, int age, String name) throws UserAlreadyExistsException, InvalidValueException;
	
	/**
	 * Used to update the Information on the User.
	 * @param userId is the Id of the User to be updated
	 * @param weight is the new weight of the User
	 * @param height is the new height of the User
	 * @param age is the new age of the User
	 * @throws UserDoesNotExistException if the User is not on the platform yet.
	 * @throws InvalidValueException if gender != 'M' or 'F' and if the integer parameters are negative
	 */
	void updateUserInfo(String userId, int weight, int height, int age) throws UserDoesNotExistException, InvalidValueException;
	
	/**
	 * Removes the User with userId from the platform.
	 * @param userId is the Id of the User to be removed
	 * @throws UserDoesNotExistException if the User does not exist
	 */
	void removeUser(String userId) throws UserDoesNotExistException;
	
	/**
	 * Gets a User with userId.
	 * @param userId the User to return
	 * @return a user object
	 * @throws UserDoesNotExistException if the User does not yet exist
	 */
	UserNonModifiable getUser(String userId) throws UserDoesNotExistException;
	
	/**
	 * Create a new Activity.
	 * @param actId the unique Id for this activity
	 * @param MET the Metabolic equivalent of the Activity.
	 * @param name the name of the Activity
	 * @throws ActivityAlreadyExistsException if an activity with actId already exists
	 * @throws InvalidValueException if MET < 0
	 */
	void createActivity(String actId, int MET, String name) throws ActivityAlreadyExistsException, InvalidValueException;
	
	/**
	 * Add an activity to a User.
	 * @param userId is the Id of the User
	 * @param actId is the Id of the Activity
	 * @param duration is how long the activity was performed
	 * @throws InvalidTimeException if the time is negative
	 * @throws UserDoesNotExistException if no User with userId exists
	 * @throws ActivityDoesNotExistException if no Activity with actId exists
	 */
	void addWorkoutToUser(String userId, String actId, int duration) throws InvalidTimeException, UserDoesNotExistException, ActivityDoesNotExistException;
	
	/**
	 * Gets the workouts of a certain User.
	 * @param userId the User to get the Workouts from
	 * @param type 'T' or 'C', T for chronological ordering and C for caloric ordering
	 * @return an Iterator of the Workouts
	 * @throws InvalidOperationException if type != 'T' or 'C'
	 * @throws UserDoesNotExistException if no User with userId exists
	 * @throws AthleteHasntTrainedException if the User hasnt worked out yet
	 */
	Iterator<PerformedActivity> getWorkouts(String userId, char type) throws InvalidOperationException, UserDoesNotExistException, AthleteHasntTrainedException;
	
	/**
	 * Adds steps to a Users count.
	 * @param userId is the Id of the User to be updated
	 * @param steps is the number of steps to add
	 * @throws UserDoesNotExistException if no User with userId exists
	 * @throws InvalidValueException if the steps < 0
	 */
	void updateSteps(String userId, int steps) throws UserDoesNotExistException, InvalidValueException;
	
	/**
	 * Creates a new Group.
	 * @param groupId the unique Id of the Group
	 * @param name the name of the Group
	 * @throws GroupAlreadyExistsException if a Group with groupId already exists
	 */
	void createGroup(String groupId, String name) throws GroupAlreadyExistsException;
	
	/**
	 * Adds a User to a Group.
	 * @param userId is the Id of the User to be added
	 * @param groupId is the Id of the Group to add the User to
	 * @throws UserDoesNotExistException if no User with userId exists
	 * @throws GroupDoesNotExistException if no Group with groupId exists
	 * @throws UserAlreadyInGroupException if the User is already in a group
	 */
	void addUserToGroup(String userId, String groupId) throws UserDoesNotExistException, GroupDoesNotExistException, UserAlreadyInGroupException;
	
	/**
	 * Removes a User from a group
	 * @param userId the Id of the User to remove
	 * @param groupId the Id of the Group
	 * @throws UserDoesNotExistException if no User with userId exists
	 * @throws GroupDoesNotExistException if no Group with groupId exists
	 * @throws UserNotInGroupException if the User has no group
	 */
	void forfeitFromGroup(String userId, String groupId) throws UserDoesNotExistException, GroupDoesNotExistException, UserNotInGroupException;
	
	/**
	 * Gets a Group from the platform
	 * @param groupId the Id of the Group to get
	 * @return the Group with groupId
	 * @throws GroupDoesNotExistException if no Group with groupId exists
	 */
	GroupNonModifiable getGroup(String groupId) throws GroupDoesNotExistException;
	
	/**
	 * Gets all the Users from a group in insertion order
	 * @param groupId is the Id of the Group
	 * @return an Iterator of Users
	 * @throws GroupDoesNotExistException if no Group with groupId exists
	 * @throws EmptyGroupException if the Group is empty
	 */
	Iterator<UserNonModifiable> listGroup(String groupId) throws GroupDoesNotExistException, EmptyGroupException;
	
	/**
	 * Returns an Iterator of Groups ordered by how many steps each group has taken.
	 * @return an Iterator of Groups ordered by how many steps each group has taken
	 * @throws NoGroupsException if no groups exist yet
	 */
	Iterator<GroupNonModifiable> listWalkers() throws NoGroupsException;
	
	/**
	 * Returns an Iterator of Groups ordered by how many calories each group has burned.
	 * @return an Iterator of Groups ordered by how many calories each group has burned
	 * @throws NoGroupsException if no groups exist yet
	 */
	Iterator<GroupNonModifiable> listWarriors() throws NoGroupsException;
}
