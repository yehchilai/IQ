/*
This question is from https://leetcode.com/problems/repeated-substring-pattern/
Difficulty: easy

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.



Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/
// T:O(N), S:O(N), 129 ms
class Solution {
    public boolean repeatedSubstringPattern(String s){
        int len = s.length();

        for(int size = 1; size <= len/2; size++){
            if(len % size == 0){
                String substr = s.substring(0, size);
                StringBuilder sb = new StringBuilder();
                int duplicate = len / size;
                for(int i = 0; i < duplicate; i++){
                    sb.append(substr);
                }
                // System.out.println(sb.toString());
                if(sb.toString().equals(s)) return true;
            }
        }

        return false;
    }
}