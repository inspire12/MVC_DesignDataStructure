package graph;

import graph.Edge;

public class UnDirectedAdjacencyMatrixGraph<E extends Edge> extends DirectedAdjacencyMatrixGraph<E> {

    private static final int EDGE_EXIST = 1;
    private static final int EDGE_NONE = 0;

    public boolean addEdge(E anEdge) {
        if (anEdge != null) {
            if (this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)) {
                int tailVertex = anEdge.tailVertex();
                int headVertex = anEdge.headVertex();
                this.setAdjacencyOfEdgeAsExist(tailVertex, headVertex);
                this.setAdjacencyOfEdgeAsExist(headVertex, tailVertex);
                this.setNumberOfEdges(this.numberOfEdges()+1);
                return true;
            }
        }
        return false;
    }

    private void setAdjacencyOfEdgeAsExist(int tailVertex, int headVertex) {
        this.setAdjacencyOfEdgeAs(tailVertex, headVertex, UnDirectedAdjacencyMatrixGraph.EDGE_EXIST);
    }
}
