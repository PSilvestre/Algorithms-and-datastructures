/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

import java.io.Serializable;

import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.OrderedDictionary;
import dataStructures.AVLTree;
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.DoublyLinkedList;
import dataStructures.Entry;
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

public class FitnessClass implements Fitness, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final String MALE_IDENTIFIER = "M";
	private static final String FEMALE_IDENTIFIER = "F";
	private static final String MALE_GENDER = "Masculino";
	private static final String FEMALE_GENDER = "Feminino";
	
	private static final char CHRONOLOGIC_ORDERING = 'T';
	private static final char CALORIE_ORDERING = 'C';
	
	private static final int DEFAULT_EXPECTED_USERS = 50;
	private static final int DEFAULT_EXPECTED_GROUPS = 20;
	private static final int DEFAULT_EXPECTED_ACTIVITIES = 50;
	
	
	private Dictionary<String, User> usersById; 
	private Dictionary<String, Activity> activities;
	private Dictionary<String, Group> groups;
	private OrderedDictionary<String, User> usersByName;  //used to keep track of taken names of users
	private OrderedDictionary<String, Group> groupsByName;  //used to keep track of taken names of groups
	
	private OrderedDictionary<Integer, List<GroupNonModifiable>> groupsByCalories;
	private OrderedDictionary<Integer, List<GroupNonModifiable>> groupsBySteps;
	
	
	public FitnessClass(){
		usersById = new ChainedHashTable<String, User>(DEFAULT_EXPECTED_USERS);
		activities = new ChainedHashTable<String, Activity>(DEFAULT_EXPECTED_ACTIVITIES);
		groups = new ChainedHashTable<String, Group>(DEFAULT_EXPECTED_GROUPS); //Quicker access doesnt justify memory usage
		usersByName = new AVLTree<String, User>();
		groupsByName = new AVLTree<String, Group>();
		groupsByCalories = new AVLTree<Integer, List<GroupNonModifiable>>();
		groupsBySteps = new AVLTree<Integer, List<GroupNonModifiable>>();
	}
	
	@Override
	public void addUser(String userId, String gender, int weight, int height, int age,  String name) throws UserAlreadyExistsException, InvalidValueException {
		if(!(gender.equalsIgnoreCase(MALE_IDENTIFIER)  || gender.equalsIgnoreCase(FEMALE_IDENTIFIER)) || weight <= 0 || height <= 0 || age <= 0)
			throw new InvalidValueException();
		if(usersById.find(userId) != null || usersByName.find(name) != null)
			throw new UserAlreadyExistsException();
		User u;
		if(gender.equalsIgnoreCase(MALE_IDENTIFIER))
			u = new DefaultUser(userId, MALE_GENDER, height, weight, age, name);
		else
			u = new DefaultUser(userId, FEMALE_GENDER, height, weight, age, name);
		
		usersById.insert(userId, u);
		usersByName.insert(name, u);
	}

	@Override
	public void updateUserInfo(String userId, int weight, int height, int age) throws UserDoesNotExistException, InvalidValueException {
		User u = usersById.find(userId);
		if(u == null)
			throw new UserDoesNotExistException();
		if(weight < 0 || height < 0 || age < 0)
			throw new InvalidValueException();
		u.setWeight(weight);
		u.setHeight(height);
		u.setAge(age);
	}

	@Override
	public void removeUser(String userId) throws UserDoesNotExistException {
		User u = usersById.find(userId);
		if(u == null)
			throw new UserDoesNotExistException();
		if(u.isInGroup()){
			Group g = groups.find(u.getGroup().getId());
			int oldCal = g.getCumulativeCalories();
			int oldStep = g.getCumulativeSteps();
			g.removeMember(userId);
			u.setGroup(null);
			updateComp(oldCal, g.getCumulativeCalories(), g, groupsByCalories);
			updateComp(oldStep, g.getCumulativeSteps(), g, groupsBySteps);
		}
		
		usersById.remove(userId);
		usersByName.remove(u.getName());
	}

	@Override
	public UserNonModifiable getUser(String userId) throws UserDoesNotExistException {
		User u = usersById.find(userId);
		if(u == null)
			throw new UserDoesNotExistException();
		return u;
	}

	@Override
	public void createActivity(String actId, int MET, String name) throws ActivityAlreadyExistsException, InvalidValueException {
		if(activities.find(actId) != null)
			throw new ActivityAlreadyExistsException();
		if(MET < 0)
			throw new InvalidValueException();
		Activity a = new Workout(actId, MET, name);
		activities.insert(actId, a);
		
		
	}

	@Override
	public void addWorkoutToUser(String userId, String actId, int duration)
			throws InvalidTimeException, UserDoesNotExistException, ActivityDoesNotExistException {
		if(duration <= 0)
			throw new InvalidTimeException();
		User u = usersById.find(userId);
		if(u == null)
			throw new UserDoesNotExistException();
		Activity a = activities.find(actId);
		if(a == null)
			throw new ActivityDoesNotExistException();
		
		int calBurned = Calories.calculateCalories(u.getWeight(), u.getHeight(), u.getGender().charAt(0), u.getAge(), a.getMET(), duration);
		PerformedActivity act = new PerformedWorkout(a, duration, calBurned);
		
		if(u.isInGroup()){
			int oldGroupCal = u.getGroup().getCumulativeCalories();
			updateComp(oldGroupCal, oldGroupCal + calBurned, u.getGroup(), groupsByCalories);
		}
		u.addActivity(act);
	}

	@Override
	public Iterator<PerformedActivity> getWorkouts(String userId, char type)
			throws InvalidOperationException, UserDoesNotExistException, AthleteHasntTrainedException {
		if(!(type == CHRONOLOGIC_ORDERING || type == CALORIE_ORDERING))
			throw new InvalidOperationException();
		User u = usersById.find(userId);
		if(u == null)
			throw new UserDoesNotExistException();
		if(!u.hasWorkedOut())
			throw new AthleteHasntTrainedException();
		if(type == CHRONOLOGIC_ORDERING)
			return u.getWorkouts();
		else
			return u.getWorkoutsByCalories();
	}

	@Override
	public void updateSteps(String userId, int steps) throws UserDoesNotExistException, InvalidValueException {
		User u = usersById.find(userId);
		if(u == null)
			throw new UserDoesNotExistException();
		if(steps < 0)
			throw new InvalidValueException();
		if(u.isInGroup()){
			int oldGroupSteps = u.getGroup().getCumulativeSteps();
			updateComp(oldGroupSteps,oldGroupSteps + steps , u.getGroup(), groupsBySteps);
		}
		
		u.addSteps(steps);
	}

	@Override
	public void createGroup(String groupId, String name) throws GroupAlreadyExistsException {
		if(groups.find(groupId) != null || groupsByName.find(name) != null)
			throw new GroupAlreadyExistsException();
		Group newGroup =  new DefaultGroup(groupId, name);
		groups.insert(groupId, newGroup);
		groupsByName.insert(name, newGroup);
		insertNewGroupToComp(newGroup, groupsBySteps);
		insertNewGroupToComp(newGroup, groupsByCalories);
	}

	@Override
	public void addUserToGroup(String userId, String groupId)
			throws UserDoesNotExistException, GroupDoesNotExistException, UserAlreadyInGroupException {
		Group g = groups.find(groupId);
		if(g == null)
			throw new GroupDoesNotExistException();
		User u = usersById.find(userId);
		if(u == null)
			throw new UserDoesNotExistException();
		if(u.isInGroup())
			throw new UserAlreadyInGroupException();
		
		int oldCal = g.getCumulativeCalories();
		int oldStep = g.getCumulativeSteps();
		g.addMember(u);
		u.setGroup(g);
		updateComp(oldCal, g.getCumulativeCalories(), g, groupsByCalories);
		updateComp(oldStep, g.getCumulativeSteps(), g, groupsBySteps);
		
		
		
		
		
	}

	@Override
	public void forfeitFromGroup(String userId, String groupId)
			throws UserDoesNotExistException, GroupDoesNotExistException, UserNotInGroupException {
		Group g = groups.find(groupId);
		if(g == null)
			throw new GroupDoesNotExistException();
		User u = usersById.find(userId);
		if(u == null)
			throw new UserDoesNotExistException();
		if(!u.isInGroup() || !u.getGroup().getId().equalsIgnoreCase(g.getId()))
			throw new UserNotInGroupException();
		
		int oldCal = g.getCumulativeCalories();
		int oldStep = g.getCumulativeSteps();
		g.removeMember(userId);
		u.setGroup(null);
		updateComp(oldCal, g.getCumulativeCalories(), g, groupsByCalories);
		updateComp(oldStep, g.getCumulativeSteps(), g, groupsBySteps);
		
		
	}

	@Override
	public GroupNonModifiable getGroup(String groupId) throws GroupDoesNotExistException {
		Group g = groups.find(groupId);
		if(g == null)
			throw new GroupDoesNotExistException();
		return g;
	}

	@Override
	public Iterator<UserNonModifiable> listGroup(String groupId) throws GroupDoesNotExistException, EmptyGroupException {
		Group g = groups.find(groupId);
		if(g == null)
			throw new GroupDoesNotExistException();
		if(g.isEmpty())
			throw new EmptyGroupException();
		return  g.getMembers();
	}

	@Override
	public Iterator<GroupNonModifiable> listWalkers() throws NoGroupsException {
		if(groups.isEmpty())
			throw new NoGroupsException();
		return constructIterator(groupsBySteps);
	}

	@Override
	public Iterator<GroupNonModifiable> listWarriors() throws NoGroupsException {
		if(groups.isEmpty())
			throw new NoGroupsException();
		return constructIterator(groupsByCalories);
	}
	
	private Iterator<GroupNonModifiable> constructIterator(OrderedDictionary<Integer, List<GroupNonModifiable>> competition){
		Iterator<Entry<Integer, List<GroupNonModifiable>>> it = competition.iteratorReverse();
		List<GroupNonModifiable> groupsOrdered = new DoublyLinkedList<GroupNonModifiable>();
		while(it.hasNext()){
			Iterator<GroupNonModifiable> node = it.next().getValue().iterator();
			while(node.hasNext()){
				groupsOrdered.addLast(node.next());
			}
			
		}
		return groupsOrdered.iterator();
	}
	
	private void updateComp(int oldVal, int newVal, GroupNonModifiable g, OrderedDictionary<Integer, List<GroupNonModifiable>> comp){
		if(oldVal != newVal){
			List<GroupNonModifiable> groupsAt = comp.find(oldVal);
			groupsAt.remove(g);
			if(groupsAt.isEmpty())
				comp.remove(oldVal);
			if(comp.find(newVal) == null) // if node doesnt exist then create it
				comp.insert(newVal, new DoublyLinkedList<GroupNonModifiable>());
			groupsAt = comp.find(newVal);
			groupsAt.addLast(g);
		}
	}
	
	private void insertNewGroupToComp(GroupNonModifiable g, OrderedDictionary<Integer, List<GroupNonModifiable>> comp){
		List<GroupNonModifiable> groupsAt = comp.find(0);
		if(groupsAt == null)
			comp.insert(0, new DoublyLinkedList<GroupNonModifiable>());
		comp.find(0).addLast(g);
	}
	
	
}
