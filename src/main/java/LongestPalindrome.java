/**
 * Created by rquinn on 5/30/17.
 */
/*
"babc";

is the whole thing a palindrome? no
n^2 answer?

*/

public class LongestPalindrome {
    static String longest;
    public static String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        longest = s.substring(0,1);
        int startIdx = 0;



        while (startIdx < s.length() - 1) {
            extendPalindrome(s, startIdx, startIdx);
            extendPalindrome(s, startIdx, startIdx + 1);
            startIdx++;
        }

        return longest;
    }

    public static void extendPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            if (end - start + 1 > longest.length()) {
                longest = s.substring(start, end + 1);
            }
            start--;
            end++;
        }

    }


    public static void main(String[] args) {
        System.out.println(longestPalindrome("abb"));
    }
}

