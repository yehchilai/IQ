/**
This question is from https://leetcode.com/problems/maximum-product-subarray/
Difficulty: medium

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

*/

// T:O(N), S:O(1), 1 ms (99.16%)
class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int ans = nums[0];
        int max = ans;
        int min = ans;

        for(int i = 1; i < nums.length; i++){
            if(nums[i] < 0){
                int tmp = min;
                min = max;
                max = tmp;
            }

            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);

            ans = Math.max(ans, max);
        }

        return ans;
    }
}

// T:O(N^2), S:O(1), 92 ms (5.17%)
class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++){
            int product = 1;
            for(int j = i; j < nums.length; j++){
                product = product * nums[j];
                max = Math.max(max, product);
            }
        }

        return max;
    }
}