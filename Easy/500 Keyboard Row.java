/*
This question is from https://leetcode.com/problems/keyboard-row/description/
DIfficulty: easy


Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.


American keyboard


Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
*/

// 4ms
class Solution {
    public String[] findWords(String[] words) {
        Map<Character,Integer> keyboard = generateMap();

        List<String> result = new LinkedList<String>();
        for(int i = 0; i < words.length; i++){
            int length = words[i].length();
            Boolean add = true;
            int row = keyboard.get(Character.toLowerCase(words[i].charAt(0)));
            for(int j = 0; j < length; j++){
                // System.out.println(keyboard.get(Character.toLowerCase(words[i].charAt(j))) + ", " +words[i].charAt(j));
                if(keyboard.get(Character.toLowerCase(words[i].charAt(j))) !=row){
                    add = false;
                    break;
                }
            }
            if (add) result.add(words[i]);
        }

        return result.toArray(new String[0]);
    }

    public Map<Character,Integer> generateMap(){
        String firstRow = "qwertyuiop";
        String secondRow = "asdfghjkl";
        String thirdRow = "zxcvbnm";

        Map<Character,Integer> keyboard = new HashMap<Character, Integer>();
        int length = firstRow.length();
        for(int i = 0; i < length; i++){
            keyboard.put(firstRow.charAt(i), 1);
        }

        int length2 = secondRow.length();
        for(int i = 0; i < length2; i++){
            keyboard.put(secondRow.charAt(i), 2);
        }

        int length3 = thirdRow.length();
        for(int i = 0; i < length3; i++){
            keyboard.put(thirdRow.charAt(i), 3);
        }

        return keyboard;
    }
}

// Best? 84ms
public String[] findWords(String[] words) {
    return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
}
