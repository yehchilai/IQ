/**
This question is from https://leetcode.com/problems/longest-increasing-subsequence/
Difficulty: medium

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/

// DP + BinarySearch, T:O(NlogN), S:O(N), 1 ms(92.67%)
class Solution {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for(int i = 0 ;i < nums.length; i++){
            int index = Arrays.binarySearch(dp, 0, len, nums[i]);

            if(index < 0){
                index = -(index+1);
            }

            dp[index] = nums[i];

            if(index == len){
                len++;
            }
        }

        return len;
    }

}

// DP, T:O(N^2), S:O(N), 15 ms(22.90%)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        if(nums == null || nums.length == 0) return max;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++){
            int maxDp = 0;
            for(int j = 0; j <= i ; j++){
                if(nums[i] > nums[j]){
                    maxDp = Math.max(maxDp, dp[j]);
                }
            }

            dp[i] = maxDp + 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}