import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
    public int[] twoSumOld(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }
    
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }       
        
        String longestMatch = strs[0];
        
        int stringsIdx = 1;
        
        while (stringsIdx < strs.length) {
            String mystring = strs[stringsIdx];
            int minStringLength = Math.min(longestMatch.length(), mystring.length());
            int charIdx = 0;
            StringBuilder tempMatch = new StringBuilder();
            while (charIdx < minStringLength) {
                if (longestMatch.charAt(charIdx) == mystring.charAt(charIdx)) {
                    tempMatch.append(longestMatch.charAt(charIdx));
                } else {
                    break;
                }
                charIdx++;
            }
            longestMatch = tempMatch.toString();
            stringsIdx++;
        }
        return longestMatch;
    }

    public static int[] shuffle(int[] input) {
        int[] output = new int[input.length];
        int idx = 0;
        HashSet<Integer> indexSet = new HashSet<Integer>();
        while (idx < input.length) {
            int randNum = ThreadLocalRandom.current().nextInt(0, input.length);
            if (indexSet.contains(randNum)) {
                continue; 
            }
            output[idx] = input[randNum];
            indexSet.add(randNum);
            idx++;
        }
        return output;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        Set<Integer> values = new HashSet<>();
        // add all of the int values to a set. 
        for (int num : nums) {
            values.add(num); 
        }
        return out;
    }

    public static int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int numsLength = nums.length;
        int startIndex = 0;
        int endIndex = numsLength - 1;
        
        while (startIndex < endIndex) {
            int sum = nums[startIndex] + nums[endIndex];
            if (sum == target) {
                return new int[] {startIndex, endIndex};
            } else if (sum < target) {
                startIndex++;
            } else {
                endIndex--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] solution = twoSum(nums, 6);
        for (int num : solution) {
            System.out.println(num);
        }
    }
    
}
