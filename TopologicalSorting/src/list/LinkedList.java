package list;

public class LinkedList<T> implements  List<T>{
	
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

    @Override
    public T firstElement() {
        if( this.isEmpty()){
            return null;
        }
        return this.head().element();
    }

    @Override
    public T lastElement() {
        LinkedNode<T> current  = this.head();
        if(current == null){
            return null;
        }
        while(current.next() != null){
            current = current.next();
        }
        return current.element();
    }

    @Override
    public T elementAtIndex(int anIndex) {
        if (anIndex < 0 || anIndex >= this.size()){
            return null;
        }
        LinkedNode<T> current = this.head();
        for (int i=0; i<anIndex; i++){
            current = current.next();
        }
        return current.element();
    }

    public boolean add(T anElement) {
        return this.addToFirst(anElement);
	}

    @Override
    public boolean addToFirst(T anElement) {
        LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement,this.head());
        this.setHead(newHeadNode);
        this.setSize(this.size()+1);
        return true;
    }

    @Override
    public boolean addToLast(T anElement) {
        if(this.isEmpty()){
            return this.addToFirst(anElement);
        }else{
            LinkedNode<T> current = this.head();
            while (current.next() != null){
                current = current.next();
            }
            current.setNext(new LinkedNode<T>(anElement, null));
            this.setSize(this.size()+1);
            return true;
        }
    }

    @Override
    public boolean addAtIndex(T anElement, int anIndex) {
        if(anIndex<0 || anIndex > this.size()){
            return false;
        }else if(anIndex == 0){
            return this.addToFirst(anElement);
        }else{
            LinkedNode<T> previous = this.head();
            LinkedNode<T> current = previous.next();
            for(int i=1; i < anIndex; i++){
                previous = current;
                current = current.next();
            }
            LinkedNode<T> newNode = new LinkedNode<T>(anElement, current);
            previous.setNext(newNode);
            this.setSize(this.size() + 1);
            return true;
        }
    }

    public T removeAny() {
        return this.removeFirst();
	}

    @Override
    public T removeFirst() {
        if (this.isEmpty()){
            return null;
        }else {
            T removedElement = this.head().element();
            this.setHead(this.head().next());
            this.setSize(this.size()-1);
            return removedElement;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()){
            return null;
        }else if(this.head().next() == null){
            T lastElement = this.head().element();
            this.setHead(null);
            this.setSize(0);
            return lastElement;
        }else{
            LinkedNode<T> previous = this.head();
            LinkedNode<T> current = previous.next();
            while(current.next() != null){
                previous = current ;
                current = current.next();
            }
            T lastElement = current.element();
            previous.setNext(current.next());
            this.setSize(this.size() - 1);
            return lastElement;
        }
    }

    @Override
    public T removeAtIndex(int anIndex) {
        if(anIndex<0 || anIndex >=this.size()){
            return null;
        }else if(anIndex == 0){
            return this.removeFirst();
        }else {
            LinkedNode<T> previous = this.head();
            LinkedNode<T> current = previous.next();
            for(int i=1; i< anIndex; i++){
                previous = current;
                current = current.next();
            }
            previous.setNext(current.next());
            this.setSize(this.size() - 1);
            return current.element();
        }
    }

    public int size() {
		return this._size;
	}

    @Override
    public void reset() {
        this.setSize(0);
        this.setHead(null);
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

	public class IteratorForLinkedList implements Iterator<T> {

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
