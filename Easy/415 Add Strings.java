/*
This question is from https://leetcode.com/problems/add-strings/
Difficulty: easy

Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

*/
// Use StringBuilder, T:O(m+n), M:O(m+1/n+1), 23ms
public class Solution {
    public String addStrings(String num1, String num2) {
        int num1Length = num1.length();
        int num2Length = num2.length();
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = num1Length - 1, j = num2Length - 1; i >=0 || j>=0; i--, j-- ){
            int num1Int = 0;
            int num2Int = 0;
            if(i >= 0) num1Int = num1.charAt(i) - '0';
            if(j >= 0) num2Int = num2.charAt(j) - '0';
            sb.append((num1Int + num2Int + carry)%10);
            carry = (num1Int + num2Int + carry)/10;
        }

        if(carry > 0) sb.append(carry);

        return sb.reverse().toString();
    }
}

// T:O(m|n), M:O(M+1|M+1), 4ms
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();


        int len1 = num1.length();
        int len2 = num2.length();
        int i = len1 - 1;
        int j = len2 - 1;
        int carrier = 0;
        while(i >= 0 || j >= 0){
            int sum = 0;

            if(i >= 0 && j >= 0){
                int a = Character.getNumericValue(num1.charAt(i));
                int b = Character.getNumericValue(num2.charAt(j));
                sum = a + b + carrier;
                i--;
                j--;
            }else if(i >= 0){
                int a = Character.getNumericValue(num1.charAt(i));
                sum = a + carrier;
                i--;
            }else if(j >= 0){
                int b = Character.getNumericValue(num2.charAt(j));
                sum = b + carrier;
                j--;
            }
            carrier = sum / 10;
            sb.append(sum%10);
        }
        if(carrier == 1) sb.append(carrier);
        return sb.reverse().toString();
    }
}
