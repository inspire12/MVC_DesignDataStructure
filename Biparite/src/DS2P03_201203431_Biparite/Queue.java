package DS2P03_201203431_Biparite;

public interface Queue<T> {
	public void reset();
	public int size();
	public boolean isEmpty();
	public boolean isFull();
	public boolean add(T anElement);
	public T remove();
}
