/*
This question is from https://leetcode.com/problems/reconstruct-original-digits-from-english/
Difficulty: medium

Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"

*/
// Math, T:O(N), M:O(N), 15ms
public class Solution {
    public String originalDigits(String s) {
        char[] charArr = s.toCharArray();
        int[] digits = new int[10];
        for(int i = 0; i < charArr.length; i++){
            switch(charArr[i]){
                case 'z':
                    digits[0]++;
                    break;
                case 'o':
                    digits[1]++;
                    break;
                case 'w':
                    digits[2]++;
                    break;
                case 'r':
                    digits[3]++;
                    break;
                case 'f':
                    digits[4]++;
                    break;
                case 'v':
                    digits[5]++;
                    break;
                case 'x':
                    digits[6]++;
                    break;
                case 's':
                    digits[7]++;
                    break;
                case 'g':
                    digits[8]++;
                    break;
                case 'i':
                    digits[9]++;
                    break;
            }
        }
        digits[7] = digits[7] - digits[6] ;// s: six, seven
        digits[5] = digits[5] - digits[7] ;// v: five, seven
        digits[4] = digits[4] - digits[5] ;// f: four, five
        digits[1] = digits[1] - digits[0] - digits[2] - digits[4] ;// o: one, zero, two, four
        digits[9] = digits[9] - digits[8] - digits[6] - digits[5] ;// i: nine, eight, six, five
        digits[3] = digits[3] - digits[4] - digits[0] ;// r: three, four, zero
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < digits.length; i++){
            for(int j = 0; j < digits[i]; j++){
                sb.append(String.valueOf(i));
            }
        }
        return sb.toString();
    }
}
