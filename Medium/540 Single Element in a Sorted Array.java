/*
This question is from https://leetcode.com/problems/single-element-in-a-sorted-array/
Difficulty: medium

Given a sorted array consisting of only integers where every element appears exactly twice except for one element which appears exactly once. Find this single element that appears only once.



Example 1:

Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: [3,3,7,7,10,11,11]
Output: 10


Note: Your solution should run in O(log n) time and O(1) space.

*/
// T:O(logN), S:O(1), 0 ms
class Solution {
    public int singleNonDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];

        int left = 0;
        int right = nums.length;

        while(left < right){
            int mid = (left + right) / 2;

           if(mid % 2 == 0){ // even index
               if(mid + 1 < nums.length && nums[mid] == nums[mid + 1]){
                   left = mid + 2;
               }else{
                   right = mid - 1;
               }
           }else{
               if(mid - 1 >= 0 && nums[mid] == nums[mid - 1]){
                   left = mid + 1;
               }else{
                   right = mid - 1;
               }
           }
        }
        return nums[left];
    }
}