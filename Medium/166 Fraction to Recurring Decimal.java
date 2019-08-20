/**
This question is from https://leetcode.com/problems/fraction-to-recurring-decimal/
Difficulty: medium

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"

*/

// T:O(N), number of fraction, S:O(N), 2 ms (59.25% )
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
        if(denominator == 0) throw new Error();
        StringBuilder sb = new StringBuilder();
        if(numerator < 0 ^ denominator < 0) sb.append("-");
        long upper = Math.abs(Long.valueOf(numerator));
        long bottom = Math.abs(Long.valueOf(denominator));
        sb.append(String.valueOf(upper/bottom));

        long remainder = upper % bottom;
        if(remainder == 0) return sb.toString();
        sb.append(".");
        // remainder: StringBuilder length
        HashMap<Long, Integer> map = new HashMap();
        while(remainder != 0){
            if(map.containsKey(remainder)){
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                break;
            }

            map.put(remainder, sb.length());
            remainder = remainder * 10;
            sb.append(remainder / bottom);
            remainder = remainder % bottom;
        }

        return sb.toString();
    }
}