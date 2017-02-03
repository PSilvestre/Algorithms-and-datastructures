/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

import java.io.Serializable;

import dataStructures.Iterator;
import dataStructures.AVLTree;
import dataStructures.Dictionary;
import dataStructures.DoublyLinkedList;
import dataStructures.Entry;
import dataStructures.List;
import dataStructures.OrderedDictionary;

public class DefaultGroup implements Group, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private OrderedDictionary<String, UserNonModifiable> membersByName; //Assuming unique names
	private OrderedDictionary<String, UserNonModifiable> membersById;
	private String id;
	private String name;
	
	private int cumulativeCal;
	private int cumulativeSteps;
	
	
	
	public DefaultGroup(String id, String name){
		this.membersById = new AVLTree<String, UserNonModifiable>();
		this.membersByName = new AVLTree<String, UserNonModifiable>();
		this.id = id;
		this.name = name;
		
		this.cumulativeCal = 0;
		this.cumulativeSteps = 0;
	}



	@Override
	public String getId() {
		return id;
	}



	@Override
	public String getName() {
		return name;
	}



	@Override
	public Iterator<UserNonModifiable> getMembers() {
		return  constructMembersByNameIterator();
	}


	@Override
	public boolean hasMember(String userId) {
		return find(userId) != null;
	}



	@Override
	public void addMember(User u) {
		membersByName.insert(u.getName(), u);
		membersById.insert(u.getId(), u);
		
		this.cumulativeCal += u.getCalorieCount();
		this.cumulativeSteps += u.getSteps();
	}



	@Override
	public void removeMember(String userId) {
		UserNonModifiable u = find(userId);
		this.cumulativeCal -= u.getCalorieCount();
		this.cumulativeSteps -= u.getSteps();
		membersByName.remove(u.getName());
		membersById.remove(userId);
		
	}



	@Override
	public int getCumulativeCalories() {
		return cumulativeCal;
	}



	@Override
	public int getCumulativeSteps() {
		return cumulativeSteps;
	}



	@Override
	public void addCalories(int calories) {
		cumulativeCal += calories;
	}



	@Override
	public void addSteps(int steps) {
		cumulativeSteps += steps;
	}

	@Override
	public boolean isEmpty() {
		return membersById.isEmpty();
	}
	
	private UserNonModifiable find(String userId){
		UserNonModifiable u;
		Iterator<Entry<String, UserNonModifiable>> users = membersById.iterator();
		while(users.hasNext()){
			u = users.next().getValue();
			if(u.getId().equalsIgnoreCase(userId))
				return u;
		}
		return null;
	}
	
	private Iterator<UserNonModifiable> constructMembersByNameIterator() {
		Iterator<Entry<String, UserNonModifiable>> it = membersByName.iterator();
		List<UserNonModifiable> usersOrdered = new DoublyLinkedList<UserNonModifiable>();
		while(it.hasNext()){
			usersOrdered.addLast(it.next().getValue());
			
		}
		return usersOrdered.iterator();
	}

}
