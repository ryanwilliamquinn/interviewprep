import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rquinn on 7/15/17.
 */
public class FBNode implements Comparable<FBNode> {
    Set<FBNode> inEdges = new HashSet<>();;
    Set<FBNode> outEdges = new HashSet<>();
    String id;

    public FBNode(String id) {
        this.id = id;
    }

    public FBNode(String id, Set<FBNode> inEdges, Set<FBNode> outEdges) {
       this.id = id;
       this.inEdges = inEdges;
       this.outEdges = outEdges;
    }

    public void removeOutEdge(FBNode edge) {
        outEdges.remove(edge);
    }

    public void addOutEdge(FBNode edge) {
        outEdges.add(edge);
    }

    public void addInEdge(FBNode edge) {
        inEdges.add(edge);
    }

    public String getId() {
        return this.id;
    }

    @Override
    public int compareTo(FBNode another) {
        if (this.outEdges.size() < another.outEdges.size()) {
            return -1;
        } else if (this.outEdges.size() > another.outEdges.size()) {
            return 1;
        } else {
            return 0;
        }
    }
}
