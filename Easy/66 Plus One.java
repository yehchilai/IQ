/*
This question is from https://leetcode.com/problems/plus-one/
Difficulty: easy

Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.


Time Complexity: O(N)
*/

// T:O(N), S:O(1), 0 ms
class Solution {
    public int[] plusOne(int[] digits) {

        if(digits.length == 0) return digits;

        int carrier = 0;
        boolean first = true;

        for(int i = digits.length - 1 ; i >= 0; i--){
            int sum  = 0;
            if(first){
                sum = digits[i] + 1;
            }else{
                sum = digits[i] + carrier;
            }
            carrier = sum/10;
            if(carrier == 0){
                digits[i] = sum;
                return digits;
            }else{
                digits[i] = sum%10;
            }
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;

        return result;
    }
}


// I have no idea about this question. The answer refers to https://leetcode.com/discuss/71659/accepted-easy-java-solution
public class Solution {
    public int[] plusOne(int[] digits) {
        if(digits.length == 0) return digits;
        for(int i = digits.length - 1; i >= 0; i--){
            if(digits[i] != 9){
                digits[i]++;
                return digits;
            }else{
                digits[i] = 0;
            }
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}