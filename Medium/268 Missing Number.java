/*
This question is from https://leetcode.com/problems/missing-number/
Difficulty: medium

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

*/
// Sort array, T:O(nlogn), M:O(1), 14ms
public class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i) return i;
        }

        return nums.length;
    }
}

// Math: Arithmetic progression, T:O(N), M:O(1) 1ms
public class Solution {
    public int missingNumber(int[] nums) {
        int sumWithoutN = ((nums.length - 1)*(nums.length))/2;
        int sumTmp = 0;
        boolean containsN = false;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == nums.length) containsN = true;
            sumTmp = sumTmp + nums[i];
        }

        if(containsN){
            return sumWithoutN - (sumTmp - nums.length);
        }else{
            return nums.length;
        }
    }
}

// bit manipulation, T:O(N), M:O(1), 1ms
public class Solution {
    public int missingNumber(int[] nums) {
        int i = 0, xor = 0;;

        for(i = 0; i < nums.length; i++){
            xor = xor ^ i ^ nums[i];
        }

        return xor ^ i;
    }
}
