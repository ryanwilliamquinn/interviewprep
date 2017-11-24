/**
 * Created by rquinn on 7/6/17.
 */
public class BinarySearchTreeNode {
    private BinarySearchTreeNode left;
    private BinarySearchTreeNode right;
    private Integer value;

    public BinarySearchTreeNode(Integer value) {
        this.value = value;
    }

    public BinarySearchTreeNode(BinarySearchTreeNode left, BinarySearchTreeNode right, Integer value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void insert(Integer newValue) {
        if (this.value < newValue) {
            if (this.right != null) {
                this.right.insert(newValue);
            } else {
                this.right = new BinarySearchTreeNode(newValue);
            }
        } else if (this.value > newValue) {
            if (this.left != null) {
                this.left.insert(newValue);
            } else {
                this.left = new BinarySearchTreeNode(newValue);
            }
        }
    }

    public Boolean contains(Integer searchValue) {
        if (this.value == searchValue) {
            return true;
        } else if (this.value < searchValue) {
            if (this.right == null) {
                return false;
            } else {
                return this.right.contains(searchValue);
            }
        } else {
            if (this.left == null) {
                return false;
            } else {
                return this.left.contains(searchValue);
            }
        }
    }

    // L, Root, R
    public String inOrder() {
        StringBuilder out = new StringBuilder("");
        if (this.left != null) {
            out.append(this.left.toString());
        }
        out.append(this.value).append(" ");
        if (this.right != null) {
            out.append(this.right.toString());
        }
        return out.toString();
    }

    // L, R, Root
    public String postOrder() {
        StringBuilder out = new StringBuilder("");
        if (this.left != null) {
            out.append(this.left.postOrder());
        }
        if (this.right != null) {
            out.append(this.right.postOrder());
        }
        out.append(this.value).append(" ");
        return out.toString();
    }

    // L, R, Root
    public String preOrder() {
        StringBuilder out = new StringBuilder("");
        out.append(this.value).append(" ");
        if (this.left != null) {
            out.append(this.left.preOrder());
        }
        if (this.right != null) {
            out.append(this.right.preOrder());
        }
        return out.toString();
    }

    @Override
    public String toString() {
        return inOrder();
    }

    public static void main(String[] args) {
        BinarySearchTreeNode node = new BinarySearchTreeNode(3);
        System.out.println("node: " + node);
        node.insert(1);
        node.insert(2);
        node.insert(5);
        node.insert(4);
        System.out.println("inorder: " + node.inOrder());
        System.out.println("preorder node: " + node.preOrder());
        System.out.println("postorder node: " + node.postOrder());
    }


}
