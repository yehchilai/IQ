/*
This question is from https://leetcode.com/problems/detect-capital/
Difficulty: easy

Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
*/
// T:O(N), S:O(N), 1 ms
class Solution {
    public boolean detectCapitalUse(String word) {

        // on line solution
        // return word == null || word.length() < 2 || word.equals(word.toLowerCase()) || word.equals(word.toUpperCase()) || word.substring(1).equals(word.substring(1).toLowerCase());

        String uppercase = word.toUpperCase();
        if(word.equals(uppercase)) return true;

        int count = 0;
        int len = word.length();
        for(int i = 0; i < len; i++){
            if(word.charAt(i) == uppercase.charAt(i)) count++;
        }

        if(count > 1) return false;

        if(count == 1 && word.charAt(0) != uppercase.charAt(0)) return false;

        return true;
    }
}