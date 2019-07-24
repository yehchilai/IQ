/*
This question is from https://leetcode.com/problems/multiply-strings/
difficulty: medium

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

*/
// T:O(M*N), S:O(m+n), 4 ms (87.75%)
class Solution {
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null) return "";
        int m = num1.length();
        int n = num2.length();

        if(m == 0 || n == 0) return "";
        int[] multi = new int[m+n];

        for(int i = m - 1; i >=0; i--){
            for(int j = n - 1; j >= 0; j--){
                int mn = (int)(num1.charAt(i) - '0') * (int)(num2.charAt(j) - '0');
                int sum = mn + multi[i + j + 1];

                multi[i + j + 1] = sum % 10;
                multi[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int digit: multi){
            if(digit != 0 || sb.length() != 0){
                sb.append(digit);
            }
        }

        if(sb.length() == 0) return "0";
        return sb.toString();
    }
}