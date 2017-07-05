import java.util.stream.IntStream;

/**
 * Created by rquinn on 5/29/17.
 */
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = null;
        ListNode node = null;
        int carry = 0;

        do {
            int l1val = 0;
            int l2val = 0;
            if (l1 != null) {
                l1val = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                l2val = l2.val;
                l2 = l2.next;
            }
            int nextval = l1val + l2val + carry;
            if (nextval > 9) {
                nextval = nextval % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            if (root == null) {
                node = new ListNode(nextval);
                root = node;
            } else {
                node.next = new ListNode(nextval);
                node = node.next;
            }
        } while (l1 != null || l2 != null || carry > 0);

        return root;
    }

    public static void main(String[] args) {
        int[] l1in = {2, 9, 1, 7, 2, 5, 8, 4, 2, 1};
        int[] l2in = {9, 9, 4, 5, 9, 4, 4, 1, 4, 1};
        ListNode l1 = fromArray(l1in);
        ListNode l2 = fromArray(l2in);
        ListNode result = addTwoNumbers(l1, l2);
        do {
            System.out.print("node value: " + result.val);
        } while ((result = result.next) != null);
    }

    public static ListNode fromArray(int[] input) {
        ListNode tmp = null;
        ListNode node = null;
        for (int i = input.length - 1; i >= 0; i--) {
            node = new ListNode(input[i]);
            node.next = tmp;
            tmp = node;
        }
        return node;
    }
}

