/*
This question is from https://leetcode.com/problems/product-of-array-except-self/
Difficulty: medium

Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
*/
// T:O(N), M:O(N), 3ms
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] shiftRight = new int[nums.length];
        int[] shiftLeft = new int[nums.length];
        shiftRight[0] = 1;
        shiftLeft[nums.length - 1] = 1;
        for(int i = 1; i < nums.length ;i++){
            shiftRight[i] = nums[i - 1] * shiftRight[i - 1];
        }

        for(int i = nums.length - 2; i >= 0; i--){
            shiftLeft[i] = nums[i+1] * shiftLeft[i+1];
        }

        int[] result = new int[nums.length];

        for(int i = 0; i < nums.length; i ++){
            result[i] = shiftRight[i] * shiftLeft[i];
        }

        return result;
    }
}

// T:O(N), M:O(1), 2ms
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        result[0] = 1;
        for(int i = 1; i < result.length; i++){
            result[i] = result[i - 1] * nums[i - 1];
        }

        int tmp = 1;
        tmp = tmp * nums[nums.length - 1];
        for(int i = result.length - 2; i >= 0; i--){
            result[i] = result[i] * tmp;
            tmp = tmp * nums[i];
        }

        return result;
    }
}
