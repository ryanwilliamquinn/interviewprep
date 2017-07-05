import java.util.Arrays;

/**
 * Created by rquinn on 5/29/17.
 */
public class CreateBalancedTree {

    /**
     *
     * @param nums - a sorted array of integers, in ascending order
     */
    public Node<Integer> create(int[] nums, Node<Integer> node) {
        System.out.println("nums: " + nums.length);
        if (nums.length == 0) {
            return node;
        }
        int medianIndex = nums.length / 2;
        int medianValue = nums[medianIndex];
        Node<Integer> newNode = new Node<>(null, medianValue, null);
        if (node != null) {
            if (medianValue < node.value) {
                node.left = newNode;
            } else {
                node.right = newNode;
            }
        }
        if (node == null) {
            node = newNode;
        }
        if (medianIndex > 0) {
            int[] smallerValues = Arrays.copyOfRange(nums, 0, medianIndex);
            create(smallerValues, newNode);
        }
        if (medianIndex < nums.length - 1) {
            int[] largerValues = Arrays.copyOfRange(nums, medianIndex + 1, nums.length);
            create(largerValues, newNode);
        }
        return node;
    }

    public static void main(String[] args) {
        CreateBalancedTree bt = new CreateBalancedTree();
        Node<Integer> finalNode = bt.create(new int[] {1,2,3,4,5}, null);
        System.out.println("finalNode: " + finalNode);

    }
}
