/*
This question is from https://leetcode.com/problems/soup-servings/
Difficulty: medium

There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:

Serve 100 ml of soup A and 0 ml of soup B
Serve 75 ml of soup A and 25 ml of soup B
Serve 50 ml of soup A and 50 ml of soup B
Serve 25 ml of soup A and 75 ml of soup B
When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.

Note that we do not have the operation where all 100 ml's of soup B are used first.

Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.



Example:
Input: N = 50
Output: 0.625
Explanation:
If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.

Notes:

0 <= N <= 10^9.
Answers within 10^-6 of the true value will be accepted as correct.

*/

// T:O(N^2), S:O(N^2), 3ms
class Solution {
    public double soupServings(int N) {
        if(N > 5000) return 1;

        int n = (N/25) + (N%25 > 0 ? 1:0);

        double[][] memo = new double[n+1][n+1];

        for(int i=0; i <= 2*n; i++){
            for(int A = 0; A <=n ; A++){
                int B = i - A;
                if(B < 0 || B > n) continue;
                double ans = 0;
                if(A == 0 && B == 0){
                    ans = 0.5;
                }else if(A == 0){
                    ans = 1;
                }else if(A > 0 && B > 0){
                    ans = 0.25*(memo[max(A-4)][B] +
                                memo[max(A-3)][max(B-1)] +
                                memo[max(A-2)][max(B-2)] +
                                memo[max(A-1)][max(B-3)]);
                }
                // System.out.println("A: "+A+", B: "+B+", ans: "+ans);
                memo[A][B] = ans;
            }
        }

        return memo[n][n];
    }

    public int max(int a){
        return Math.max(0, a);
    }

}

// Time Limit Exceeded
class Solution {
    public double soupServings(int N) {
        if(N > 5000) return 1;

        int n = (N/25) + (N%25 > 0 ? 1:0);

        return recursive(n, n , new double[n+1][n+1]);
    }

    public double recursive(int A, int B, double[][] memo){
        if(A <= 0 && B <= 0) return 0.5;
        if(A <= 0) return 1;
        if(B <= 0 ) return 0;

        int[] serveA = {4, 3, 2, 1};
        int[] serveB = {0, 1, 2, 3};
        // int[] serveA = {100, 75, 50, 25};
        // int[] serveB = {0, 25, 50, 75};

        memo[A][B] = 0;

        for(int i = 0; i < serveA.length; i++){
            memo[A][B] += recursive(A - serveA[i], B - serveB[i], memo);
        }

        memo[A][B] = memo[A][B] * 0.25;

        return memo[A][B];
    }
}