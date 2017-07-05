import java.util.List;

/**
 * Created by rquinn on 5/28/17.
 */
public class TestSolution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {0,4,3,0};
        int[] result = s.twoSum(nums, 0);
        for (int elem : result) {
            System.out.print(elem + " ");
        }
    }
}
