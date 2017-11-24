import java.util.Map;

public class WordGames {
    /*
      Given a two dimensional array of   letters. Find a target word in the array
      Return a collection containing start and end positions for each word
     */
    private int[] search(char[][] letters, int row, int column, String target) {
        int[] out = {-1, -1};
        String[] directions = { "forward", "forwarddown", "backward", "backwardup" };

        return out;
    }

    public int[] wordsearch(char[][] letters, String target) {
        int width = letters.length;
        int height = letters[0].length;
        // search for the start or end character
        // if you find it, do depth first searches of the surrounding valid paths
        // stop early if you know there isn't enough space for the whole word
        int targetLength = target.length();
        char firstChar = target.charAt(0);
        char lastChar = target.charAt(targetLength - 1);

        for (int row = 0; row < height; row++) {
           for (int column = 0; column < width; column++) {
               char c = letters[row][column];
               // search for start or end char
               /*
               if (c == firstChar) {
                    do
               } else if ()
               */
           }
        }

        return null;
    }


}
