/*
This question is from https://leetcode.com/problems/domino-and-tromino-tiling/
Difficulty: medium

We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.

XX  <- domino

XX  <- "L" tromino
X
Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.

(In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)

Example:
Input: 3
Output: 5
Explanation:
The five different ways are listed below, different letters indicates different tiles:
XYZ XXZ XYY XXY XYY
XYZ YYZ XZZ XYY XXY
Note:

N  will be in range [1, 1000].
*/

// Math question, T:O(N), S:O(N), 0 ms
// 1. f(n) = f(n - 1) + f(n - 2) + g(n - 1)
// 2. g(n) = g(n - 1) + 2 * f(n - 2)
// from 1, 3. f(n) = 2 * f(n - 1) - f(n - 3) + g(n - 1) - g(n - 2)
// use 2 from 3, 4. f(n) = 2 * f(n - 1) + f(n - 3)
class Solution {
    public int numTilings(int N) {
        int[] ans = new int[N + 1];

        if(N == 0) return 1;
        if(N == 1) return 1;
        if(N == 2) return 2;

        ans[1] = 1;
        ans[2] = 2;
        ans[3] = 5;

        int modulo = 1000000007;

        for(int i = 4; i <= N; i++){
            ans[i] = (2*ans[i - 1]) % modulo + ans[i - 3] % modulo;
            ans[i] %= modulo;
        }

        return ans[N];
    }
}