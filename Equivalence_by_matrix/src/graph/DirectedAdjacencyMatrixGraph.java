package graph;

import list.ArrayList;
import list.Iterator;

public class DirectedAdjacencyMatrixGraph<E extends Edge> extends AdjacencyGraph<E> implements Graph<E>{
    private static final int EDGE_EXIST = 1;
    private static final int EDGE_NONE = 0;

    private int _numberOfVertices;
    private int _numberOfEdges;
    private int[][] _adjacency;
    protected DirectedAdjacencyMatrixGraph() {
    }

    public int[][] adjacency() {
        return _adjacency;
    }
    public DirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
        this.setNumberOfVertices(givenNumberOfVertices);
        this.setNumberOfEdges(0);
        this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
        for (int tailVertex = 0; tailVertex < this.numberOfVertices(); ++tailVertex) {
            for (int headVertex = 0; headVertex < this.numberOfVertices(); ++headVertex) {
                this.setAdjacencyOfEdgeAsNone(tailVertex, headVertex);
            }
        }
    }
    protected void setAdjacencyOfEdgeAs(int tailVertex, int headVertex, int anAdjacencyOfEdge) {
        this.adjacency()[tailVertex][headVertex] = anAdjacencyOfEdge;
    }
    private void setAdjacencyOfEdgeAsExist(int tailVertex, int headVertex) {
        this.setAdjacencyOfEdgeAs(tailVertex, headVertex, DirectedAdjacencyMatrixGraph.EDGE_EXIST);
    }
    protected void setAdjacencyOfEdgeAsNone(int tailVertex, int headVertex) {
        this.setAdjacencyOfEdgeAs(tailVertex, headVertex, DirectedAdjacencyMatrixGraph.EDGE_NONE);
    }

    public int numberOfVertices() {
        return this._numberOfVertices;
    }

    protected void setNumberOfVertices(int _numberOfVertices) {
        this._numberOfVertices = _numberOfVertices;
    }

    protected boolean adjaceyOfEdgeDoesExist(int tailVertex, int headVertex) {
        return (this.adjacencyOfEdge(tailVertex, headVertex) != DirectedAdjacencyMatrixGraph.EDGE_NONE);
    }

    public int adjacencyOfEdge(int tailVertex, int headVertex) {
        return this.adjacency()[tailVertex][headVertex];
    }

    public int numberOfEdges() {
        return this._numberOfEdges;
    }

    public boolean vertexDoesExist(int aVertex) {
        return (aVertex >= 0 && aVertex < this.numberOfVertices());
    }

    public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
        if (this.edgeIsValid(aTailVertex, aHeadVertex)) {
            return (this.adjacencyOfEdgeDoesExist(aTailVertex, aHeadVertex));
        }
        return false;
    }

    protected boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
        return (this.adjacencyOfEdge(tailVertex, headVertex)!= DirectedAdjacencyMatrixGraph.EDGE_NONE);
    }

    public boolean edgeDoesExist(E anEdge) {
        if(anEdge != null) {
            int tailVertex = anEdge.tailVertex();
            int headVertex = anEdge.headVertex();
            if(this.vertexDoesExist(tailVertex)&&this.vertexDoesExist(headVertex)) {
                return this.adjacencyOfEdgeDoesExist(tailVertex, headVertex);
            }
        }
        return false;
    }

    public boolean edgeIsValid(int aTailVertex, int aHeadVertex) {
        return (this.vertexDoesExist(aTailVertex) && this.vertexDoesExist(aHeadVertex));
    }

    @Override
    public boolean edgeIsValid(E anEdge) {
        if (anEdge != null) {
            return (this.edgeIsValid(anEdge.tailVertex(), anEdge.headVertex()));
        }
        return false;
    }

    @Override
    public boolean addEdge(E anEdge) {
        if (anEdge != null) {
            if (this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)) {
                int tailVertex = anEdge.tailVertex();
                int headVertex = anEdge.headVertex();
                this.setAdjacencyOfEdgeAsExist(tailVertex, headVertex);
               // this.setAdjacencyOfEdgeAsExist(headVertex, tailVertex);
                this.setNumberOfEdges(this.numberOfEdges()+1);
                return true;
            }
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    public E edge(int aTailVertex, int aHeadVertex) {
        if(this.edgeDoesExist(aTailVertex, aHeadVertex)){
            return (E) new Edge(aTailVertex, aHeadVertex);
        }
        return null;
    }

    public void setAdjacency(int[][] _adjacency) {
        this._adjacency = _adjacency;
    }

    public void setNumberOfEdges(int _numberOfEdges) {
        this._numberOfEdges = _numberOfEdges;
    }

    @Override
    public Iterator<E> neighborIteratorOf(int aTailVertex) {
       return new IteratorForNeighborsOf(aTailVertex);
    }
    public class IteratorForNeighborsOf implements Iterator<E>{
        private int _tailVertex;
        private int _nextHeadVertex;

        public int tailVertex(){
            return _tailVertex;
        }
        public void setTailVertex(int newTailVertex){
            this._tailVertex = newTailVertex;
        }
        private int nextHeadVertex(){
            return this._nextHeadVertex;
        }
        private void setNextHeadVertex(int newNextHeadVertex){
            this._nextHeadVertex = newNextHeadVertex;
        }
        private IteratorForNeighborsOf(int givenTailVertex){
            this.setTailVertex(givenTailVertex);
            this.setNextHeadVertex(0);
        }

        @Override
        public boolean hasNext() {
            while(this.nextHeadVertex() < DirectedAdjacencyMatrixGraph.this.numberOfVertices()){
                if(DirectedAdjacencyMatrixGraph.this.adjacencyOfEdgeDoesExist(this.tailVertex(), this.nextHeadVertex())) {
                    return true;
                }
                this.setNextHeadVertex(this.nextHeadVertex()+1);
            }
            return false;
        }

        @Override
        public E next() {
            E nextElement = (E) new Edge(this.tailVertex(), this.nextHeadVertex());
            this.setNextHeadVertex(this.nextHeadVertex()+1);
            return nextElement;
        }
    }
}
