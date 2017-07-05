public class Trie {

    private TrieNode root = new TrieNode();

    public void addWord(String word) {
        // traverse the trie, adding letters as needed
        TrieNode current = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            TrieNode t = current.child(c);
            if (t == null) {
                TrieNode child = new TrieNode(c);
                current.add(c, child);
                current = child;
            } else {
                current = t;
            }
        }
    }

    // add lookup
    public boolean contains(String word) {
        return root.find(word);
    }
}
