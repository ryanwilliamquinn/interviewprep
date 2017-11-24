import java.util.*;
public class StringQuestions {

    static boolean isPalindrome(String word) {
        if (word == null || word.length() < 2) {
            return true;
        }

        char[] chars = word.toCharArray();
        int startIndex = 0;
        int endIndex = chars.length - 1;

        while (endIndex > startIndex) {
            if (chars[startIndex] != chars[endIndex]) {
                return false;
            }
            startIndex++;
            endIndex--;
        }
        return true;
    }

    static String longestPalindrome(String word, int leftIndex, int rightIndex) {
        int furthestLeft = -1;
        int furthestRight = -1;
        while (leftIndex >= 0 && rightIndex < word.length()) {
           if (word.charAt(leftIndex) == word.charAt(rightIndex)) {
               furthestLeft = leftIndex;
               furthestRight = rightIndex;
           } else {
               break;
           }
           leftIndex--;
           rightIndex++;
        }
        if (furthestLeft < 0) {
            return "";
        }
        return word.substring(furthestLeft, furthestRight + 1);
    }

    static String getLongestPalindrome(String word) {
        if (word == null || word.length() < 2) {
            return word;
        }

        String longestWord = "";
        for (int i = 0; i < word.length(); i++) {
           String longestOdd = longestPalindrome(word, i, i);
           if (longestOdd.length() > longestWord.length()) {
              longestWord = longestOdd;
           }
           String longestEven = longestPalindrome(word, i, i+1);
           if (longestEven.length() > longestWord.length()) {
               longestWord = longestEven;
           }
        }
        return longestWord;
    }

    static boolean isSubstring(String needle, String haystack) {
        if (needle == null || haystack == null) {
            return false;
        }
        char[] hay = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        int h = 0;
        int n = 0;

        while (h < hay.length - needle.length() - 1) {
           while (n < needles.length) {
               if (hay[h] != needles[n]) {
                   if (n == 0) {
                       h++;
                   }
                   n = 0;
               } else {
                   n++;
                   h++;
               }
           }
           if (n == needles.length) {
               return true;
           }
        }

        return false;
        //return haystack.contains(needle);
    }

    static boolean isAnagram(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() != word2.length()) {
            return false;
        }

        Map<Character, Integer> counts = new HashMap<>();
        char[] word1chars = word1.toCharArray();
        char[] word2chars = word2.toCharArray();
        for (char c : word1chars) {
            if (counts.containsKey(c)) {
                counts.put(c, counts.get(c) + 1);
            } else {
                counts.put(c, 1);
            }
        }

        for (char c : word2chars) {
            if (counts.containsKey(c)) {
                int count = counts.get(c);
                if (count < 1) {
                    return false;
                }
                counts.put(c, count - 1);
            } else {
                return false;
            }
        }

        return true;
    }

    static boolean isRotation(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        return (word1 + word1).contains(word2);
    }

    private static List<String> recPerm(String current, String word, int r) {
        List<String> out = new ArrayList<>();
        if (r == 0) {
            out.add(current);
            return out;
        } else {
            char[] chars = word.toCharArray();
            Set<Character> usedCharacters = new HashSet<>();
            for (int i = 0; i < chars.length; i++) {
                char currentChar = chars[i];
                if (!usedCharacters.contains(currentChar)) {
                    usedCharacters.add(currentChar);
                    out.addAll(recPerm (current + chars[i], new StringBuilder(word).deleteCharAt(i).toString(), r - 1));
                }
            }
        }
        return out;
    }

    static List<String> getPermutations(String word, int r) {
        return recPerm("", word, r);
    }

    static boolean isNested(String line) {
        Map<Character, Character> braces = new HashMap<>();
        braces.put('(', ')');
        braces.put('[', ']');
        braces.put('{', '}');
        List<Character> openers = Arrays.asList('(', '{', '[');
        Stack<Character> braceStack = new Stack<>();

        for (Character c : line.toCharArray()) {
            if (openers.contains(c)) {
                braceStack.push(c);
            } else {
                // it is a closer, so the previous element must be an opener
                if (braceStack.size() < 1) {
                    return false;
                }
                Character previous = braceStack.pop();
                if (braces.get(previous) != c) {
                   return false;
                }
            }
        }
        return braceStack.size() == 0;
    }

    /**
     * reverse some or all of an array
     * @param line
     * @param left
     * @param right
     */
    static void reverse(char[] line, int left, int right) {
        char temp;
        while (left < right) {
            temp = line[left];
            line[left] = line[right];
            line[right] = temp;
            left++;
            right--;
        }
    }
    /**
     *
     * reverse the order of words in a sentence, but keeps words unchanged. words are divided by blanks.
     * do this in place
     * @param line
     */
    static void getReverseWords(char[] line) {
        reverse(line, 0, line.length -1);
        // TODO what if the array starts with a space or ends with a space?
        int left = 0;
        for (int right = 1; right < line.length; right++) {
            if (Character.isWhitespace(line[right])) {
                reverse(line, left, right - 1);
                left = right + 1;
                right = left + 1;
            }
        }
        reverse(line, left, line.length - 1);
    }

    private static void resortLines(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return;
        }
        for (int i = lines.size() - 1; i > 0; i--) {
            String currentLine = lines.get(i);
            String previousLine = lines.get(i - 1);
            if (currentLine.length() >= previousLine.length()) {
                int lastSpaceIdx = currentLine.lastIndexOf(" ");
                lines.set(i, currentLine.substring(0, lastSpaceIdx));
                lines.set(i - 1, currentLine.substring(lastSpaceIdx + 1) + " " + previousLine);
            }
        }
    }
    /**
     * Given a long String, split the string in such a way that the next line is longer than the previous line.
     * What if you have to print in opposite order, next line is shorter than previous line?
     * @param text
     * @return
     */
    static List<String> getLongerNextlines(String text) {


        String[] words = text.split("\\s+");
        List<String> out = new ArrayList<String>();

        for (int idx = words.length - 1; idx >= 0; idx--) {
            String word = words[idx];
            int numLines = out.size();
            if (numLines > 0) {
                String lastLine = out.get(numLines - 1);
                if (lastLine.length() > word.length()) {
                    out.add(word);
                } else {
                    out.set(numLines -1, word + " " + lastLine);
                    resortLines(out);
                }
            } else {
                out.add(word);
            }

        }
        Collections.reverse(out);
        return  out;
    }


    private static void capitalizeWord(char[] text, int boundaryIndex, int wordLength) {
        int firstCharIndex = boundaryIndex - wordLength;
        text[firstCharIndex] = Character.toUpperCase(text[firstCharIndex]);
    }

    /**
     * Given an input string, convert it to uppercase, lowercase, title case.
     * Note: For title case, capitalize all words in titles of publications and documents, except a, an, the, at, by,
     * for, in, of, on, to, up, and, as, but, it, or, and nor
     * @param text
     */
    static String getTitleCasing(char[] text) {
        String[] nonCapWords = { "a", "an", "the", "at", "by", "for", "in", "of", "on", "to", "up", "and", "as", "but", "it", "or", "nor" };
        Trie nonCapTrie = new Trie();
        for (String word : nonCapWords) {
            nonCapTrie.addWord(word);
        }

        // Always capitalize the first word of a title
        boolean isFirstWord = true;

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < text.length; i++) {
            Character c = text[i];
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            } else {
                String word = sb.toString();
                if (isFirstWord || !nonCapTrie.contains(word)) {
                    capitalizeWord(text, i, word.length());
                    isFirstWord = false;
                }
                sb = new StringBuilder("");
            }
        }
        String word = sb.toString();
        if (!nonCapTrie.contains(word)) {
            capitalizeWord(text, text.length, word.length());
        }

        return new String(text);
    }

    /**
     * Given an integer, convert it to String. Can you convert String to integer.
     * @param value
     * @return
     */
    static int atoi(String value) {
        char[] chars = value.toCharArray();
        int sum = 0;
        int multiple = 1;
        for (int i = chars.length - 1; i >=0; i--) {
            int charIntValue = (chars[i] - 48);
            sum += charIntValue * multiple;
            multiple = multiple * 10;
        }
        return sum;
    }

    static String itoa(int number) {
        String out = "";
        while (number != 0) {
            int remainder = number % 10;
            out =  Character.toChars(remainder + 48)[0] + out;
            number = number / 10;
        }
        return out;
    }


    static int countVowels(String line) {
        Set<Character> vowels = new HashSet<>();
        vowels.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (vowels.contains(line.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    static int countConsonants(String line) {
        return line.length() - countVowels(line);
    }

    static void urlDecode(char[] encodedString) {
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < encodedString.length; readIndex++) {
            char c = encodedString[readIndex];
            if (c == '%') {
                char[] codepoint = {encodedString[readIndex + 1], encodedString[readIndex + 2]};
                char[] decoded = Character.toChars(Integer.parseInt(new String(codepoint), 16));
                for (char dc : decoded) {
                    encodedString[writeIndex] = dc;
                    writeIndex++;
                }
                readIndex = readIndex + 2;
            } else {
                encodedString[writeIndex] = c;
                writeIndex++;
            }
        }
        while (writeIndex < encodedString.length) {
            encodedString[writeIndex] = ' ';
            writeIndex++;
        }
    }

    public static void main(String[] args) {
        int count = 0;
        for (String s : getPermutations("aba", 3)) {
            count++;
            System.out.println(s);
        }
        System.out.println("count: " + count);
        /*
        //List<String> lines = getLongerNextlines("to be strong wrestler");
        List<String> lines = getLongerNextlines("this is ok tes sentence");
        for (String line : lines) {
            System.out.println(line);
        }
        char[] encodedLine = "hello%20world%21".toCharArray();
        urlDecode(encodedLine);
        System.out.println(encodedLine);

        System.out.println(getLongestPalindrome("abamadama"));
        System.out.println(isPalindrome("abc"));
        System.out.println(isPalindrome("aba"));
        System.out.println(isPalindrome("abab"));
        System.out.println(isPalindrome("ababa"));
        System.out.println(isPalindrome("abcba"));
        System.out.println(isPalindrome("abcbc"));

        System.out.println(getLongestPalindrome("wababac"));

        System.out.println(isSubstring("abc", "ryabcan"));

        System.out.println(isAnagram("abdcd", "bcab"));

        System.out.println(isRotation("abc", "bca"));
        System.out.println(isRotation("this", "histh"));

        int count = 0;
        for (String s : getPermutations("abcda", 3)) {
            count++;
            System.out.println(s);
        }
        System.out.println("count: " + count);

        count = 0;
        for (String s : getPermutations("aaa", 3)) {
            count++;
            System.out.println(s);
        }
        System.out.println("count: " + count);

        System.out.println(isNested("{[]()}"));
        System.out.println(isNested("{[(])}"));
        System.out.println(isNested("{["));

        char[] reverseLine = "winnie feels bad today".toCharArray();
        getReverseWords(reverseLine);
        System.out.println(reverseLine);

        System.out.println(getTitleCasing("the title of my book but".toCharArray()));

        System.out.println(atoi("527"));

        System.out.println(itoa(724));


        System.out.println(countConsonants("abcdef"));
        System.out.println(countVowels("abcdef"));

        System.out.println(urlDecode("ryan%20quinn%21".toCharArray()));
        */

    }

}
