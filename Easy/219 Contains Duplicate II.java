/*
The question is from https://leetcode.com/problems/contains-duplicate-ii/
Difficulty: easy

Given an array of integers and an integer k, 
find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] 
and the difference between i and j is at most k.

*/
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> duplicate = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(duplicate.containsKey(nums[i])){
                int difference = i - duplicate.get(nums[i]);
                duplicate
            }else{
                duplicate.put(nums[i], i)
            }
        }
    }
}