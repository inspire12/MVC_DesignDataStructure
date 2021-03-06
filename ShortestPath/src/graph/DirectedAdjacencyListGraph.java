package graph;

import list.Iterator;
import list.LinkedList;

public class DirectedAdjacencyListGraph <E extends Edge> extends AdjacencyGraph<E>{

    private LinkedList<E>[] _adjacency;

    public DirectedAdjacencyListGraph(LinkedList<E>[] _adjacency) {
        this._adjacency = _adjacency;
    }

    @SuppressWarnings("unchecked")
    public DirectedAdjacencyListGraph(int givenNumberOfVertices) {
        this.setNumberOfVertices(givenNumberOfVertices);
        this.setAdjacency(new LinkedList[givenNumberOfVertices]);
        for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++){
            this.adjacency()[tailVertex] = new LinkedList<E>();
        }
        this.setNumberOfEdges(0);
    }

    public LinkedList<E>[] adjacency() {
        return this._adjacency;
    }

    public void setAdjacency(LinkedList<E>[] newAdjacency) {
        this._adjacency = newAdjacency;
    }


    @Override
    public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
        return (this.adjacencyOfEdge(aTailVertex,aHeadVertex)!=AdjacencyGraph.EDGE_NONE);
    }

    @Override
    public boolean edgeDoesExist(E anEdge) {
        if(anEdge != null){
            //초기에 vertex 초기화가 문제 있는 것 같음
            return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
        }
        return false;
    }

    @Override
    public E edge(int aTailVertex, int aHeadVertex) {
        if(this.vertexDoesExist(aTailVertex)){
            Iterator<E> iterator = this.neighborIteratorOf(aTailVertex);
            while(iterator.hasNext()){
                E neighborEdge = iterator.next();
                if(aHeadVertex == neighborEdge.headVertex()){
                    return neighborEdge;
                }
            }
        }
        return null;
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

    @Override
    public Iterator<E> neighborIteratorOf(int aTailVertex) {
        if(this.vertexDoesExist(aTailVertex)){
            return (Iterator<E>) this.neighborListOf(aTailVertex).iterator();
        }
        return null;
    }
    protected LinkedList<E> neighborListOf(int aTailVertex){
        return this.adjacency()[aTailVertex];
    }

    public int adjacencyOfEdge(int aTailVertex, int aHeadVertex){
        if(this.vertexDoesExist(aTailVertex)&&this.vertexDoesExist(aHeadVertex)){
            Iterator<E> iterator = this.neighborIteratorOf(aTailVertex);
            while(iterator.hasNext()){
                E neighborEdge = iterator.next();
                if(aHeadVertex == neighborEdge.headVertex()){
                    return AdjacencyGraph.EDGE_EXIST;
                }
            }
        }
        return AdjacencyGraph.EDGE_NONE;
    }
}
