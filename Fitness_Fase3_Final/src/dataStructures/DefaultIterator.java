/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package dataStructures;

public class DefaultIterator<E> implements Iterator<E>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private E[] data;
	private int currentPosition;
	private int size;
	
	public DefaultIterator(E[] inputData, int size){
		data = inputData;
		currentPosition = 0;
		this.size = size;
	}
	
	@Override
	public boolean hasNext() {
		if(currentPosition < size)
			return true;
		return false;
	}

	@Override
	public E next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		return data[currentPosition++];
	}

	@Override
	public void rewind() {
		currentPosition = 0;
	}

}
