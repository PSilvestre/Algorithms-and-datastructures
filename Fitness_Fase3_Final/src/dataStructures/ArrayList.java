/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */

package dataStructures;

import dataStructures.DefaultIterator;
import dataStructures.Iterator;

public class ArrayList<E> implements List<E>{

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_ARRAY_SIZE = 32;
	private static final short GROWTH = 2;
	
	
	private E[] data;
	private int size;
	
	public ArrayList(){
		this(DEFAULT_ARRAY_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int size){
		data = (E[]) new Object[size];
		size = 0;
	}
	
	

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new DefaultIterator<E>(data, size);
	}

	@Override
	public E getFirst() throws EmptyListException {
		if(size == 0)
			throw new EmptyListException();
		return data[0];
	}

	@Override
	public E getLast() throws EmptyListException {
		if(size == 0)
			throw new EmptyListException();
		return data[this.size() - 1];
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if(position < 0 || position > this.size() - 1)
			throw new InvalidPositionException();
		
		return data[position];
	}

	@Override
	public int find(Object element) {
		for(int i = 0; i < this.size(); i++)
			if(element.equals(data[i]))
				return i;
		return -1;
	}

	@Override
	public void addFirst(Object element) {
		add(0, element);
	}

	@Override
	public void addLast(Object element) {
		add(this.size(), element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int position, Object element) throws InvalidPositionException {
		if(position < 0 || position > data.length -1)
			throw new InvalidPositionException();
		if(this.size() == data.length)
			grow();
		for(int i = this.size()-1; i >= position; i--)
			data[i+1] = data[i];
		data[position] = (E) element;
		size++;
	}

	@Override
	public E removeFirst() throws EmptyListException {
		return remove(0);
	}

	@Override
	public E removeLast() throws EmptyListException {
		return remove(this.size());
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if(position < 0 || position > this.size() -1)
			throw new InvalidPositionException();
		E temp = data[position];
		
		for(int i = position; i < this.size() - 1; i++)
			data[i] = data[i+1];
		data[this.size()-1] = null;
		size--;
		return temp;
	}

	@Override
	public boolean remove(Object element) {
		int position = find(element);
		if(position == -1)
			return false;
		remove(position);
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	protected void grow(){
		E[] temp = (E[]) new Object[data.length * GROWTH];
		for(int i = 0; i < this.size(); i++)
			temp[i] = data[i];
		data = temp;
	}
}
