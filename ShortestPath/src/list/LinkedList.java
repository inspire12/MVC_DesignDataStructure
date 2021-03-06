package list;

import graph.Edge;

public class LinkedList<T> {
	
	private LinkedNode<T> _head;
	private int _size;

    public LinkedList (){
        this.setSize(0);
        this.setHead(null);
    }

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

	public T removeAny() {
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
		return this._size;
	}

	public void setSize(int newSize) {
		this._size = newSize;
	}

	public LinkedNode<T> head() {
		return this._head;
	}

	public void setHead(LinkedNode _head) {
		this._head = _head;
	}

	public IteratorForLinkedList iterator() {
		return new IteratorForLinkedList();
	}

	public class IteratorForLinkedList implements Iterator{
		private LinkedNode<T> _nextNode;
		public IteratorForLinkedList() {
			this.setNextNode(head());
		}
			
		public LinkedNode<T> nextNode() {
			return this._nextNode;
		}
        public void setNextNode(LinkedNode<T> newNextNode) {
            this._nextNode = newNextNode;
        }

        @Override
		public boolean hasNext() {
			return (this.nextNode()!=null);
		}
		@Override
		public T next() {
            T nextElement = this.nextNode().element();
            this.setNextNode(this.nextNode().next());
            return nextElement;
        }
	}
}
