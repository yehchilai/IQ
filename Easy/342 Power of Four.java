/*
This question is from https://leetcode.com/problems/power-of-four/
Difficulty: easy

Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?

*/
// Bit manipulation, T:O(1), M:O(1), 3ms
public class Solution {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num&(num - 1)) == 0 && (num & 0x55555555) != 0 ;
    }
}
