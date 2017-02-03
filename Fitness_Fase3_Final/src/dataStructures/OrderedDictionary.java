package dataStructures;  

public interface OrderedDictionary<K extends Comparable<K>, V> 
    extends Dictionary<K,V>
{                                                                   

    // Returns the entry with the smallest key in the dictionary.
    Entry<K,V> minEntry( ) throws EmptyDictionaryException;

    // Returns the entry with the largest key in the dictionary.
    Entry<K,V> maxEntry( ) throws EmptyDictionaryException;

    /**
     * Returns an iterator of the entries in the dictionary 
     * which preserves the key order relation.
     * @return  key-order iterator of the entries in the dictionary
     */
    Iterator<Entry<K,V>> iterator( );  
    
    /**
     * Returns an iterator of the entries in the dictionary 
     * which preserves the key order relation, only in decreasing order.
     * @return reverse key-order iterator of the entries in the dictionary
     */
    Iterator<Entry<K,V>> iteratorReverse( );  

} 