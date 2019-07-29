/**
This question is from https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
Difficulty: hard

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

*/

// T:O(kn), S:O(kn), 1 ms (99.77%)
// dp(k, i) = max{dp(k, i-1), dp(k-1, j-1) + prices(i) - prices(j)}
// dp(k, i) = max{dp(k, i-1), prices(i) - min}
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;

        int[][] dp = new int[3][prices.length];

        for(int k = 1; k <= 2; k++){
            int min = prices[0];
            for(int i = 1; i < prices.length; i++){
                min = Math.min(min, prices[i] - dp[k - 1][i]);
                dp[k][i] = Math.max(dp[k][i-1], prices[i] - min);
            }
        }

        return dp[2][prices.length - 1];
    }
}

// T:O(N), S:O(1), 1 ms (99.97%)
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;

        int buy1 = Integer.MAX_VALUE;
        int buy2 = Integer.MAX_VALUE;
        int sell1 = 0;
        int sell2 = 0;

        for(int i = 0; i < prices.length; i++){
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }

        return sell2;
    }
}