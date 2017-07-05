public class Node<T> {
    Node left;
    T value;
    Node right;

    public Node(Node left, T value, Node right) {
        this.left = left;
        this.value =value;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node value: " + value;
        //return "Node: Left - " + this.left + ", Value - " + this.value + ", Right - " + this.right;
    }
}
