import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rquinn on 5/29/17.
 */
public class TreeLevelLists {
    Queue<Node<Integer>> current = new LinkedList<>();
    ArrayList<ArrayList<Node<Integer>>> out = new ArrayList<>();

    public ArrayList<ArrayList<Node<Integer>>> getLists(Node root) {
        current.add(root);
        processCurrent();
        return out;
    }

    public void processCurrent() {
        if (current.size() == 0) {
            return;
        }
        Queue<Node<Integer>> next = new LinkedList<>();
        ArrayList<Node<Integer>> currList = new ArrayList<>();
        out.add(currList);
        Node elem;
        while ((elem = current.poll()) != null) {
           currList.add(elem);
            if (elem.left != null) {
                next.add(elem.left);
            }
            if (elem.right != null) {
                next.add(elem.right);
            }
        }

        if (next.size() != 0) {
            current = next;
            processCurrent();
        }
    }

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(new Node<>(null, 5, null), 10, new Node<>(null, 15, null));
        TreeLevelLists tl = new TreeLevelLists();
        ArrayList<ArrayList<Node<Integer>>> lists = tl.getLists(root);
        for (ArrayList<Node<Integer>> list : lists) {
            System.out.println("List length: " + list.size());
            for (Node node : list) {
                System.out.print("the node: " + node);
            }
            System.out.println();
        }
    }
}
