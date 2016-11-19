/*
This question is from https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
Difficulty: medium

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

*/

// math, greedy, T:O(N), M:O(1)
public class Solution {
    public int maxProfit(int[] prices) {
        int result = 0;

        if(prices.length < 1) return result;

        for(int i = 1 ;i < prices.length; i++){
            int sub = prices[i] - prices[i - 1];
            if(sub > 0) result = result + sub;
        }

        return result;
    }
}
