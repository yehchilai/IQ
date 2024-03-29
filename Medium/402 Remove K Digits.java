/*
This question is from https://leetcode.com/problems/remove-k-digits/
Difficulty: medium

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
*/
// T:O(N + k), S:O(N), 5 ms
class Solution {
    public String removeKdigits(String num, int k) {
        StringBuilder sb  = new StringBuilder();
        int len = num.length();
        int remain = len - k;

        for(int i = 0; i < len; i++){
            // System.out.println("check: "+num.charAt(i));
            while(sb.length() > 0
                  && sb.charAt(sb.length() - 1) > num.charAt(i)
                  && k > 0){
                // System.out.println("pop: "+sb.charAt(sb.length() - 1));
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(num.charAt(i));
        }
        // System.out.println("==========");
        while(sb.length() > remain){
            sb.deleteCharAt(sb.length() - 1);
        }

        while(sb.length() > 0 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }

        if(sb.length() == 0) return "0";
        return sb.toString();
    }
}

// T:O(N*K), S:O(N), 32 ms
class Solution {
    public String removeKdigits(String num, int k) {
        StringBuilder sb  = new StringBuilder(num);

        while(k > 0){

            int i = 0;
            while((i < sb.length() - 1) && (sb.charAt(i) <= sb.charAt(i + 1))) i++;
            sb.deleteCharAt(i);
            k--;
        }

        while(sb.length() > 0 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }

        if(sb.length() == 0) return "0";
        return sb.toString();
    }
}