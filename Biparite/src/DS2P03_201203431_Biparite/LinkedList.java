package DS2P03_201203431_Biparite;

import java.util.Iterator;

public class LinkedList<T> {
	
	private LinkedNode<T> _head;
	private int _size;
	
	
	public boolean isEmpty() {
		return (this.size() == 0);
	}
	public boolean isFull() {
		return false;
	}
	
	public boolean add(T anElement) {
		LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement,this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size()+1);
		return true;
	}

	public Object removeAny() {
		if (this.isEmpty()){
			return null;
		}else {
			T removedElement = this.head().element();
			this.setHead(this.head().next());
			this.setSize(this.size()-1);
			return removedElement;
		}
	}
	
	public int size() {
		return _size;
	}

	public void setSize(int _size) {
		this._size = _size;
	}

	public LinkedNode<T> head() {
		return _head;
	}

	public void setHead(LinkedNode<T> _head) {
		this._head = _head;
	}
	public IteratorForLinkedList iterator() {
		return new IteratorForLinkedList();
	}
	public class IteratorForLinkedList implements Iterator<T>{
		
		private LinkedNode<T> _nextNode;
		public IteratorForLinkedList() {
			this.setNextNode(LinkedList.this.head());
		}
			
		public LinkedNode<T> get_nextNode() {
			return _nextNode;
		}
		@Override
		public boolean hasNext() {
			return (this.get_nextNode()!=null);
		}
		@Override
		public T next() {
			T nextElement = this.get_nextNode().element();
			this.setNextNode(this.get_nextNode().next());
			return nextElement;
		}
		
		public void setNextNode(LinkedNode<T> _nextNode) {
			this._nextNode = _nextNode;
		}
		
	}
}
