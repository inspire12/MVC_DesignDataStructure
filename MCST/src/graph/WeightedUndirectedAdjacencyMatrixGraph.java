package graph;

public class WeightedUndirectedAdjacencyMatrixGraph<WE extends WeightedEdge> extends UndirectedAdjacencyMatrixGraph<WE> implements SupplementForWeightedGraph<WE> {
 
	private static final int WEIGHTED_EDGE_NONE = -1;
	@Override
	protected boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
		return (this.adjacencyOfEdge(tailVertex, headVertex) != WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE );
	}
	public WeightedUndirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
		super();
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex = 0; tailVertex< this.numberOfVertices(); tailVertex++) {
			for(int headVertex =0; headVertex < this.numberOfVertices(); headVertex++) {
				this.setWeightedEdgeAsNone(tailVertex, headVertex);
			}
		}
	}
	@Override
	public boolean addEdge(WE anEdge) {
		if(anEdge != null) {
			if(this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)) {
				int tailVertex = anEdge.tailVertex();
				int headVertex = anEdge.headVertex();
				this.setWeightedEdge(tailVertex, headVertex, anEdge.weight());
				this.setWeightedEdge(headVertex, tailVertex, anEdge.weight());
				this.setNumberOfEdges(this.numberOfEdges()+1);
				return true;
			}
		}
		return false;
	}
	@Override
	protected boolean adjaceyOfEdgeDoesExist(int aTailVertex, int aHeadVertex) {
		return (this.adjacencyOfEdge(aTailVertex, aHeadVertex)!= WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}
	private void setWeightedEdge(int aTailVertex, int aHeadVertex, int newWeight) {
		this.adjacency()[aTailVertex][aHeadVertex] = newWeight;
	}
	private void setWeightedEdgeAsNone(int aTailVertex, int aHeadVertex) {
		this.setWeightedEdge(aTailVertex, aHeadVertex, WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}
	@Override
	public int weightOfEdge(WE anEdge) {
		if(anEdge!=null) {
			return this.weightOfEdge(anEdge.tailVertex(), anEdge.headVertex());
		}
		return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
	}

	@Override
	public int weightOfEdge(int aTailVertex, int aHeadVertex) {
		if(this.edgeDoesExist(aTailVertex, aHeadVertex)){
			return this.adjacencyOfEdge(aTailVertex, aHeadVertex);
		}
		return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
	}

	

}
