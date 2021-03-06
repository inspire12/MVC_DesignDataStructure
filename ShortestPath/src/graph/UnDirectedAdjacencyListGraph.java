package graph;

import list.Iterator;
import list.LinkedList;

public class UnDirectedAdjacencyListGraph<E extends Edge> extends DirectedAdjacencyListGraph<E>{
    public UnDirectedAdjacencyListGraph(LinkedList[] _adjacency) {
        super(_adjacency);
    }

    @Override
    public boolean addEdge(E anEdge) {
        if( this.edgeIsValid(anEdge) && (!this.edgeDoesExist(anEdge))){
            this.neighborListOf(anEdge.tailVertex()).add(anEdge);
            this.setNumberOfEdges(this.numberOfEdges() + 1);
            return true;
        }
        return false;
    }
}
