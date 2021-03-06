package graph;

import list.LinkedList;

public class UnDirectedAdjacencyListGraph<E extends Edge> extends DirectedAdjacencyListGraph<E> {
    public UnDirectedAdjacencyListGraph(LinkedList[] _adjacency) {
        super(_adjacency);
    }
    public UnDirectedAdjacencyListGraph(int numberOfVertices) {
        super(numberOfVertices);
    }
    @Override
    public boolean addEdge(E anEdge) {
        if( this.edgeIsValid(anEdge) && (!this.edgeDoesExist(anEdge))){
            this.neighborListOf(anEdge.tailVertex()).add(anEdge);
            // 양 방향 처리
            E reversedEdge = (E) anEdge.reversed();
            this.neighborListOf(anEdge.headVertex()).add(reversedEdge);
            this.setNumberOfEdges(this.numberOfEdges() + 1);
            return true;
        }
        return false;
    }
}
