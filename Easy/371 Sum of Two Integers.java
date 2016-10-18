/*
This question is from https://leetcode.com/problems/sum-of-two-integers/
Difficulty: easy

Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.


*/
// 0ms
public class Solution {
    public int getSum(int a, int b) {
        int result = a ^ b; //XOR
        int carry = (a & b) << 1;
        int result = 0;
        if(carry != 0){
            result = getSum(result, carry);
        }else{
            result = result;
        }
        
        return result;
    }
}