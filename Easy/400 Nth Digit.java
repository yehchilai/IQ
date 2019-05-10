/*
This question is from https://leetcode.com/problems/nth-digit/
Difficulty: easy

Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
*/
// T:O(lgN) 10 based?, S:O(1), 0ms
class Solution {
    public int findNthDigit(int n) {
        n = n - 1;
        int tens = 1;
        int digits = 1;

        while(n/(9*tens*digits) >= 1){
            n = n - 9*tens*digits;
            tens = tens * 10;
            digits = digits + 1;
        }

        String str = Integer.toString(tens + n/digits);

        return str.charAt(n%digits) - '0';
    }
}

//
public class Solution {
    public int findNthDigit(int n) {
        n = n-1; // when n = 1~9, need to subtract 1.
        int tens = 1;
        int digits = 1;
        while(n/(9*tens*digits) >= 1){
            n = n - (9*tens*digits);
            tens = tens*10;
            digits = digits + 1;
        }

        String str = Integer.toString(tens + n/digits);
        return Character.getNumericValue(str.charAt(n%digits));
    }
}
