package graph;

import list.Iterator;

public interface Graph<E> {
	int numberOfVertices();
	int numberOfEdges();
	
	boolean vertexDoesExist(int aVertex);
	boolean edgeDoesExist(int aTailVertex, int aHeadVertex);
	boolean edgeDoesExist(E anEdge);
	
	boolean edgeIsValid(int aTailVertex, int aHeadVertex);
	boolean edgeIsValid(E anEdge);

	public E edge(int aTailVertex, int aHeadVertex);
	boolean addEdge(E anEdge);

    public int adjacencyOfEdge(int aTailVertex, int aHeadVertex);
	public Iterator<E> neighborIteratorOf(int aTailVertex);
}
