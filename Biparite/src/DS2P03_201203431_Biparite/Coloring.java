package DS2P03_201203431_Biparite;

public class Coloring {
	private AdjacencyMatrixGraph _graph;
	private VertexColor[]	_vertexColors;
	
	private int _startingVertex;
	private LinkedList<Edge> _sameColorEdges;

		
	public Coloring( AdjacencyMatrixGraph givenGraph) {
		this.setGraph(givenGraph);
		this.setVertexColors(new VertexColor[this.graph().numberOfVertices()] );
		for(int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++) {
			this.setVertexColor(vertex, VertexColor.NONE);
		}
		this.setSameColorEdges(new LinkedList<Edge>());
		this.setStartingVertex(0);
	}

	public void runColoring() {
		this.paintColorOfVertices();
		this.findSameColorEdges();
	}
	//bfs
	private void paintColorOfVertices() {
		this.setVertexColor(this.startingVertex(), VertexColor.RED);
		CircularQueue<Integer> breadthFirstSearchQueue = new CircularQueue<Integer>(this.graph().numberOfVertices());
		breadthFirstSearchQueue.add(this.startingVertex());
		
		while(! breadthFirstSearchQueue.isEmpty()) {
			int tailVertex = breadthFirstSearchQueue.remove();
			VertexColor headVertexColor = 
					(this.vertexColor(tailVertex) == VertexColor.RED) ? VertexColor.BLUE : VertexColor.RED;
			for(int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				Edge visitingEdge = new Edge(tailVertex, headVertex);
				if(this.graph().edgeDoesExist(visitingEdge)) {
					if(this.vertexColor(headVertex)==VertexColor.NONE) {
						this.setVertexColor(headVertex, headVertexColor);
						breadthFirstSearchQueue.add(headVertex);
					}
				}
			}
		}
	}
	
	private void findSameColorEdges() {
		for(int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			for(int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				Edge visitingEdge = new Edge(tailVertex,headVertex);
				if(this.graph().edgeDoesExist(visitingEdge)) {
					if(this.vertexColor(tailVertex) == this.vertexColor(headVertex)) {
						this.sameColorEdges().add(visitingEdge);
					}
				}
			}
		}
	}
	
	
	
	private AdjacencyMatrixGraph graph() {
		return this._graph;
	}
	private void setGraph(AdjacencyMatrixGraph newGraph) {
		this._graph = newGraph;
	}
	private int startingVertex() {
		return this._startingVertex;
	}
	private void setStartingVertex(int newVertex) {
		this._startingVertex= newVertex;
	}
	
	private VertexColor[] vertexColors() {
		return this._vertexColors;
	}
	private void setVertexColors(VertexColor[] newVertexColors) {
		this._vertexColors = newVertexColors;
	}

	public VertexColor vertexColor(int aVertex) {
		return this.vertexColors()[aVertex] ;
	}
	private void setVertexColor(int aVertex, VertexColor newColor) {
		this.vertexColors()[aVertex] = newColor;
	}
	public LinkedList<Edge> sameColorEdges(){
		return this._sameColorEdges;
	}
	
	private void setSameColorEdges(LinkedList<Edge> newLinkedList) {
		this._sameColorEdges = newLinkedList;
	}
}
