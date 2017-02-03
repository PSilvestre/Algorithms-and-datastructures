package dataStructures;

import dataStructures.NoSuchElementException;

public class DoublyLLIteratorReverse<E> implements TwoWayIterator<E>
{

	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;


    /** 
     * Node with the first element in the iteration.
     */
    protected DListNode<E> firstNode;

    /**
     * Node with the last element in the iteration.
     */
    protected DListNode<E> lastNode;

    /**
     * Node with the next element in the iteration.
     */
    protected DListNode<E> nextToReturn;

    /**
     * Node with the previous element in the iteration.
     */
    protected DListNode<E> prevToReturn;


    /**
     * DoublyLLIterator constructor
     * @param first - Node with the first element of the iteration
     * @param last - Node with the last element of the iteration
     */
    public DoublyLLIteratorReverse( DListNode<E> first, DListNode<E> last )
    {
        firstNode = first;
        lastNode = last;
        this.rewind();
    }      


    @Override
    public void rewind( )
    {
        nextToReturn = lastNode;
        prevToReturn = null;
    }


    @Override
    public void fullForward( )
    {
        prevToReturn = firstNode;
        nextToReturn = null;
    }


    @Override
    public boolean hasNext( )
    {
        return nextToReturn != null;
    }


    @Override
    public boolean hasPrevious( )
    {
        return prevToReturn != null;
    }


    @Override
    public E next( ) throws NoSuchElementException{
        if ( !this.hasPrevious() )
            throw new NoSuchElementException();

        E element = nextToReturn.getElement();
        prevToReturn = nextToReturn.getNext();
        nextToReturn = nextToReturn.getPrevious();
        return element;
    }


    @Override
    public E previous( ) throws NoSuchElementException
    {
        if ( !this.hasNext() )
            throw new NoSuchElementException();

        E element = prevToReturn.getElement();
        nextToReturn = prevToReturn.getPrevious();
        prevToReturn = prevToReturn.getNext();
        return element;
    }


}
