/**
This question is from https://leetcode.com/problems/basic-calculator-ii/
Difficulty: medium

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.

*/

// T:O(N), S:O(N), 10ms (90.22%)
class Solution {
    public int calculate(String s) {
        if(s == null) return 0;
        int ans = 0;
        int len = s.length();
        int tmpSum = 0;
        int currNum = 0;
        char operator = '+';
        for(int i = 0 ; i < len; i++){
            char c = s.charAt(i);

            if(Character.isDigit(c)){
                currNum = currNum * 10 + (int)(c - '0');
            }

            if((!Character.isDigit(c) && c != ' ') || i == len - 1){
                switch (operator){
                    case '+':
                        ans += tmpSum;
                        tmpSum = currNum;
                        break;
                    case '-':
                        ans += tmpSum;
                        tmpSum = 0 - currNum;
                        break;
                    case '*':
                        tmpSum = tmpSum * currNum;
                        break;
                    case '/':
                        tmpSum = tmpSum / currNum;
                        break;
                }
                operator = c;
                currNum = 0;
            }

        }
        ans += tmpSum;
        return ans;
    }
}