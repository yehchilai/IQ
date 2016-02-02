/*
This question is from https://leetcode.com/problems/remove-duplicates-from-sorted-array/
Difficulty: easy

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
It doesn't matter what you leave beyond the new length.


Time Complexity: O()

[1,1,2,3,4,5,6,6,6,7,7,7,8,8,9,10]
[,,2,3,4,5,6,6,6,7,7,7,8,8,9,10]

d = 2
index = 1
[1,]
*/
public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int index = 0;
        int duplicate = nums[0];
        for(int i = 0;i < nums.length; i++){
        	if(nums[i] != duplicate){
        		nums[index] = duplicate;
        		duplicate = nums[i];
        		index++;
        	}
        }
        nums[index] = duplicate;
		index++;
        return index;
    }
}