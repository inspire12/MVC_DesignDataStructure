package list;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    public int capacity() {
        return _capacity;
    }

    public void setCapacity(int capacity) {
        _capacity = capacity;
    }

    public int size() {
        return _size;
    }

    public void setSize(int size) {
        _size = size;
    }

    public T[] elements() {
        return _elements;
    }

    public void setElements(T[] elements) {
        _elements = elements;
    }

    private int _capacity;
    private int _size;
    private T[] _elements;

    public ArrayList(){
        this(ArrayList.DEFAULT_CAPACITY);
    }

    public ArrayList(int givenCapacity) {
        this.setCapacity(givenCapacity);
        this.setElements((T[]) new Object[this.capacity()]);
        this.setSize(0);
    }

    @Override
    public void reset() {
        for(int i=0; i<this.size(); i++){
            this.elements()[i] = null;
        }
        this.setSize(0);
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean isFull() {
        return this.size() == this.capacity();
    }

    @Override
    public T firstElement() {
        if(!this.isEmpty()){
            return this.elements()[0];
        }
        return null;
    }

    @Override
    public T lastElement() {
        if(! this.isEmpty()){
            return this.elements()[this.size() -1];
        }
        return null;
    }

    @Override
    public T elementAtIndex(int anIndex) {
        if(!this.isEmpty()){
            if(anIndex >= 0 && anIndex < this.size()){
                return this.elements()[anIndex];
            }
        }
        return null;
    }

    @Override
    public boolean add(T anElement) {
        return this.addToLast(anElement);
    }

    @Override
    public boolean addToFirst(T anElement) {
        return this.addAtIndex(anElement, 0);
    }

    @Override
    public boolean addToLast(T anElement) {
        return this.addAtIndex(anElement, this.size());
    }

    @Override
    public boolean addAtIndex(T anElement, int anIndex) {
        if(!this.isFull()){
            if(anIndex >= 0 && anIndex <= this.size()){
                for(int i= this.size(); i> anIndex; i--){
                    this.elements()[i] = this.elements()[i-1];
                }
                this.elements()[anIndex] = anElement;
                this.setSize(this.size() + 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public T removeAny() {
        return removeLast();
    }

    @Override
    public T removeFirst() {
        return removeAtIndex(0);
    }

    @Override
    public T removeLast() {
        return this.removeAtIndex(this.size() - 1);
    }

    @Override
    public T removeAtIndex(int anIndex) {
        if(!this.isEmpty()){
            if(anIndex >= 0 && anIndex < this.size()){
                T removedElement = this.elements()[anIndex];
                for(int i= anIndex; i< this.size(); i++){
                    this.elements()[i] = this.elements()[i+1];
                }
                this.setSize(this.size()- 1);
                this.elements()[this.size()] = null;
                return removedElement;
            }
        }
        return null;
    }

    public  IteratorForArrayList iterator() {
        return new IteratorForArrayList();
    }

    public class IteratorForArrayList implements Iterator<T> {

        private int _nextPosition;
        public IteratorForArrayList(int nextPosition) {
            this.setNextPosition(0);
        }

        public IteratorForArrayList() {

        }

        private int nexPosition(){
            return this._nextPosition;
        }
        public void setNextPosition(int nextPosition) {
            _nextPosition = nextPosition;
        }

        @Override
        public boolean hasNext() {
            return (this.nexPosition() < ArrayList.this.size());
        }
        @Override
        public T next() {
            T nextElement = ArrayList.this.elements()[this.nexPosition()];
            this.setNextPosition(this.nexPosition()+ 1);
            return nextElement;
        }
    }
}
