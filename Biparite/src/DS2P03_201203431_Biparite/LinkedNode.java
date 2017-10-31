package DS2P03_201203431_Biparite;

public class LinkedNode<T> {
	private T _element;
	private LinkedNode<T> _next;
	
	public LinkedNode(){
		this.setElement(null);
		this.setNext(null);
	}
	
	
	public LinkedNode(T givenElement, LinkedNode<T> givenNext) {
		this.setElement(givenElement);
		this.setNext(givenNext);
	}



	public T element() {
		return _element;
	}
	public void setElement(T _element) {
		this._element = _element;
	}
	public LinkedNode<T> next() {
		return _next;
	}
	public void setNext(LinkedNode<T> _next) {
		this._next = _next;
	}
	
}
