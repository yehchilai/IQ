/*
This question is from https://leetcode.com/problems/maximum-subarray/
Difficulty: easy

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

*/
// T:O(N), S:O(1), 1 ms
class Solution {
    public int maxSubArray(int[] nums) {
        int currentMax = nums[0];
        int max = nums[0];

        for(int i = 1; i < nums.length; i++){
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            max = Math.max(max, currentMax);
        }

        return max;
    }
}