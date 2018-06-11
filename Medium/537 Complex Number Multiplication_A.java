/*
This qestion is from https://leetcode.com/problems/complex-number-multiplication/description/
Difficulty: medium

Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.

*/

// T:O(1), S:O(1), 10ms
class Solution {
    public String complexNumberMultiply(String a, String b) {
        String[] aa = a.split("\\+");
        String[] bb = b.split("\\+");

        int calculateA = 0;
        int calculateAI = 0;
        int calculateB = 0;
        int calculateBI = 0;

        if(aa.length == 1){
            if(aa[0].contains("i")){
                calculateAI = Integer.valueOf(aa[0].replace("i",""));
            }else{
                calculateA = Integer.valueOf(aa[0]);
            }
        }else{
            calculateA = Integer.valueOf(aa[0]);
            calculateAI = Integer.valueOf(aa[1].replace("i",""));
        }

        if(bb.length == 1){
            if(bb[0].contains("i")){
                calculateBI = Integer.valueOf(bb[0].replace("i",""));
            }else{
                calculateB = Integer.valueOf(bb[0]);
            }
        }else{
            // System.out.println(b);
            // System.out.println(bb[0]);
            // System.out.println(bb[1]);
            calculateB = Integer.valueOf(bb[0]);
            calculateBI = Integer.valueOf(bb[1].replace("i",""));

        }

        return String.valueOf(calculateA * calculateB - calculateAI * calculateBI)
            + "+" +String.valueOf(calculateAI * calculateB + calculateA * calculateBI)
            + "i";
    }
}