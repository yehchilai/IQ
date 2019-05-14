/*
This question is from https://leetcode.com/problems/target-sum/
Difficulty: medium

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

// 2-d DP, T:O(constant*N), S:O(constant*N), 8 ms
class Solution {

    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;

        for(int i = 1; i < nums.length; i++){
            for(int sum = -1000; sum <= 1000; sum++){
                if(dp[i-1][sum + 1000] > 0){
                    dp[i][sum + 1000 + nums[i]] += dp[i - 1][sum + 1000];
                    dp[i][sum + 1000 - nums[i]] += dp[i - 1][sum + 1000];
                }
            }
        }
        if(S > 1000) return 0;
        return dp[nums.length - 1][S + 1000];
    }

}

// T:O(2^N), S:O(1), 375 ms
class Solution {

    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        search(0, nums, S, 0);
        return count;
    }

    private void search(int index, int[] nums, int S, int sum){

        if(index == nums.length){
            if(sum == S) count++;
            return;
        }

        search(index + 1, nums, S, sum - nums[index]);
        search(index + 1, nums, S, sum + nums[index]);
    }
}