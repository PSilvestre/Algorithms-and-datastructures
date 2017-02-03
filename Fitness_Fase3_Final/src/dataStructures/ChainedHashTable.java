package dataStructures;

import dataStructures.Iterator;

/**
 * Chained Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    public ChainedHashTable( int capacity )
    {
    	createTable(capacity);
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
    	if (this.isFull())
			this.rehash();
		V oldValue = table[hash(key)].insert(key, value);
		if (oldValue == null)
			currentSize++;

		return oldValue;
    }

    @Override
    public V remove( K key )
    {
    	int index = hash(key);
        if(!table[index].isEmpty()){
        	V value = table[index].remove(key);
        	if(value != null)
        		currentSize--;
        	return value;
        }
        return null;
        	
    }

    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
       return new ChainedHashTableIterator<K, V>(table);
    } 
    
    protected void rehash(){
    	Dictionary<K, V>[] temp = table;
    	createTable(maxSize * 2);
    	maxSize *= 2;
    	for(int i = 0; i < temp.length; i++){
    		if(!temp[i].isEmpty()){
    			Iterator<Entry<K, V>> it = temp[i].iterator();
    			while(it.hasNext()){
    				Entry<K, V> e = it.next();
    				insert(e.getKey(), e.getValue());
    			}
    		}
    	}
    }
    
    @SuppressWarnings("unchecked")
	protected void createTable(int cap){
    	 int arraySize = HashTable.nextPrime((int) (1.1 * cap));
         // Compiler gives a warning.
         table = (Dictionary<K,V>[]) new Dictionary[arraySize];
         for ( int i = 0; i < arraySize; i++ )
             table[i] = new OrderedDoubleList<K,V>();
    }
}
































