/*
This question is from https://leetcode.com/problems/new-21-game/
Difficulty: medium

Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.
Example 2:

Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
Example 3:

Input: N = 21, K = 17, W = 10
Output: 0.73278
Note:

0 <= K <= N <= 10000
1 <= W <= 10000
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
The judging time limit has been reduced for this question.
*/

// DP, T:O(N+W), S:O(N), 4 ms
class Solution{
	public double new21Game(int N, int K, int W) {
        if(K == 0 || N >= K+W) return 1;

        double[] dp = new double[N+1];
        double ans = 0;
        double Wsum = 1;
        dp[0] = 1;

        for(int i = 1; i <= N; i++){
            dp[i] = Wsum/(double)W;
            if(i < K){
                Wsum += dp[i];
            }else{
                ans += dp[i];
            }

            if(i - W >= 0) Wsum -= dp[i - W];
        }

        return ans;

   }
}

// T:O(K*W+(N−K)), S:O(N+W), Time Limit Exceeded
class Solution {
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[N + W + 1];

        for(int i = K ; i <= N ; i++){
            dp[i] = 1;
        }


        for(int i = K - 1 ; i >= 0 ; i--){
            double sum = 0;
            for(int j = i + 1 ; j <= i + W; j++){
                // System.out.println("j: "+j+", "+dp[j]);
                sum += dp[j];
            }
            // System.out.println("    k: "+i+", "+sum);
            dp[i] = sum/(double)W;
        }

        return dp[0];
    }
}