/*
This question is from https://leetcode.com/problems/convert-a-number-to-hexadecimal/
Difficulty: easy

Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"
*/

// StringBuilder does not work.
public class Solution {
    public String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        String hex = "0123456789abcdef";

        if(num < 0){
            num = ~num + 1;
        }

        int remainder = num % 16;
        int quotient = num / 16;
        sb.append(hex.charAt(remainder));
        while(quotient > 0){
            remainder = quotient % 16;
            quotient = quotient / 16;
            sb.append(hex.charAt(remainder));
        }

        return sb.reverse().toString();
    }
}

// Bit maniputation, T:O(n), M:O(n), 8ms
public class Solution {
    public String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        String hex = "0123456789abcdef";
        if(num == 0) return "0";

        while(num != 0){
            sb.append(hex.charAt(num & 15));
            num = (num >>> 4) ; // unsigned shift
        }

        return sb.reverse().toString();
    }
}
