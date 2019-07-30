/**
This question is from https://leetcode.com/problems/regular-expression-matching/
DIfficulty: hard

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
*/

// T:(S*P), S:O(S*P), 2 ms (96.14%)
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;

        int sLen = s.length();
        int pLen = p.length();

        boolean[][] dp = new boolean[sLen+1][pLen+1];

        // set * as true
        dp[0][0] = true;
        for(int j = 1; j < dp[0].length; j++){
            if(p.charAt(j - 1) == '*'){
                if(dp[0][j - 1] || (j > 1 && dp[0][j - 2])){
                    dp[0][j] = true;
                }
            }
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){

                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);

                if( sChar == pChar || pChar == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }

                if(pChar == '*'){
                    if(sChar != p.charAt(j - 2) && p.charAt(j - 2) != '.'){
                        dp[i][j] = dp[i][j - 2];
                    }else{
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i][j - 2];
                    }
                }
            }
        }

        // for(boolean[] arr: dp) System.out.println(Arrays.toString(arr));
        return dp[sLen][pLen];
    }
}
