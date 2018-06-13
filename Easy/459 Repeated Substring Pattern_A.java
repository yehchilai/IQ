/*
This question is from https://leetcode.com/problems/repeated-substring-pattern/description/
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
// T:O(N), S:O(N), 111ms
class Solution {
    public boolean repeatedSubstringPattern(String s){
        // string length
        int len = s.length();
        // generate division size
        for( int size = 1; size <= len/2 ;size++){
            // check if the remainder is 0
            if(len%size == 0){
                // generate new string and compare the new string to s
                String subStr = s.substring(0, size);
                StringBuilder sb = new StringBuilder();
                int quotient = len/size;
                for(int i = 0 ; i < quotient; i++){
                    sb.append(subStr);
                }

                if(sb.toString().equals(s)) return true;
            }
        }
        return false;
    }
}

// T:O(N^2), S:O(1), 170ms
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // string length
        int len = s.length();

        // create subset size
        for(int size = 1; size <= len/2 ; size++){
            String subset = s.substring(0, size);
            // System.out.println("# "+subset);
            if(len%size == 0){
                boolean ifPattern = true;
                for(int i = size; i <= len; i=i+size){
                    // System.out.print(s.substring(i - size,i) + " ");
                    if(!subset.equals(s.substring(i - size,i))){
                        ifPattern = false;
                        break;
                    }
                }
                // System.out.println();
                if(ifPattern) return true;
            }

        }
        return false;
    }
}