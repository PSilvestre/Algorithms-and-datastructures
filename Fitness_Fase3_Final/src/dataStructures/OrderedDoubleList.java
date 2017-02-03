package dataStructures;

import dataStructures.Iterator;
import dataStructures.DListNode;
import dataStructures.DoublyLLIterator;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *  Node at the head of the list.
     */
    protected DListNode<Entry<K, V>> head;

    /**
     * Node at the tail of the list.
     */
    protected DListNode<Entry<K, V>> tail;

    /**
     * Number of elements in the list.
     */
    protected int currentSize;
	
	public OrderedDoubleList(){
		 head = null;
	     tail = null;
	     currentSize = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public V find(K key) {
		V value = null;
		if(!this.isEmpty()){
			DListNode<Entry<K, V>> node = findNearest(key);
			if(node != null && node.getElement().getKey().compareTo(key) == 0)
				value = node.getElement().getValue();
			}
		return value;
	}
	
	@Override
	public V insert(K key, V value) {
		Entry<K, V> entry = new EntryClass<K, V>(key, value);
        if(this.isEmpty()){
        	addFirst(entry);
        	return null;
        }
        
        DListNode<Entry<K,V>> found = findNearest(key);
        if(found == null){
        	addLast(entry);
        	return null;
        }
        
        if(found.getElement().getKey().compareTo(key) == 0){
        	V oldValue = found.getElement().getValue();
        	found.setElement(entry);
        	return oldValue;
        }
        //Our key must be bigger
        if(found == head)
        	addFirst(entry);
        else
        	addMiddle(entry, found);
        return null;
	}
	
	@Override
	public V remove(K key) {
		if(this.isEmpty())
			return null;
		DListNode<Entry<K, V>> found = findNearest(key);
		V oldValue = null;
		if(found != null && found.getElement().getKey().compareTo(key) == 0){
			oldValue = found.getElement().getValue();
			if(found == head)
				removeFirst();
			else if(found == tail)
				removeLast();
			else
				removeMiddle(found);
		}
      
       return oldValue;
	}


	@Override
	public Iterator<Entry<K, V>> iterator() {
		 return new DoublyLLIterator<Entry<K,V>>(head, tail);
	}
	@Override
	public Iterator<Entry<K, V>> iteratorReverse() {
		 return new DoublyLLIteratorReverse<Entry<K,V>>(head, tail);
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		return tail.getElement();
	}
	
	protected void addFirst(Entry<K, V> element) {
		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(element, null, head);
		if (this.isEmpty())
			tail = newNode;
		else
			head.setPrevious(newNode);
		head = newNode;
		currentSize++;
	}

	protected void addLast(Entry<K, V> element) {

		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(element, tail, null);
		if (this.isEmpty())
			head = newNode;
		else
			tail.setNext(newNode);
		tail = newNode;
		currentSize++;
	}

	
	protected void addMiddle(Entry<K, V> element, DListNode<Entry<K, V>> nextNode) {

		DListNode<Entry<K, V>> PrevNode = nextNode.getPrevious();
		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(element, PrevNode, nextNode);
		PrevNode.setNext(newNode);
		nextNode.setPrevious(newNode);
		currentSize++;
	}
	
	protected void removeFirst() {
		head = head.getNext();
		if (head == null)
			tail = null;
		else
			head.setPrevious(null);
		currentSize--;
	}

	protected void removeLast() {
		tail = tail.getPrevious();
		if (tail == null)
			head = null;
		else
			tail.setNext(null);
		currentSize--;
	}

	protected void removeMiddle(DListNode<Entry<K, V>> node) {
		DListNode<Entry<K, V>> prevNode = node.getPrevious();
		DListNode<Entry<K, V>> nextNode = node.getNext();
		prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);
		currentSize--;
	}
	
	protected DListNode<Entry<K, V>> findNearest(K key) {
		DListNode<Entry<K, V>> node = head;
		while (node != null && node.getElement().getKey().compareTo(key) < 0)
			node = node.getNext();
		return node;
	}
	
}
