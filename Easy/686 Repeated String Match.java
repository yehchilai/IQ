/*
This question is from https://leetcode.com/problems/repeated-string-match/
Difficulty: easy

Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.
*/
// T:O(), S:O(), 116ms
class Solution {
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder(A);
        int copy = 1;

        while(sb.length() < B.length()){
            sb.append(A);
            copy++;
        }
        // System.out.println(A+", "+sb.toString()+": "+copy);
        if(sb.indexOf(B) > -1) return copy;
        sb.append(A);
        if(sb.indexOf(B) > -1) return copy + 1;

        return -1;
    }
}
