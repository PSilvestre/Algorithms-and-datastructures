package dataStructures;

import dataStructures.Iterator;
import dataStructures.NoSuchElementException;


public class ChainedHashTableIterator<K, V> implements Iterator<Entry<K, V>>{
	
	private static final long serialVersionUID = 1L;
	
	private Dictionary<K, V>[] table;
	private int current;
	private Iterator<Entry<K, V>> it;
	
	public ChainedHashTableIterator(Dictionary<K, V>[] table){
		this.table = table;
		this.rewind();
	}
	
	@Override
	public boolean hasNext() {
		if(it == null)
			return false;
		return it.hasNext();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if(!it.hasNext())
			throw new NoSuchElementException();
		Entry<K,V> entry = it.next();
		if(!hasNext())
			findNextIt(current+1);
		return entry;
	}

	@Override
	public void rewind() {
		current = 0;
		findNextIt(current);
	}
	
	private void findNextIt(int from){
		it = null;
		int i = from;
		while(i < table.length && it == null){
			if(!table[i].isEmpty()){
				it = table[i].iterator();
				current = i;
			}
			i++;
		}
	}

}
