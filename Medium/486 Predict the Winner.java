/*
This question is from https://leetcode.com/problems/predict-the-winner/
Difficulty: medium

Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2.
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.

*/
// T:O(N^2), S:(N^2), 0 ms
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length + 1][nums.length];

        for(int start = nums.length; start >= 0; start--){
            for(int end = start + 1; end < nums.length ; end++){
                int a = nums[start] - dp[start + 1][end];
                int b = nums[end] - dp[start][end - 1];

                dp[start][end] = Math.max(a, b);
            }
        }

        return dp[0][nums.length - 1] >= 0;
    }


}

// T:O(2^N), S:O(1), 45 ms
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return win(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int win(int[] nums, int start, int end, int turn){
        if(start == end) return turn * nums[start];

        int chooseFirst = turn * nums[start] + win(nums, start + 1 , end, -turn);
        int chooseLast = turn * nums[end] + win(nums, start, end - 1, -turn);

        return turn* Math.max(turn*chooseFirst, turn*chooseLast);
    }
}