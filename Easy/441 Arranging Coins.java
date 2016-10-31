/*
This question is from https://leetcode.com/problems/arranging-coins/
Difficulty: easy

You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 */

// d0-while, T:O(), M:O(1), 64ms
public class Solution {
    public int arrangeCoins(int n) {
        int stair = 0;
        int coins = 1;
        do{
            n = n - coins;
            if(n >= 0) stair ++;
            coins ++;
        } while (n > 0);

        return stair;
    }
}

// quadratic equation, sum of arithmetic progression, T:O(1), 58 ms
// n = (x(x+1))/2, x^2 + x -2n = 0, x = (-1 + sqrt(1+8n))/2
public class Solution {
    public int arrangeCoins(int n) {
        return (-1+ (int)Math.sqrt(1 + 8*(long)n))/2;
    }
}

