/*
This question is from https://leetcode.com/problems/strobogrammatic-number/
Difficulty: easy

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false
*/
// T:O(N), S:O(1), 1 ms
class Solution {
    public boolean isStrobogrammatic(String num) {
        int[] pattern = new int[]{0,1,-1,-1,-1,-1,9,-1,8,6};

        int len = num.length();

        StringBuilder sb = new StringBuilder();

        for(int i = len - 1; i >= 0; i--){
            char c = num.charAt(i);
            // System.out.println(c+": "+pattern[c - '0']+", "+(char)pattern[c - '0']);
            if(pattern[c - '0'] == -1) return false;

            sb.append(pattern[c - '0']);
        }
        System.out.println(sb.toString());
        return sb.toString().equals(num);
    }
}