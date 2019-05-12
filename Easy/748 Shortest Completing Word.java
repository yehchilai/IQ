/*
This question is from https://leetcode.com/problems/shortest-completing-word/
Difficulty: easy

Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate

Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.

It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.

The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.

Example 1:
Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
Output: "steps"
Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
Note that the answer is not "step", because the letter "s" must occur in the word twice.
Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
Example 2:
Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
Output: "pest"
Explanation: There are 3 smallest length words that contains the letters "s".
We return the one that occurred first.
Note:
licensePlate will be a string with length in range [1, 7].
licensePlate will contain digits, spaces, or letters (uppercase or lowercase).
words will have a length in the range [10, 1000].
Every words[i] will consist of lowercase letters, and have length in range [1, 15].
*/
// T:O(N*K), S:O(K), 11 ms
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        String sStr = "";
        int min = Integer.MAX_VALUE;
        HashMap<Character, Integer> mapLP = new HashMap();
        licensePlate = licensePlate.toLowerCase();
        int len = licensePlate.length();

        for(int i = 0; i < len; i++){
            char c = licensePlate.charAt(i);
            if(c >= 'a' && c <= 'z'){
                int count = mapLP.getOrDefault(c, 0);
                mapLP.put(c, count + 1);
            }
        }

        for(int i = 0; i < words.length; i++){
            String str = words[i];
            if(match(mapLP, str)){
                int currentLen = str.length();
                if(currentLen < min){
                    sStr = str;
                    min = currentLen;
                }
            }
        }


        return sStr;
    }

    private boolean match(HashMap<Character, Integer> mapLP, String word){
        HashMap<Character, Integer> map = new HashMap();
        int len = word.length();

        for(int i = 0 ; i < len; i++){
            char c = word.charAt(i);
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }

        for(Map.Entry<Character, Integer> entry: mapLP.entrySet()){
            if(map.containsKey(entry.getKey())){
                int count = map.get(entry.getKey());
                if(count < entry.getValue()) return false;
            }else{
                return false;
            }
        }

        return true;
    }
}