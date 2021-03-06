package equivalenceClasses;

import app.AppView;
import graph.AdjacencyGraph;
import graph.Edge;
import list.ArrayStack;
import list.Iterator;
import list.LinkedList;

public class EquivalenceClasses <E extends Edge>{
    private AdjacencyGraph<E> _graph;
    private boolean[] _found;
    private ArrayStack<Integer> _sameClassMembers;
    private LinkedList<LinkedList<Integer>> _equivalenceClassList;
    private static final boolean DEBUG_MODE = true;

    public EquivalenceClasses(){
        this.setGraph(null);
        this.setFound(null);
        this.setSameClassMembers(null);
        this.setEquivalenceClassList(null);
    }
    private void setSameClassMembers(ArrayStack<Integer> newSameClassMembers){
        this._sameClassMembers = newSameClassMembers;
    }

    public AdjacencyGraph<E> graph() {
        return _graph;
    }

    public void setGraph(AdjacencyGraph<E> graph) {
        _graph = graph;
    }

    public boolean[] found() {
        return _found;
    }

    public void setFound(boolean[] found) {
        _found = found;
    }

    public ArrayStack<Integer> sameClassMembers() {
        return _sameClassMembers;
    }

    public LinkedList<LinkedList<Integer>> equivalenceClassList() {
        return _equivalenceClassList;
    }

    public void setEquivalenceClassList(LinkedList<LinkedList<Integer>> equivalenceClassList) {
        _equivalenceClassList = equivalenceClassList;
    }

    private  static void showDebugMessage(String aMessage){
        if(EquivalenceClasses.DEBUG_MODE){
            AppView.outputDebugMessage(aMessage);
        }
    }

    public boolean solve(AdjacencyGraph<E> aGraph){
        this.setGraph(aGraph);
        if(this.graph().numberOfVertices() < 1){
            return false;
        }
        this.setFound(new boolean[this.graph().numberOfVertices()]);
        this.setEquivalenceClassList(new LinkedList<LinkedList<Integer>>());
        this.setSameClassMembers(new ArrayStack<Integer>());
        showDebugMessage("\n");
        for(int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++){
            if(!this.found()[vertex]){
                showDebugMessage("[Debug] 새로운 동등 클래스 : {");
                LinkedList<Integer> newEquivalenceClass = new LinkedList<Integer>();
                this.equivalenceClassList().add(newEquivalenceClass);

                this.found()[vertex] = true;
                newEquivalenceClass.add(vertex);
                showDebugMessage(" "+vertex);
                this.sameClassMembers().push(vertex);
                while(! this.sameClassMembers().isEmpty()){
            //        showDebugMessage(""+this.sameClassMembers().top());
                    int tailVertex = this.sameClassMembers().pop();
                    Iterator<E> iterator = this.graph().neighborIteratorOf(tailVertex);
                    while (iterator.hasNext()){
                        E edge = iterator.next();
                        int headVertex = edge.headVertex();
                        if(!this.found()[headVertex]){
                            this.found()[headVertex] = true;
                            newEquivalenceClass.add(headVertex);
                            showDebugMessage(", "+ headVertex );
                            this.sameClassMembers().push(headVertex);
                        }
                    }
                }
                showDebugMessage(" }\n");
            }
        }
        return true;
    }

}
