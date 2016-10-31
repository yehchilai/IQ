/*
This question is from https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
Difficulty: easy

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.

*/

// two for loop, T:O(n^2), Time Limit Exceeded
public class Solution {
    public int maxProfit(int[] prices) {
        int result = 0;
        for(int i = prices.length - 1; i >= 0; i-- ){
            for(int j = i; j >= 0; j--){
                int diff = prices[i] - prices[j];
                if(diff > 0) result = Math.max(result, diff);
            }
        }
        return result;
    }

}

// one loop,  T:O(n), 3ms
public class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i = 0; i < prices.length; i++){
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }

        return max;
    }

}
