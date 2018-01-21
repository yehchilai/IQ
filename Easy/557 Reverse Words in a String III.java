/*
This question is from https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
Difficulty: easy

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.

*/
class Solution {
    public String reverseWords(String s) {
        String[] original = s.split(" ");
        String[] reverse = new String[original.length];
        for(int i = 0; i < original.length; i++){
            StringBuilder sb = new StringBuilder(original[i]);
            reverse[i] = sb.reverse().toString();
        }
        return String.join(" ", reverse);
    }
}