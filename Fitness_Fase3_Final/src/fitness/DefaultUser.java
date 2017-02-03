/**Updated
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

import java.io.Serializable;

import dataStructures.AVLTree;
import dataStructures.Dictionary;
import dataStructures.DoublyLinkedList;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.OrderedDictionary;
import dataStructures.Stack;
import dataStructures.StackInList;


public  class DefaultUser implements User, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String gender;
	private int height;
	private int weight;
	private int age;
	
	private int steps;
	private int caloriesBurned;
	private Stack<PerformedActivity> activities;
	private OrderedDictionary<Integer, List<PerformedActivity>> activitiesByCalories;
	
	
	private Group group;
	
	public DefaultUser(String id, String gender, int height, int weight, int age, String name){
		this.id = id;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.name = name;
		
		this.steps = 0;
		this.caloriesBurned = 0;
		this.activities = new StackInList<PerformedActivity>();
		this.activitiesByCalories = new AVLTree<Integer, List<PerformedActivity>>();
		this.group = null;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String newId) {
		this.id = newId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String newName) {
		this.name = newName;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int newHeight) {
		this.height = newHeight;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int newAge) {
		this.age = newAge;
	}

	@Override
	public int getSteps() {
		return steps;
	}

	@Override
	public void addSteps(int newSteps) {
		this.steps += newSteps;
		if(group != null)
			group.addSteps(newSteps);
	}

	@Override
	public Iterator<PerformedActivity> getWorkouts() {
		return activities.iterator();
	}

	@Override
	public void addActivity(PerformedActivity act) {
		
		activities.push(act);
		updateActsByCalories(act);
		if(group != null)
			group.addCalories(act.getCalories());
		caloriesBurned += act.getCalories();
	}
	
	@Override
	public int getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(int newWeight) {
		this.weight = newWeight;
	}

	@Override
	public String getGender() {
		return gender;
	}

	@Override
	public boolean hasWorkedOut() {
		return !activities.isEmpty();
	}

	@Override
	public Iterator<PerformedActivity> getWorkoutsByCalories() {
		return constructActivitiesByCaloriesIterator();
	}

	@Override
	public boolean isInGroup(){
		return group != null;
	}
	
	@Override
	public int getCalorieCount() {
		return caloriesBurned;
	}

	@Override
	public void setGroup(Group g) {
		this.group = g;
	}

	@Override
	public GroupNonModifiable getGroup() {
		return group;
	}
	
	private void updateActsByCalories(PerformedActivity a){
		List<PerformedActivity> actsAt = activitiesByCalories.find(a.getCalories());
		if(actsAt == null)
			activitiesByCalories.insert(a.getCalories(), new DoublyLinkedList<PerformedActivity>());
		actsAt = activitiesByCalories.find(a.getCalories());
		actsAt.addLast(a);
	}

	
	private Iterator<PerformedActivity> constructActivitiesByCaloriesIterator(){
		Iterator<Entry<Integer, List<PerformedActivity>>> it = activitiesByCalories.iteratorReverse();
		List<PerformedActivity> actsOrdered = new DoublyLinkedList<PerformedActivity>();
		while(it.hasNext()){
			Iterator<PerformedActivity> node = it.next().getValue().iterator();
			while(node.hasNext())
				actsOrdered.addLast(node.next());
			
		}
		return actsOrdered.iterator();
	}
	
}
