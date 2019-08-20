/**
This question is from https://leetcode.com/problems/divide-two-integers/
Difficulty: medium

Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
*/

// T:O(32), S:O(1), 1 ms (100%)
class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == (1 << 31) && divisor == -1) return (1 << 31) - 1;
        int result = 0;
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);

        for(int shift = 31; shift >= 0; shift --){
            if((a >>> shift) - b >= 0){
                result = result + (1 << shift);
                a = a - (b << shift);
            }
        }


        if(dividend > 0 ^ divisor > 0){
            return 0 - result;
        }
        return result;
    }
}

// T:O(Q), S:O(logQ), 2 ms (17.12%)
class Solution {
    public int divide(int dividend, int divisor) {
        int sign = 1;

        if(divisor == 0) throw new Error();

        if(dividend < 0 ^ divisor < 0){
            sign = -1;
        }

        long upper = Math.abs(Long.valueOf(dividend));
        long bottom = Math.abs(Long.valueOf(divisor));

        long quotient = longDivide(upper, bottom);

        if(quotient > Integer.MAX_VALUE){
            return sign < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        if(sign < 0) return (int)(0 - quotient);
        return (int)quotient;
    }

    private long longDivide(long upper, long bottom){

        if(upper < bottom) return 0;

        long sum = bottom;
        long multiple = 1;

        while((sum + sum) < upper){
            sum = sum + sum;
            multiple = multiple + multiple;
        }

        return multiple + longDivide(upper - sum , bottom);
    }
}