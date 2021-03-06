package topologicalSort;
import app.AppView;
import graph.AdjacencyGraph;
import graph.Edge;
import list.*;

public class TopologicalSort <E extends Edge>{
    private static final boolean DEBUG_MODE = true;

    private AdjacencyGraph<E> _graph;  // 입력
    private int[] _predecessorCounts; //
    private Stack<Integer> _zeroCountVertices; //
    private List<Integer> _sortedList; // 출력 결과

    public TopologicalSort(){
        this.setGraph(null);
        this.setPredecessorCounts(null);
        this.setZeroCountVertices(null);
        this.setSortedList(null);
    }

    private void initPredecessorCounts(){
        this.setPredecessorCounts(new int[this.graph().numberOfVertices()]);
        for(int tailVertex = 0; tailVertex <this.graph().numberOfVertices(); tailVertex++){
            this._predecessorCounts[tailVertex] = 0;
        }
        for(int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++){
            Iterator<E> iterator = this.graph().neighborIteratorOf(tailVertex);
            while(iterator.hasNext()){
                Edge edge = (Edge) iterator.next();
                this.getPredecessorCounts()[edge.headVertex()]++;
            }
        }
        TopologicalSort.showDebugMessage("\n[Debug] 각 vertex의 초기 선행자의 수는 다음과 같습니다:\n-->");
        for(int vertex =0; vertex < this.graph().numberOfVertices(); vertex++){
            TopologicalSort.showDebugMessage(" ["+vertex+"]="+this.getPredecessorCounts()[vertex]);
        }
        TopologicalSort.showDebugMessage("\n");
    }

    private  void initZerCountVertices(){
        this.setZeroCountVertices(new LinkedStack<Integer>());
        TopologicalSort.showDebugMessage("\n[Debug] 그래프에 선행자가 없는 vertex들은 다음과 같습니다:\n --> ( ");
        for(int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++){
            if(this.getPredecessorCounts()[vertex]==0){
                this.getZeroCountVertices().push(vertex);
                TopologicalSort.showDebugMessage(vertex + " ");
            }
        }
        TopologicalSort.showDebugMessage(")\n");
    }
    private void showZeroCountVertices(){
        TopologicalSort.showDebugMessage("--> 스택:<Top>");
        Iterator<Integer> iterator = ((LinkedStack<Integer>) this.getZeroCountVertices()).iterator();
        while(iterator.hasNext()){
            int vertex = (Integer) iterator.next();
            TopologicalSort.showDebugMessage(" "+vertex);
        }
        TopologicalSort.showDebugMessage("<Bottom>\n");
    }

    public boolean solve(AdjacencyGraph<E> aGraph){
        this.setGraph(aGraph);
        this.initPredecessorCounts();
        this.initZerCountVertices();
        this.setSortedList(new ArrayList<Integer>(this.graph().numberOfVertices()));

        TopologicalSort.showDebugMessage("\n[Debug] 스택에 pop/push 되는 고정은 다음과 같습니다: \n");

        while(!this.getZeroCountVertices().isEmpty()){
            int tailVertex = this.getZeroCountVertices().pop();
            TopologicalSort.showDebugMessage("--> Popped = "+tailVertex + ": Pushed = (");
            this.getSortedList().add(tailVertex);
            Iterator<E> iterator = this.graph().neighborIteratorOf(tailVertex);
            while(iterator.hasNext()){
                Edge edge = (Edge) iterator.next();
                --this.getPredecessorCounts()[edge.headVertex()];
                if(this.getPredecessorCounts()[edge.headVertex()]==0){
                    this.getZeroCountVertices().push(edge.headVertex());
                    TopologicalSort.showDebugMessage(edge.headVertex() + " ");
                }
            }
            TopologicalSort.showDebugMessage(" )\n");
            this.showZeroCountVertices();
        }
        return this.getSortedList().size() == this.graph().numberOfVertices();
    }

    private static void showDebugMessage(String aMessage){
        if(DEBUG_MODE == true)
            AppView.output(aMessage);
    }


    private AdjacencyGraph<E> graph() {
        return this._graph;
    }

    public static boolean isDebugMode() {
        return DEBUG_MODE;
    }

    public AdjacencyGraph<E> getGraph() {
        return _graph;
    }

    public void setGraph(AdjacencyGraph<E> graph) {
        _graph = graph;
    }

    public int[] getPredecessorCounts() {
        return _predecessorCounts;
    }

    public void setPredecessorCounts(int[] predecessorCounts) {
        _predecessorCounts = predecessorCounts;
    }

    public Stack<Integer> getZeroCountVertices() {
        return _zeroCountVertices;
    }

    public void setZeroCountVertices(Stack<Integer> zeroCountVertices) {
        this._zeroCountVertices = zeroCountVertices;
    }

    public List<Integer> getSortedList() {
        return _sortedList;
    }

    public void setSortedList(ArrayList<Integer> sortedList) {
        this._sortedList = sortedList;
    }

    public List<Integer> toppologicallySortedList() {
        return this.getSortedList();
    }
}
