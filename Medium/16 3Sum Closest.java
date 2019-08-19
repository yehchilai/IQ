/**
This questio is from https://leetcode.com/problems/3sum-closest/
Diffciulty: medium

Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

// T:O(N^2 + NlogN), S:O(N), 4 ms (95.84%)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int ans = -1;

        for(int i = 0; i < nums.length - 2; i++){
            int lo = i + 1;
            int hi = nums.length - 1;
            while(lo < hi){

                int sum = nums[lo] + nums[hi] + nums[i];
                if(sum == target) return sum;

                int diff = Math.abs(sum - target);
                if(diff < min){
                    min = diff;
                    ans = sum;
                }

                if(sum < target){
                    lo++;
                }else{
                    hi--;
                }
            }
        }
        return ans;
    }
}

// T:O(N^3), S:O(1), 76 ms (7.23%)
class Solution {
    public int threeSumClosest(int[] nums, int target) {

        int min = Integer.MAX_VALUE;
        int ans = -1;

        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i + 1; j < nums.length - 1; j++){
                for(int k = j + 1; k < nums.length; k++){
                    int sum = nums[i] + nums[j] + nums[k];
                    int diff = Math.abs(sum - target);
                    if(diff < min){
                        ans = sum;
                        min = diff;
                    }
                }
            }
        }

        return ans;
    }
}