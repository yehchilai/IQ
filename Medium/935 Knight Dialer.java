/*
This question is from https://leetcode.com/problems/knight-dialer/
Difficulty: medium

A chess knight can move as indicated in the chess diagram below:

 .



This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.



Example 1:

Input: 1
Output: 10
Example 2:

Input: 2
Output: 20
Example 3:

Input: 3
Output: 46


Note:

1 <= N <= 5000
*/

// explain
// T:O(N), https://hackernoon.com/google-interview-questions-deconstructed-the-knights-dialer-f780d516f029
// T:O(logN), https://hackernoon.com/google-interview-questions-deconstructed-the-knights-dialer-impossibly-fast-edition-c288da1685b8


// T:O(N), S:O(N), 15 ms (92.54)
class Solution {
    public int knightDialer(int N) {
        if(N == 0) return 0;

        // modulo
        int mod = 1_000_000_007;

        int[][] move = new int[][]{{4,6}, {6,8}, {7,9}, {4,8}, {0,3,9}, {}, {0,1,7}, {2,6}, {1,3}, {2,4}};

        int[] dp = new int[10];
        Arrays.fill(dp, 1);

        for(int i = 1; i < N; i++){
            int[] tmp = new int[10];
            // check all start points in this step
            for(int m = 0; m < move.length; m++){
                // System.out.println(m+": "+move[m].length);
                // run 10 start points with their neighbors
                for(int j = 0; j < move[m].length; j++){
                    // add previous numbers
                    int prev = dp[move[m][j]];
                    tmp[m] = (tmp[m] + prev) % mod;
                }
            }
            dp = tmp;
        }

        long ans = 0;
        // System.out.println(Arrays.toString(dp));
        for(int n : dp) ans = (ans + n) % mod;

        return (int)ans;
    }
}