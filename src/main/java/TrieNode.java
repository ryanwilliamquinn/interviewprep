import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Character c;
    Map<Character, TrieNode> childNodes = new HashMap<>();

    // root node constructor
    public TrieNode() {};

    public TrieNode(Character c) {
        this.c = c;
    }

    public boolean find(String s) {
        TrieNode current = this;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            TrieNode child = current.childNodes.get(c);
            if (child == null) {
                return false;
            } else {
                current = child;
            }
        }
        return true;
    }

    public TrieNode child(Character c) {
       return childNodes.get(c);
    }

    public void add(Character c, TrieNode t) {
        childNodes.put(c, t);
    }

}
