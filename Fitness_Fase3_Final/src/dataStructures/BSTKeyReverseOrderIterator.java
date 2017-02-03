package dataStructures;

public class BSTKeyReverseOrderIterator<K, V> implements Iterator<Entry<K, V>>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<BSTNode<K, V>> path;
	private BSTNode<K, V> root;
	
	public BSTKeyReverseOrderIterator(BSTNode<K, V> root){
		this.root = root;
		rewind();
			
	}

	@Override
	public boolean hasNext() {
		return !path.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if(!hasNext()) throw new NoSuchElementException();
		BSTNode<K, V> node = path.pop();
		BSTNode<K, V> result = node;
		if(node.getLeft() != null){
			node = node.getLeft();
			getToLocalMax(node);
		}
		return result.getEntry();
	}

	@Override
	public void rewind() {
		path = new StackInList<BSTNode<K, V>>();
		getToLocalMax(root);
	}
	
	private void getToLocalMax(BSTNode<K, V> localRoot){
		BSTNode<K, V> curr = localRoot;
		while(curr != null){
			path.push(curr);
			curr = curr.getRight();
		}
	}
}
