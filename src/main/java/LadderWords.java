import java.util.*;

public class LadderWords {
    private class PathNode {
        int distance = 1;
        String word = "";

        public PathNode(String currentWord) {
            word = currentWord;
        }

        public PathNode(String currentWord, int dist) {
            word = currentWord;
            distance = dist;
        }

        public String getWord() {
            return word;
        }

        public int getDistance() {
            return distance;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordList);

        List<Set<Character>> charsAtPosition = new ArrayList<>();

        for (int charIdx = 0; charIdx < beginWord.length(); charIdx++) {
            Set<Character> charSet = new HashSet<>();
            charsAtPosition.add(charSet);
            for (int wordListIdx = 0; wordListIdx < wordList.size(); wordListIdx++) {
                charSet.add(wordList.get(wordListIdx).charAt(charIdx));
            }
        }

        Set<String> visitedWords = new HashSet<>();
        Queue<PathNode> morphedWordsQueue = new LinkedList<>();

        morphedWordsQueue.add(new PathNode(beginWord));

        while (morphedWordsQueue.size() > 0) {
            PathNode pn = morphedWordsQueue.poll();
            char[] word = pn.getWord().toCharArray();
            for (int i = 0; i < word.length; i++) {
                Set<Character> letters = charsAtPosition.get(i);
                for (char c : letters) {
                    word[i] = c;
                    String newWord = new String(word);
                    if (wordSet.contains(newWord) && !visitedWords.contains(newWord)) {
                        visitedWords.add(newWord);
                        int newDistance = pn.getDistance() + 1;
                        morphedWordsQueue.add(new PathNode(newWord, newDistance));

                        if (newWord.equals(endWord)) {
                            return newDistance;
                        }
                    }


                }
                // set the word back to original value for each step
                word = pn.getWord().toCharArray();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LadderWords lw = new LadderWords();
        lw.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"));
                /*
                "hit"
"cog"
["hot","dot","dog","lot","log"]
                 */
    }

 /*
preprocessing:
1. get unique characters at each
2. add words from words list to map or trie to easily test validity
searching:
3. do breadth first search of all possible words 1 position away, ignoring previous words
4. will have to use a data structure to store the path, or at least the number of steps, in addition to the current word
*/
}
