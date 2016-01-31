/*
This question is from https://leetcode.com/problems/remove-element/

Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.


Time Comlexity: O(N), N: the number of elements in the array
*/
public class Solution {
    public int removeElement(int[] nums, int val) {
        int length = 0;
        for(int i: nums){
        	if(i != val){
        	    nums[length] = i;
        	    length++;
        	} 
        }
        return length;
    }
}