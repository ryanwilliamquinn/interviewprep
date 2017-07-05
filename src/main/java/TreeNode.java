public class TreeNode implements Comparable<TreeNode> {
    TreeNode left;
    TreeNode right;
    Integer weight;
    boolean isLeaf;
    char value;

    public TreeNode(char value, Integer weight) {
        this.value = value;
        this.weight = weight;
        this.isLeaf = false;
    }

    public TreeNode(char value, Integer weight, boolean isLeaf) {
        this.value = value;
        this.weight = weight;
        this.isLeaf = isLeaf;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("");
        if (this.left != null) {
            out.append("left: " + this.left.toString());
        }
        out.append(", this value: " + this.value + " this weight: " + this.weight + ", ");
        if (this.right != null) {
            out.append("right: " + this.right.toString());
        }

        return out.toString();
    }

    @Override
    public int compareTo(TreeNode another) {
        if (this.weight < another.weight) {
            return -1;
        } else if (this.weight > another.weight) {
            return 1;
        } else {
            if (this.value < another.value) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
