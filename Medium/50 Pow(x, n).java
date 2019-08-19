/**
This question is from https://leetcode.com/problems/powx-n/
Difficulty: medium

Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
*/

// T:O(logn), S:O(1), 0 ms (100.00%)
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if(N < 0){
            x = 1/x;
            N = -N;
        }

        double ans = 1;
        double currentProduct = x;
        for(long i = N; i > 0; i = i /2){
            if(i % 2 == 1){
                ans = ans * currentProduct;
            }

            currentProduct = currentProduct * currentProduct;
        }

        return ans;
    }
}

// T:O(logn), S:O(logn), 0 ms (100.00%)
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n == Integer.MIN_VALUE && x > 1) return 0;
        if(n < 0){
            x = 1/x;
            n = -n;
        }

        return n%2 == 0 ? myPow(x*x, n/2): x * myPow(x*x, n/2);
    }
}

// T:O(n), S:O(1), Time Limit Exceeded
class Solution {
    public double myPow(double x, int n) {
        long N = n;

        if(N < 0){
            x = 1/x;
            N = -N;
        }

        double ans = 1;

        for(int i = 0 ;i < N; i++){
            ans = ans * x;
        }

        return ans;
    }
}