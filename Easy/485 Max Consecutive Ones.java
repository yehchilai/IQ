/*
This question is from https://leetcode.com/problems/max-consecutive-ones/
Difficulty: easy

Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000

*/
// T:O(N), S:O(1), 3 ms
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = Integer.MIN_VALUE;
        int count = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                count++;
                // System.out.println(i+": "+count);
                max = Math.max(max, count);
            }else{
                count = 0;
            }

        }

        return Math.max(max, 0);
    }
}