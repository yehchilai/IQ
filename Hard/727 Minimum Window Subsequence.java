/*
This question is from https://leetcode.com/problems/minimum-window-subsequence/
Difficulty: hard

Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input:
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.


Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
*/
// DP, T:O(T*S), S:O(T*S), 47 ms
class Solution {
    public String minWindow(String S, String T) {
        int lenS = S.length();
        int lenT = T.length();

        if(lenT > lenS) return "";

        int[][] dp = new int[lenS][lenT];
        // fill dp
        for(int[] tmp: dp) Arrays.fill(tmp, -1);
        // record first character match index
        for(int i = 0; i < lenS; i++){
            if(S.charAt(i) == T.charAt(0)){
                dp[i][0] = i;
            }
        }

        // update the following match index
        for(int j = 1; j < lenT; j++){

            int previousIndex = -1;
            for(int i = 0; i < lenS; i++){
                if(S.charAt(i) == T.charAt(j)){
                    dp[i][j] = previousIndex;
                }
                previousIndex = Math.max(previousIndex, dp[i][j - 1]);
            }
        }

//         for(int i = 0; i < lenS; i++){
//             System.out.print(i+": ");
//             for(int n : dp[i]) System.out.print(n+", ");
//             System.out.println();
//         }

        // get substring length
        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;
        for(int i = 0; i < lenS; i++){
            if(dp[i][lenT - 1] != -1){
                int length = i - dp[i][lenT - 1] + 1;
                if(length < minLength){
                    startIndex = dp[i][lenT - 1];
                    minLength = length;
                }
            }
        }

        if(startIndex == -1) return "";
        return S.substring(startIndex, startIndex + minLength);
    }

}