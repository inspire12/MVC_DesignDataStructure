package list;

public class ArrayStack<E> implements Stack {

    private static final int DEFAULT_CAPACITY = 100;
    private int _capacity;
    private int _top;
    private E[] _elements;

    public ArrayStack(){
        this(ArrayStack.DEFAULT_CAPACITY);
    }
    public ArrayStack(int givenCapacity){
        this.setCapacity(givenCapacity);
        this.setElements((E []) new Object[this.capacity()]);
        this.setTop(-1);
    }

    @Override
    public void reset() {
        this.setTop(-1);
    }

    @Override
    public int size() {
        return this.top()+1;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0 ;
    }

    @Override
    public boolean isFull() {
        return this.capacity()<=this.size();
    }

    @Override
    public boolean push(Object anElement) {
        if(isFull()){
            return false;
        }
        this.setTop(this.top()+1);
        this.elements()[this.top()] = (E)anElement; // size는 증가
        return false;
    }

    @Override
    public E pop() {
        if(isEmpty()){
            return null;
        }
        E a = this.elements()[this.size()-1];
        this.setTop(this.top() - 1);
        return a;
    }

    @Override
    public Object peek() {
        return this.elements()[this.size()];
    }

    public int capacity() {
        return _capacity;
    }

    public void setCapacity(int capacity) {
        _capacity = capacity;
    }

    public int top() {
        return _top;
    }

    public void setTop(int top) {
        _top = top;
    }

    public E[] elements() {
        return _elements;
    }

    public void setElements(E[] elements) {
        _elements = elements;
    }
}
