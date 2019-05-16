/*
This question is from https://leetcode.com/problems/integer-replacement/
Difficulty: medium

Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
*/

// T:O(logN), S:O(1), 0 ms
class Solution {
    public int integerReplacement(int n) {
        int count = 0;
        if(n == Integer.MAX_VALUE) return 32;
        while(n > 1){
            if(n%2 == 0){
                n = n /2;
            }else{
                if(n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
                    n = n - 1;
                }else{
                    n = n + 1;
                }
            }
            count++;
        }

        return count;
    }
}