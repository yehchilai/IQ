/*
This question is from https://leetcode.com/problems/integer-break/
Difficulty: medium

Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.
*/

// Math. Find pattern 2+2+3+3+...., T:O(1), M:O(1), 0ms
public class Solution {
    public int integerBreak(int n) {
        if(n <= 2) return 1;
        if(n == 3) return 2;
        long quotient = 0;
        long product = 2;
        quotient = n/3;
        if(n%3 == 1){
            quotient = quotient - 1;
            product = 4;
        }else if(n%3 == 0){
            product = 1;
        }

        return (int)(product * (long)Math.pow(3,quotient));
    }
}
