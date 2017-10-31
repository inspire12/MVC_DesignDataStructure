package DS2P03_201203431_Biparite;

public class CircularQueue<T> implements Queue<T>{
	private T[] _elements;
	private int _capacity;
	private int _front;
	private int _rear;
	
	@SuppressWarnings("unchecked")
	public CircularQueue(int maxNumberOfElements) {
		this.setCapacity(maxNumberOfElements+1);
		this.setElements((T[]) new Object[this.capacity()]);
		this.reset();
	}
	
	@Override
	public void reset() {
		this.setFront(0);
		this.setRear(0);
	}

	private int nextRear() {
		return (this.rear() + 1)% this.capacity();
	}
	private int nextFront() {
		return (this.front()+1)% this.capacity();
	}
	
	
	public T[] elements() {
		return _elements;
	}
	public void setElements(T[] _elements) {
		this._elements = _elements;
	}
	public int capacity() {
		return _capacity;
	}
	public void setCapacity(int _capacity) {
		this._capacity = _capacity;
	}
	public int front() {
		return _front;
	}
	public void setFront(int _front) {
		this._front = _front;
	}
	public int rear() {
		return _rear;
	}
	public void setRear(int _rear) {
		this._rear = _rear;
	}

	@Override
	public int size() {
		if(this.front() <= this.rear()) {
			return (this.rear() - this.front());
		}else {
			return (this.capacity() + this.rear() - this.front());
		}
	}

	@Override
	public boolean isEmpty() {
		return (this.front() == this.rear());
	}

	@Override
	public boolean isFull() {
		return (this.front() == this.nextRear());
	}

	@Override
	public boolean add(T anElement) {
		if(this.isFull()) {
			return false;
		}else {
			this.setRear(this.nextRear());
			this.elements()[this.rear()] = anElement;
			return true;
		}
	}

	@Override
	public T remove() {
		if(this.isEmpty()) {
			return null;
		}else {
			this.setFront(this.nextFront());
			return this.elements()[this.front()];
		}
	}
}
