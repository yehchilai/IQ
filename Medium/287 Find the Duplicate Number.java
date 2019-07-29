/**
This question is from https://leetcode.com/problems/find-the-duplicate-number/
Difficulty: medium

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

// T:O(N), S:O(1), 0 ms (100.00%)
// Floyd's Tortoise and Hare (Cycle Detection)
class Solution {
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int slow = nums[0];
        int fast = nums[0];

        do{
            slow = nums[slow];
            fast = nums[nums[fast]];

        }while(slow != fast);

        // System.out.println(slow);
        int ans = nums[0];

        while(ans != slow){
            ans = nums[ans];
            slow = nums[slow];
        }

        return ans;
    }
}

// You must not modify the array (assume the array is read only). Cannot sort. T:O(NlogN), S:O(1), 8 ms (7.92%)
class Solution {
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 1; i++){
            int index = Arrays.binarySearch(nums, i+1, nums.length, nums[i]);
            if(index >= 0){
                return nums[index];
            }
        }

        return 0;
    }
}

// T:O(N^2), S:O(1), 106 ms (5.03%)
class Solution {
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        for(int i = 0 ; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if(nums[i] == nums[j]) return nums[i];
            }
        }

        return 0;
    }
}