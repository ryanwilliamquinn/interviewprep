import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class HuffmanEncoding {

    private static final char terminalCharacter = 'z';

    public TreeNode createHuffmanTree(PriorityQueue<TreeNode> pq) {
        // if the primary queue only has one element, attach it to an empty node
        if (pq.size() == 1) {
            TreeNode leafNode = pq.poll();
            TreeNode rootNode = new TreeNode(leafNode.getValue(), leafNode.getWeight());
            rootNode.setRight(leafNode);
            return rootNode;
        } else {
            // create the huffman tree from the individual nodes
            while (pq.size() > 1) {
                // get the smallest two nodes from the priority queue
                TreeNode smallest = pq.poll();
                TreeNode nextSmallest = pq.poll();
                // get the smallest character lexicographically to act as the character for the new parent node
                char smallestChar = smallest.value < nextSmallest.value ? smallest.value : nextSmallest.value;
                // get the sum of the weights to act as the weight for the new parent node
                Integer frequency = smallest.getWeight() + nextSmallest.getWeight();
                TreeNode newRoot = new TreeNode(smallestChar, frequency);
                newRoot.setLeft(smallest);
                newRoot.setRight(nextSmallest);
                pq.add(newRoot);
            }
        }

        return pq.poll();
    }

    /**
     * We need a list to create the randomly generated string. It is also convenient for initializing hte priority queue
     * @param frequencies
     * @return a sorted (ascending) list of treenodes
     */
    public List<TreeNode> createList(Map<Character, Integer> frequencies) {

        List<TreeNode> treeNodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            treeNodes.add(new TreeNode(entry.getKey(), entry.getValue(), true));
        }
        // sort the list before returning
        Collections.sort(treeNodes);
        return treeNodes;
    }

    private PriorityQueue<TreeNode> createQueue(List<TreeNode> frequencies) {
        PriorityQueue<TreeNode> pq = new PriorityQueue<>();
        pq.addAll(frequencies);
        return pq;
    }

    /**
     * Recursively creates the huffman table based on the tree created previously
     * @param root
     * @param path
     * @param huffmanTable
     * @return
     */
    public Map<Character, String> createHuffmanTable(TreeNode root, String path, Map<Character, String> huffmanTable) {
        if (root == null) {
            return huffmanTable;
        } else {
            if (root.isLeaf()) {
                huffmanTable.put(root.value, path);
            } else {
                createHuffmanTable(root.getLeft(), path + "0", huffmanTable);
                createHuffmanTable(root.getRight(), path + "1", huffmanTable);
            }
        }
       return huffmanTable;
    }

    /**
     * entry point, pass it a map of character frequencies
     * @param frequencies
     * @return
     */
    public Map<Character, String> createHuffmanTable(Map<Character, Integer> frequencies)  {
        List<TreeNode> treeNodes = createList(frequencies);
        PriorityQueue<TreeNode> pq = createQueue(treeNodes);
        TreeNode root = createHuffmanTree(pq);
        return createHuffmanTable(root, "", new HashMap<>());
    }

    /**
     * create a string based on the given length and character frequencies
     * @param frequencies
     * @param length
     * @return
     */
    public String createWeightedString(List<TreeNode> frequencies, int length) {
        // if length is 0 or negative, return empty
        if (length < 1 || frequencies == null || frequencies.size() < 1) {
            return "";
        }

        // create a list of weights and correlated characters, to manage the random character generation
        List<TreeNode> cutoffs = new ArrayList<>();

        int runningSum = 0;

        for (int i = 0; i < frequencies.size(); i++) {
            TreeNode currentNode = frequencies.get(i);
            if (currentNode.getValue() == terminalCharacter) {
                continue;
            }
            runningSum += currentNode.getWeight();
            TreeNode newNode = new TreeNode(currentNode.getValue(), runningSum);
            cutoffs.add(newNode);
        }

        StringBuilder out = new StringBuilder("");
        for (int j = 0; j < length; j++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, runningSum);
            for (TreeNode node : cutoffs) {
                if (node.getWeight() >= randomNum) {
                    out.append(node.getValue());
                    break;
                }
            }
        }
        // append EOF character
        out.append('z');

        return out.toString();
    }

    public List<Byte> encodeString(String input, Map<Character, String> huffmanTable) {
        String byteString = "";
        List<Byte> out = new ArrayList<>();
        for (char c : input.toCharArray()) {
            // for each character, look up the encoding in the huffman table
            // if it doesn't exist, throw
            String encoding = huffmanTable.get(c);

            if (encoding == null) {
                throw new Error("No encoding found for char: " + c);
            }
            byteString += encoding;
            if (byteString.length() >= 8) {
               out.add(createByteFromString(byteString.substring(0, 8)));
               byteString = byteString.substring(8);
            }
        }
        if (byteString.length() > 0) {
            out.add(createByteFromString(byteString));
        }
        return out;
    }

    /**
     *
     * @param input
     * @return
     */
    public byte createByteFromString(String input) {
        // add padding to right side to make length == 8
        if (input.length() < 8) {
            while (input.length() < 8) {
                // since we are only padding once per string encoding (the last byte), i don't worry about using a stringbuilder
                input += "0";
            }
        }
        int b = 0;
        for (int i = 0; i < input.length(); i++) {
            // shift the current bits once to the left
            b = b << 1;
            // or the first bit with a 1, to set it to 1, otherwise leave it as 0
            if (input.charAt(i) == '1') {
                b = b | 1;
            }
        }
        return (byte) b;
    }

    public String decode(List<Byte> bytes, Map<Character, String> huffmanTable) {
        Map<String, Character> swapped = huffmanTable.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        String decoded = "";
        String currentByte = "";
        int[] masks = {
                0x80,
                0x40,
                0x20,
                0x10,
                0x08,
                0x04,
                0x02,
                0x01
        };
        for (byte b : bytes) {
            for (int mask : masks) {
                if ((b & mask) == mask) {
                    currentByte += "1";
                } else {
                    currentByte += "0";
                }
                if (swapped.containsKey(currentByte)) {
                    char decodedChar = swapped.get(currentByte);
                    if (decodedChar == terminalCharacter) {
                        return decoded;
                    }
                    decoded += decodedChar;
                    currentByte = "";
                }

            }
        }
        return decoded;
    }

    public String createRandomString(Map<Character, Integer> frequencies, Integer length) {
        List<TreeNode> treeNodes = createList(frequencies);
        return createWeightedString(treeNodes, 21);
    }

    public static void main(String[] args) {
        Map<Character, Integer> frequencies = new HashMap<>();
        frequencies.put('a', 100);
        frequencies.put('b', 10);
        frequencies.put('c', 500);
        frequencies.put('d', 20);
        frequencies.put('e', 10);
        frequencies.put('f', 15);
        frequencies.put('g', 17);
        frequencies.put('h', 210);
        // z is EOF
        frequencies.put('z', 1);

        HuffmanEncoding he = new HuffmanEncoding();

        // create the huffman table
        Map<Character, String> table = he.createHuffmanTable(frequencies);

        // note that the weighted string includes a terminal character
        String weightedString = he.createRandomString(frequencies, 200);

        // encode the string
        List<Byte> bytes = he.encodeString(weightedString, table);

        // decode the string
        String decoded = he.decode(bytes, table);

        System.out.println(decoded.equals(weightedString.substring(0, weightedString.length() - 1)));
    }




}
