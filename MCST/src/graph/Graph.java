package graph;

public interface Graph<E> {
	int numberOfVertices();
	int numberOfEdges();
	
	boolean vertexDoesExist(int aVertex);
	boolean edgeDoesExist(int aTailVertex, int aHeadVertex);
	boolean edgeDoesExist(E anEdge);
	
	boolean edgeIsValid(int aTailVertex, int aHeadVertex);
	boolean edgeIsValid(E anEdge);
	
	boolean addEdge(E anEdge);
}
