/**
This question is from https://leetcode.com/problems/rotate-string/
Difficulty: easy

We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.
*/
// T:O(N), S:O(1), 0 ms (100%)
class Solution {
    public boolean rotateString(String A, String B) {
        if(A == null || B == null) return false;
        int aLen = A.length();
        int bLen = B.length();

        if(aLen != bLen) return false;
        if(A.equals(B)) return true;
        A = A + A;
        // System.out.println(A);
        aLen = aLen + aLen;

        int i = 0;
        int j = 0;

        while(i < aLen){
            if(A.charAt(i) == B.charAt(j)){
                j++;
                i++;
                if(j == bLen) return true;
            }else if(j > 0){
                j = 0;
            }else{
                i++;
            }
        }

        return false;
    }
}

// T:O(N*N), str.equals takes N operations, S:O(1), 1 ms (30.98%)
class Solution {
    public boolean rotateString(String A, String B) {
        if(A == null || B == null) return false;
        int aLen = A.length();
        int bLen = B.length();

        if(aLen != bLen) return false;
        if(A.equals(B)) return true;
        for(int i = 1; i < aLen; i++){
            String str = A.substring(i, aLen) + A.substring(0, i);
            // System.out.println(i+": "+A.substring(i, aLen)+"+" + A.substring(0, i)+" = "+str);
            if(str.equals(B)) return true;
        }

        return false;
    }
}