import java.util.*;

/**
 * Created by rquinn on 6/18/17.
 */
public class ThreeSum {

    public static List<List<Integer>> twoSum(int[] nums, int startIndex, int target, int firstValue) {
        // nums is a sorted list
        int endIndex = nums.length - 1;
        List<List<Integer>> results = new ArrayList<>();
        while (startIndex < endIndex) {
            int startValue = nums[startIndex];
            int endValue = nums[endIndex];
            int sum = startValue + endValue;
            if (sum == target) {
                List<Integer> solution = new ArrayList<>();
                solution.add(firstValue);
                solution.add(startValue);
                solution.add(endValue);
                results.add(solution);
            }
            if (sum < target) {
                while (nums[startIndex] == startValue && startIndex < endIndex) {
                    startIndex++;
                }
            } else {
                while(nums[endIndex] == endValue && endIndex > startIndex) {
                    endIndex--;
                }
            }
        }
        return results;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> uniqueResults = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            uniqueResults.addAll(twoSum(nums, i + 1, nums[i] * -1, nums[i]));
        }
        return new ArrayList<List<Integer>>(uniqueResults);

    }

    public static void main(String[] args) {
        int[] nums = new int[] {0, 0, 0};
        List<List<Integer>> results = threeSum(nums);
        System.out.println("from list");
        for (List<Integer> result : results) {
            for (Integer n : result) {
                System.out.print(n + " ");
            }
            System.out.println("");
        }

    }



}
