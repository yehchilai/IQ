/*
This question is from https://leetcode.com/problems/move-zeroes/
Difficulty: easy

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

Time Complexty = O(n+m)
*/
public class Solution{
	// i = 0 => zeros = 1, currentPosition = 0 [0, 1, 0, 3, 12]  
	// i = 1 => zeros = 1, currentPosition = 1 [1, 1, 0, 3, 12] 
	// i = 2 => zeros = 2, currentPosition = 1 [1, 1, 0, 3, 12] 
	// i = 3 => zeros = 2, currentPosition = 2 [1, 3, 0, 3, 12] 
	// i = 4 => zeros = 2, currentPosition = 3 [1, 3, 12, 3, 12]
	public void moveZeroes(int[] nums){
		int currentPosition = 0;
		for(int i = 0 ; i < nums.length; i++){
			if(nums[i] != 0){
				nums[currentPosition] = nums[i];
				currentPosition++;
			}
		}

		for(int i = currentPosition; i < nums.length; i++){
			nums[i] = 0;
		}
		// alternative
		// Arrays.fill(nums, currentPosition, nums.length, 0);
	}
}