package app;


import equivalenceClasses.EquivalenceClasses;
import graph.AdjacencyGraph;
import graph.DirectedAdjacencyMatrixGraph;
import graph.Edge;
import graph.UnDirectedAdjacencyMatrixGraph;
import list.Iterator;
import list.List;

public class AppController {
	private AdjacencyGraph<Edge> _graph;
    private EquivalenceClasses<Edge> _equivalenceClassess;

    private AdjacencyGraph<Edge> graph(){
        return this._graph;
    }
	private void setGraph(AdjacencyGraph<Edge> newGraph) {
		this._graph = newGraph;
	}

	public EquivalenceClasses<Edge> equivalenceClasses(){
        return this._equivalenceClassess;
    }

    public void setEquivalenceClassess(EquivalenceClasses<Edge> newEquivalenceClasses){
        this._equivalenceClassess = newEquivalenceClasses;
    }


	public AppController() {
        this.setGraph(null);
        this.setEquivalenceClassess(new EquivalenceClasses<Edge>());
	}

    private Edge inputEdge() {
		do {
			AppView.outputLine("- 입력할 edge의 두 vertex와 cost를 차례로 입력해야 합니다:");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
            if(this.graph().vertexDoesExist(tailVertex)&& this.graph().vertexDoesExist(headVertex)){
				if(tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 원소 번호가 동일합니다.");
				}else {
					return (new Edge(tailVertex, headVertex));
				}
			}
			else {
				if(!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 vertex 입니다: " + tailVertex);
				}
				if(!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 vertex 입니다: " + headVertex);
				}
			}
		}while(true);
	}

    private int inputSourceVertex(){
        int sourceVertex  = AppView.inputSourceVertex();
        while(!this.graph().vertexDoesExist(sourceVertex)){
            AppView.outputLine("[오류] 입력된 출발 vertex는 존재하지 않습니다: "+sourceVertex);
            sourceVertex = AppView.inputSourceVertex();
        }
        return sourceVertex;
    }


    private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 관계의 원소 수와 관걔 쌍 수를 먼저 입력해야 합니다.:");
		int numberOfVertices = inputNumberOfVertices();
		while(numberOfVertices <= 2){
            if(numberOfVertices == 2){
                AppView.outputLine("원소가 2이면 엣지는 1개 밖에 형성이 되지 않습니다.");
            }
            if(numberOfVertices == 1){
                AppView.outputLine("원소가 2개 이상이어야 관계 쌍를 연결할 수 있습니다.");
            }
            numberOfVertices = inputNumberOfVertices();
		}
		this.setGraph(new UnDirectedAdjacencyMatrixGraph<Edge>(numberOfVertices));

        int numberOfEdges = this.inputNumberOfEdges();
        while(numberOfEdges == 0){
            AppView.outputLine("관계쌍이 없으면 의미가 없습니다.");
            numberOfEdges = this.inputNumberOfEdges();
        }
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 관계 쌍이 주어진 수 만큼 입력합니다.:");
		int edgeCount = 0;
		while(edgeCount < numberOfEdges) {
			Edge edge = this.inputEdge();
			if(this.graph().edgeDoesExist(edge)) {
				AppView.outputLine("[오류] 입력된 관계 쌍 (" + edge.tailVertex()+", "+ edge.headVertex() +")는 관계에 이미 존재합니다.");
			}else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 관계 쌍 (" + edge.tailVertex() + ", "+ edge.headVertex() + ")가 관계에 삽입되었습니다.");
			}
		}
	}
    private void showEquivalenceClasses(){
        AppView.outputLine("");
        AppView.outputLine("> 찾아진 동등 클래스는 다음과 같습니다:");
        Iterator<?> equivalenceClassListIterator = this.equivalenceClasses().equivalenceClassList().iterator();
        for(int classOrder = 0; equivalenceClassListIterator.hasNext(); classOrder++){
            AppView.output("[동등 클래스 " + String.format("%2d", classOrder)+"] ");

            List<Integer> equivalenceClass = (List<Integer>) equivalenceClassListIterator.next();
            Iterator<Integer> equivalenceClassIterator = equivalenceClass.iterator();
            AppView.output(String.format(" = {%2d", equivalenceClassIterator.next()));
            while(equivalenceClassIterator.hasNext()){
                AppView.output(String.format(",%2d", equivalenceClassIterator.next()));
            }
            AppView.outputLine(" }");
        }
    }

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 관계는 다음과 같습니다:");
		for(int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("["+tailVertex+"] ->");
			Iterator<Edge> neighborIterator = this.graph().neighborIteratorOf(tailVertex);
            while(neighborIterator.hasNext()){
                Edge nextEdge = neighborIterator.next();
                AppView.output(" " + nextEdge.headVertex());
            }
			AppView.outputLine("");
		}
	}
	public void run() {
		AppView.outputLine("<<< 동등 클래스 찾기 프로그램을 시작합니다. >>>");
		this.inputAndMakeGraph();
		this.showGraph();

		if(this.equivalenceClasses().solve(this.graph())){
		    this.showEquivalenceClasses();
        }else{
		    AppView.outputLine("");
		    AppView.outputLine("[오류] 동등 클래스를 성공적으로 마치지 못했습니다.");
        }
        AppView.outputLine("");
		AppView.outputLine("<<< 동등 클래스 찾기 프로그램을 종료합니다. >>>");
	}
	private int inputNumberOfEdges() {
		return AppView.inputNumberOfEdges();
	}
	private int inputNumberOfVertices() {
		return AppView.inputNumberOfVertices();
	}
}
