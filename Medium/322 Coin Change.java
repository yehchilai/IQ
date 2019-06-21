/*
This question is from https://leetcode.com/problems/coin-change/
Difficulty: medium

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
*/
// T:O(N), S:O(N), 11 ms
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int currentAmount = 1; currentAmount <= amount; currentAmount++){
            for(int i= 0; i < coins.length; i++){
                int coin = coins[i];
                if(currentAmount - coin >= 0 && dp[currentAmount - coin] != -1){
                    dp[currentAmount] = Math.min(dp[currentAmount], dp[currentAmount - coin] + 1);
                }
            }

            if(dp[currentAmount] == amount + 1){
                dp[currentAmount] = -1;
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
}