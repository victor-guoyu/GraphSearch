package search;

public class SearchGraph {
    
    public void runSelectedAlgorithm() {
        switch(Config.INSTANCE.getAlgorithm()) {
            case BFS:
                breadFirstSearch();
                break;
            case DFS:
                depthFirstSearch();
                break;
            case UCS:
                uniformCostSearch();
                break;
        default:
            break;
        }
    }
    
    private void breadFirstSearch() {
        
    }

    private void depthFirstSearch() {
        
    }

    private void uniformCostSearch() {
        
    }

    public static void main(String[] args) {
        SearchGraph sg = new SearchGraph();
        sg.runSelectedAlgorithm();
    }
}