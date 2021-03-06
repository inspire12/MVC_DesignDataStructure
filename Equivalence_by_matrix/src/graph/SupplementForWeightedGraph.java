package graph;

public interface SupplementForWeightedGraph<E> {
	int weightOfEdge(E anEdge);
	int weightOfEdge(int aTailVertex, int aHeadVertex);
}
