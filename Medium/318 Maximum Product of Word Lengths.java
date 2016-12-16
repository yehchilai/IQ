/*
This question is from https://leetcode.com/problems/maximum-product-of-word-lengths/
Difficulty: medium

Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.

*/
// check all element, T:O(N^2), M:(M), Time Limit Exceeded
public class Solution {
    public int maxProduct(String[] words) {
        int result = 0;
        for(int i = 0; i < words.length; i++){
            for(int j = i + 1; j < words.length; j++){
                result = Math.max(result, product(words[i], words[j]));
            }
        }

        return result;
    }

    public int product(String a, String b){
        HashSet<Character> set = new HashSet<>();
        for(Character c : a.toCharArray()){
            set.add(c);
        }

        for(Character c: b.toCharArray()){
            if(set.contains(c)) return 0;
        }

        return a.length() * b.length();
    }
}

// bit manipulate, T:O(N^2), M:O(N), 33ms
public class Solution {
    public int maxProduct(String[] words) {
        int result = 0;
        int[] bitmaps = new int[words.length];

        for(int i = 0; i < words.length; i++){
            char[] currentWord = words[i].toCharArray();
            for(int j = 0; j < currentWord.length; j++){
                // use 26 bits to record if the character is in the word.
                // e.g.   ...0000000100101
                //                  f  c a
                bitmaps[i] = bitmaps[i] | (1 << (currentWord[j] - 'a'));
            }
        }

        for(int i = 0; i < words.length; i++){
            for(int j = i + 1; j < words.length; j++){
                if((bitmaps[i] & bitmaps[j]) == 0){
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }

}
