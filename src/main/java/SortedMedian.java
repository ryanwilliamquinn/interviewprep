/**
 * Created by rquinn on 5/29/17.
 */
public class SortedMedian {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length == 1) {
            if (nums1.length == 1) {
                return nums1[0];
            } else {
                return nums2[0];
            }
        }
        int medianIdx = length / 2;
        boolean isEven = length % 2 == 0;
        int count = 0;
        int i = 0;
        int j = 0;
        int val = 0;
        int median = 0;

        while (count <= medianIdx) {
            if (i >= nums1.length) {
                val = nums2[j];
                j++;
            } else if (j >= nums2.length) {
                val = nums1[i];
                i++;
            } else if (nums1[i] <= nums2[j]) {
                val = nums1[i];
                i++;
            } else {
                val = nums2[j];
                j++;
            }
            if (!isEven && medianIdx == count) {
                return val;
            } else if (isEven && count == medianIdx - 1) {
                median += val;
            } else if (isEven && count == medianIdx) {
                return (median + val) / 2.0;
            }
            count++;
        }
        return median / 1.0;
    }

    public static void main(String[] args) {
        System.out.println("median: " + findMedianSortedArrays(new int[]{1, 3}, new int[]{}));
    }
}
