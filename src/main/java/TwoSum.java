import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> values = new HashMap<>();

        int idx = 0;
        while (idx < nums.length) {
            int idxValue = nums[idx];
            int otherHalf = target - idxValue;
            if (values.containsKey(otherHalf)) {
               return new int[] {values.get(otherHalf), idx};
            }

            values.put(idxValue, idx);
            idx++;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] solution = twoSum(nums, 6 );
        for (int num : solution) {
            System.out.println(num);
        }
    }
}
