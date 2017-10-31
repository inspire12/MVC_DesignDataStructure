package DS2P03_201203431_Biparite;

public class AdjacencyMatrixGraph {
	private int _numberOfVertices;
	private int _numberOfEdges;

	private int[][] _adjacency;
	private static final int EDGE_EXIST = 1;
	private static final int EDGE_NONE = 0;

	public AdjacencyMatrixGraph(int givenNumberOfVertices) {
		this._numberOfVertices = givenNumberOfVertices;
		this._numberOfEdges = 0;
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for (int tailVertex = 0; tailVertex < this.numberOfVertices(); ++tailVertex) {
			for (int headVertex = 0; headVertex < this.numberOfVertices(); ++headVertex) {
				this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_NONE;
			}
		}
	}

	
	private boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
		return (this.adjacency()[tailVertex][headVertex]!= AdjacencyMatrixGraph.EDGE_NONE);
	}
	public boolean vertexDoesExist(int aVertex) {
		return (aVertex >= 0 && aVertex < this.numberOfVertices());
	}
	// union find
	public boolean edgeDoesExist(Edge anEdge) {
		if(anEdge != null) {
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			if(this.vertexDoesExist(tailVertex)&&this.vertexDoesExist(headVertex)) {
				return this.adjacencyOfEdgeDoesExist(tailVertex, headVertex);
			}
		}
		return false;
	}
		

	public boolean addEdge(Edge anEdge) {
		if(anEdge != null) {
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			if(this.vertexDoesExist(tailVertex)&&this.vertexDoesExist(headVertex)) {
				if(!this.adjacencyOfEdgeDoesExist(tailVertex, headVertex)) {
					this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
					this.adjacency()[headVertex][tailVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
					this.setNumberOfEdges(this.getNumberOfEdges()+1);
					return true;
				}
			}
		}
		return false;
	}

	public int numberOfVertices() {
		return _numberOfVertices;
	}

	@SuppressWarnings("unused")
	private void setNumberOfVertices(int _numberOfVertices) {
		this._numberOfVertices = _numberOfVertices;
	}

	private int getNumberOfEdges() {
		return _numberOfEdges;
	}

	private void setNumberOfEdges(int _numberOfEdges) {
		this._numberOfEdges = _numberOfEdges;
	}

	private int[][] adjacency() {
		return this._adjacency;
	}


	private void setAdjacency(int[][] _adjacency) {
		this._adjacency = _adjacency;
	}

}
