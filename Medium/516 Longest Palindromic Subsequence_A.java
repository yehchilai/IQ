/*
This question is from https://leetcode.com/problems/longest-palindromic-subsequence/description/
Difficulty: medium

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
*/
// Check the "subsequence" difimation
// Sample : leetcode
// Both substring and subsequence: leet, code
// Sub sequence: ltcode, eecode, ecode, ltde // and not substring
// Neither sequence nor substring: tlc, dlt
// https://algorithms.tutorialhorizon.com/longest-palindromic-subsequence/

// Dynamic programming T: O(N^2), S: O(N^2), 50ms
class Solution {

    public int longestPalindromeSubseq(String s) {

        // string length
        int len = s.length();

        // dynamic programming array
        // start is row and end is column
        int[][] dp = new int[len][len];

        // go through all subset, and begin with 1 element
        for(int start = len - 1; start >= 0; start--){
            dp[start][start] = 1;
            for(int end = start + 1; end < len; end++){
                if(s.charAt(start) == s.charAt(end)){
                    dp[start][end] = dp[start + 1][end - 1] + 2;
                }else{
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end -1]);
                }
            }
        }

        return dp[0][len - 1];
    }

}