/**
This question is from https://leetcode.com/problems/first-missing-positive/
Difficulty: hard

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
*/

// T:O(N), S:O(1), 0 ms (100%)
class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;

        int index = 0;

        while(index < nums.length){
            if(nums[index] == index + 1 || nums[index] <= 0 || nums[index] > nums.length){
                index++;
            }else if(nums[nums[index] - 1] != nums[index]){
                swap(nums, index, nums[index] - 1);
            }else{
                index++;
            }
        }
        // System.out.println(Arrays.toString(nums));
        int result = 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < 0) continue;
            if(nums[i] == result){
                result++;
            }else{
                return result;
            }
        }

        return result;
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}