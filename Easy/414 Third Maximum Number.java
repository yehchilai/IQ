/*
This question is from https://leetcode.com/problems/third-maximum-number/
Difficulty: easy

Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

*/
// One for loop, T:O(N), M:O(1), 4ms
public class Solution {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;

        for(int i=0; i < nums.length; i++){
            if(nums[i] > first){
                third = second;
                second = first;
                first = (long)nums[i];
            }else if(nums[i] > second && nums[i] != first){
                third = second;
                second = (long)nums[i];
            }else if(nums[i] > third && nums[i] != first && nums[i] != second){
                third = (long)nums[i];
            }
        }

        if(third == Long.MIN_VALUE) return (int)first;
        return (int)third;
    }
}
