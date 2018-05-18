/*
This question is from https://leetcode.com/problems/unique-morse-code-words/description/
Difficulty: easy

International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows: "a" maps to ".-", "b" maps to "-...", "c" maps to "-.-.", and so on.

For convenience, the full table for the 26 letters of the English alphabet is given below:

[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter. For example, "cab" can be written as "-.-.-....-", (which is the concatenation "-.-." + "-..." + ".-"). We'll call such a concatenation, the transformation of a word.

Return the number of different transformations among all words we have.

Example:
Input: words = ["gin", "zen", "gig", "msg"]
Output: 2
Explanation:
The transformation of each word is:
"gin" -> "--...-."
"zen" -> "--...-."
"gig" -> "--...--."
"msg" -> "--...--."

There are 2 different transformations, "--...-." and "--...--.".


Note:

The length of words will be at most 100.
Each words[i] will have length in range [1, 12].
words[i] will only consist of lowercase letters.

*/
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = int[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        Set<String> output = new HashSet();
        // go through words
        for(int i=0; i<words.length; i++){
            int len = words[i].length();
            String morseWord = "";
            // go through charcters in a word
            for(int j=0; j < len; j++){
                morseWord = morseWord + morse[words[i].charAt(j) - 'a'];
            }
            // add morse word into the set
            output.add(morseWord);
        }

        return output.size();
    }
}

// https://leetcode.com/problems/unique-morse-code-words/discuss/130646/Java-using-functional-programming
class Solution {

    private static final String[] MAP = {".-","-...","-.-.","-..",".","..-.","--.","....","..",
                                         ".---","-.-",".-..","--","-.","---",".--.","--.-",".-.",
                                          "...","-","..-","...-",".--","-..-","-.--","--.."};

    public int uniqueMorseRepresentations(String[] words) {
        return Arrays.stream(words).map(Solution::convertToMorse).collect(Collectors.toSet()).size();
    }

    private static String convertToMorse(String word){
        StringBuilder sb = new StringBuilder();
        word.chars().forEach(c -> sb.append(MAP[c-97]));
        return sb.toString();
    }
}