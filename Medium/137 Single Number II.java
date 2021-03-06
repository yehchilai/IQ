/*
This question is from https://leetcode.com/problems/single-number-ii/
Difficulty: medium

Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/
// bit manipulate, T:O(32N), M:O(1), 9ms
public class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++){
            int sum = 0;
            for(int j = 0; j < nums.length; j++){
                if(((nums[j]>>i) & 1) == 1){
                    sum++;
                }
            }
            sum = sum % 3;
            if(sum != 0){
                ans = ans + (sum << i);
            }
        }

        return ans;
    }
}
