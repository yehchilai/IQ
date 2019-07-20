/*
This question is from https://leetcode.com/problems/sort-colors/
DIfficulty: medium

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
*/

// T:O(N), S:O(1), 0 ms (100%)
// one-pass
class Solution {
    public void sortColors(int[] nums) {
        int start = 0;
        int current = start;
        int end = nums.length - 1;

        while(current <= end){
            if(nums[current] == 0){
                nums[current] = nums[start];
                nums[start] = 0;
                start++;
            }else if(nums[current] == 2){
                nums[current] = nums[end];
                nums[end] = 2;
                end--;
                current--;
            }
            current++;
            // System.out.println(Arrays.toString(nums));
        }

    }
}

// T:O(N), S:O(1), 0 ms (100%)
// two-pass
class Solution {
    public void sortColors(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int twos = 0;

        for(int i = 0; i < nums.length; i++){
            switch (nums[i]){
                case 0:
                    zeros++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    twos++;
                    break;
            }
        }
        // System.out.println(zeros+", "+ones+", "+twos);
        for(int i = 0; i < zeros ; i++) nums[i] = 0;
        // System.out.println(Arrays.toString(nums));
        for(int i = zeros; i < zeros + ones; i++) nums[i] = 1;
        // System.out.println(Arrays.toString(nums));
        for(int i = zeros + ones; i < nums.length; i++) nums[i] = 2;

    }
}